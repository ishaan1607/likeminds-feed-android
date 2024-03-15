package com.likeminds.feed.android.core.ui.widgets.searchbar.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.content.res.Configuration
import android.text.*
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.CheckResult
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleCoroutineScope
import com.likeminds.feed.android.core.databinding.LmFeedSearchBarBinding
import com.likeminds.feed.android.core.ui.base.styles.*
import com.likeminds.feed.android.core.ui.widgets.searchbar.style.LMFeedSearchBarViewStyle
import com.likeminds.feed.android.core.utils.LMFeedAnimationUtils.circleHideView
import com.likeminds.feed.android.core.utils.LMFeedAnimationUtils.circleRevealView
import com.likeminds.feed.android.core.utils.LMFeedViewUtils
import com.likeminds.feed.android.core.utils.LMFeedViewUtils.hide
import com.likeminds.feed.android.core.utils.LMFeedViewUtils.show
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*

class LMFeedSearchBarView @JvmOverloads constructor(
    mContext: Context,
    attributeSet: AttributeSet? = null
) : ConstraintLayout(mContext, attributeSet) {

    private val isHardKeyboardAvailable: Boolean
        get() = this.resources.configuration.keyboard != Configuration.KEYBOARD_NOKEYS

    /**
     * The previous query text.
     */
    private var mOldQuery: CharSequence = ""

    /**
     * The current query text.
     */
    private var mCurrentQuery = ""

    /**
     * Listener for when the search view opens and closes.
     */
    private var mSearchViewListener: LMFeedSearchBarListener? = null

    /**
     * Determines if the search view is opened or closed.
     * @return True if the search view is open, false if it is closed.
     */
    /**
     * Whether or not the search view is open right now.
     */
    var isOpen = false
        private set

    private val binding =
        LmFeedSearchBarBinding.inflate(LayoutInflater.from(context), this, true)

    private lateinit var lifecycleScope: LifecycleCoroutineScope

    private fun displayClearButton(display: Boolean) {
        binding.ivClose.visibility = if (display) View.VISIBLE else View.GONE
    }

    companion object {
        private const val TAG = "LMFeedCustomSearchBarView"
    }

    init {
        binding.apply {
            ivClose.setOnClickListener {
                etSearch.setText("")
                mSearchViewListener?.onSearchCrossed()
            }

            ivBack.setOnClickListener {
                closeSearch()
            }
            initSearchView()
        }
    }

    fun initialize(lifecycleScope: LifecycleCoroutineScope) {
        this.lifecycleScope = lifecycleScope
    }

    private fun initSearchView() {
        binding.apply {
            etSearch.setOnEditorActionListener(TextView.OnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    onSubmitQuery()
                    return@OnEditorActionListener true
                }
                false
            })
            etSearch.onFocusChangeListener = OnFocusChangeListener { view, hasFocus ->
                // If we gain focus, show keyboard and show suggestions.
                if (hasFocus) {
                    showKeyboard(view)
                }
            }
        }
    }

    private fun showKeyboard(view: View?) {
        view?.requestFocus()
        if (isHardKeyboardAvailable.not()) {
            val inputMethodManager =
                view?.context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            inputMethodManager?.showSoftInput(view, 0)
        }
    }

    fun setSearchViewListener(mSearchViewListener: LMFeedSearchBarListener?) {
        this.mSearchViewListener = mSearchViewListener
    }

    private fun clearFocusInHere() {
        LMFeedViewUtils.hideKeyboard(this)
        binding.etSearch.clearFocus()
    }

    /**
     * Adds TextWatcher to edit text with Flow operators
     * **/
    @ExperimentalCoroutinesApi
    @CheckResult
    fun EditText.textChanges(): Flow<CharSequence?> {
        return callbackFlow {
            val listener = object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {

                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (isOpen) {
                        onTextChanged(s.toString(), this@callbackFlow)
                    }
                }
            }
            addTextChangedListener(listener)
            awaitClose { removeTextChangedListener(listener) }
        }.onStart { emit(text) }
    }

    /**
     * Filters and updates the buttons when text is changed.
     * @param newText The new text.
     */
    private fun onTextChanged(newText: CharSequence, producerScope: ProducerScope<CharSequence?>) {
        // Get current query
        mCurrentQuery = binding.etSearch.text.toString()

        // If the text is not empty, show the empty button and hide the voice button
        if (!TextUtils.isEmpty(mCurrentQuery)) {
            displayClearButton(true)
        } else {
            displayClearButton(false)
        }

        // If we have a query listener and the text has changed, call it.
        if ((!TextUtils.isEmpty(mCurrentQuery) || !TextUtils.isEmpty(mOldQuery))
        ) {
            producerScope.trySend(newText.toString())
        }

        mOldQuery = mCurrentQuery
    }

    /**
     * Closes the search view if necessary.
     */
    fun closeSearch() {
        if (!isOpen) {
            return
        }
        isOpen = false
        // Clear text, values, and focus.
        binding.etSearch.setText("")
        clearFocusInHere()
        val listenerAdapter: AnimatorListenerAdapter = object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                // After the animation is done. Hide the root view.
                binding.searchToolbar.visibility = View.GONE
            }
        }
        circleHideView(binding.searchToolbar, listenerAdapter)
        mSearchViewListener?.onSearchViewClosed()
    }

    /**
     * Called when a query is submitted. This will close the search view.
     */
    private fun onSubmitQuery() {
        // Get the query.
        val query: CharSequence? = binding.etSearch.text
        // If the query is not null and it has some text, submit it.
        if (query != null && TextUtils.getTrimmedLength(query) > 0) {
            clearFocusInHere()
        }
    }

    fun setStyle(searchBarViewStyle: LMFeedSearchBarViewStyle) {
        searchBarViewStyle.apply {

            //sets background color of the search bar
            backgroundColor?.let {
                setBackgroundColor(ContextCompat.getColor(context, it))
            }

            elevation?.let {
                setElevation(resources.getDimension(it))
            }

            configureSearchInputStyle(searchInputStyle)
            configureSearchBackIcon(searchBackIconStyle)
            configureSearchCloseIcon(searchCloseIconStyle)
        }
    }

    private fun configureSearchInputStyle(searchInputStyle: LMFeedEditTextStyle) {
        binding.etSearch.setStyle(searchInputStyle)
    }

    private fun configureSearchBackIcon(searchBackIconStyle: LMFeedIconStyle?) {
        binding.ivBack.apply {
            if (searchBackIconStyle == null) {
                hide()
            } else {
                setStyle(searchBackIconStyle)
                show()
            }
        }
    }

    private fun configureSearchCloseIcon(searchCloseIconStyle: LMFeedIconStyle?) {
        binding.ivClose.apply {
            if (searchCloseIconStyle == null) {
                hide()
            } else {
                setStyle(searchCloseIconStyle)
                show()
            }
        }
    }

    fun openSearch() {
        // If search is already open, just return.
        if (isOpen) {
            return
        }
        // Get focus
        binding.etSearch.setText("")
        binding.etSearch.requestFocus()
        circleRevealView(binding.searchToolbar)
        elevation = 20F
        isOpen = true
        mSearchViewListener?.onSearchViewOpened()
    }

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    fun observeSearchView(debounce: Boolean = true) {
        if (debounce) {
            binding.etSearch.textChanges()
                .debounce(500)
                .distinctUntilChanged()
                .onEach { keyword ->
                    if (keyword != null) {
                        if (keyword.isNotEmpty()) {
                            mSearchViewListener?.onKeywordEntered(keyword.toString())
                        } else {
                            mSearchViewListener?.onEmptyKeywordEntered()
                        }
                    }
                }
                .launchIn(lifecycleScope)
        } else {
            binding.etSearch.textChanges()
                .distinctUntilChanged()
                .onEach { keyword ->
                    if (!keyword.isNullOrEmpty()) {
                        mSearchViewListener?.onKeywordEntered(keyword.toString())
                    }
                }
                .launchIn(lifecycleScope)
        }
    }
}

/**
 * Interface that handles the opening and closing of the SearchView.
 */
interface LMFeedSearchBarListener {
    fun onSearchViewOpened() {
        //triggered when a user clicks on search icon and search view is opened
    }

    fun onSearchViewClosed() {
        //triggered when the search view is closed
    }

    fun onSearchCrossed() {
        //triggered when the user crosses the search view
    }

    fun onKeywordEntered(keyword: String) {
        //triggered when a user enters a text to search
    }

    fun onEmptyKeywordEntered() {
        //triggered when a back-presses till last of the text
    }
}