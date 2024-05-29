package com.likeminds.feed.android.core.poll.create.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.datepicker.*
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.databinding.LmFeedFragmentCreatePollBinding
import com.likeminds.feed.android.core.databinding.LmFeedItemCreatePollOptionBinding
import com.likeminds.feed.android.core.poll.create.adapter.LMFeedCreatePollOptionAdapterListener
import com.likeminds.feed.android.core.poll.create.adapter.LMFeedPollAdvancedOptionsAdapter
import com.likeminds.feed.android.core.poll.create.model.LMFeedCreatePollOptionViewData
import com.likeminds.feed.android.core.poll.create.viewmodel.LMFeedCreatePollViewModel
import com.likeminds.feed.android.core.poll.result.model.LMFeedPollViewData
import com.likeminds.feed.android.core.ui.base.styles.setStyle
import com.likeminds.feed.android.core.ui.base.views.*
import com.likeminds.feed.android.core.ui.widgets.headerview.view.LMFeedHeaderView
import com.likeminds.feed.android.core.ui.widgets.post.postheaderview.view.LMFeedPostHeaderView
import com.likeminds.feed.android.core.utils.*
import com.likeminds.feed.android.core.utils.LMFeedValueUtils.isValidIndex
import com.likeminds.feed.android.core.utils.LMFeedViewUtils.hide
import com.likeminds.feed.android.core.utils.LMFeedViewUtils.show
import com.likeminds.feed.android.core.utils.coroutine.observeInLifecycle
import com.likeminds.feed.android.core.utils.user.LMFeedUserViewData
import kotlinx.coroutines.flow.onEach
import java.text.SimpleDateFormat
import java.util.*

open class LMFeedCreatePollFragment : Fragment(), LMFeedCreatePollOptionAdapterListener {

    private lateinit var binding: LmFeedFragmentCreatePollBinding
    private var poll: LMFeedPollViewData? = null

    private val viewModel: LMFeedCreatePollViewModel by viewModels()
    private var isAdvancedOptionsVisible = false

    companion object {
        const val TAG = "LMFeedCreatePollFragment"
        const val LM_FEED_CREATE_POLL_FRAGMENT_EXTRAS = "LM_FEED_CREATE_POLL_FRAGMENT_EXTRAS"
        const val DATE_PICKER_TAG = "DATE_PICKER"
        const val TIME_PICKER_TAG = "TIME_PICKER"
        const val MAX_OPTIONS = 10

        @JvmStatic
        fun getInstance(poll: LMFeedPollViewData?): LMFeedCreatePollFragment {
            val fragment = LMFeedCreatePollFragment()
            val args = Bundle()
            args.putParcelable(LM_FEED_CREATE_POLL_FRAGMENT_EXTRAS, poll)
            fragment.arguments = args
            return fragment
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        receiveExtras()
    }

    private fun receiveExtras() {
        if (arguments == null || arguments?.containsKey(LM_FEED_CREATE_POLL_FRAGMENT_EXTRAS) == false) {
            requireActivity().supportFragmentManager.popBackStack()
            return
        } else {
            poll = LMFeedExtrasUtil.getParcelable(
                arguments,
                LM_FEED_CREATE_POLL_FRAGMENT_EXTRAS,
                LMFeedPollViewData::class.java
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LmFeedFragmentCreatePollBinding.inflate(inflater, container, false)

        binding.apply {
            customizeCreatePollHeader(headerViewCreatePoll)
            customizeAuthorView(authorView)
            customizePollQuestion(tvPollQuestionTitle, etPollQuestion)
            customizePollExpiryTime(tvPollExpireTitle, tvPollExpireTime)
            customizePollOptions(tvPollOptionsTitle, tvAddOption)
            customizeAdvancedOptionTitle(tvAdvancedOptions)
            customizeAdvanceOptionSwitchOptions(
                switchAnonymousPoll,
                switchLiveResults,
                switchAddNewOptions
            )
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
        initUI()
        fetchInitialData()
        observeData()
    }

    //customize the header view of the fragment
    protected open fun customizeCreatePollHeader(headerView: LMFeedHeaderView) {
        headerView.apply {
            setStyle(LMFeedStyleTransformer.createPollFragmentViewStyle.headerViewStyle)

            setTitleText(getString(R.string.lm_feed_new_poll))
            setSubmitText(getString(R.string.lm_feed_done))
            setSubmitButtonEnabled(false)
        }
    }

    //customize the author view of the fragment
    protected open fun customizeAuthorView(authorView: LMFeedPostHeaderView) {
        authorView.apply {
            setStyle(LMFeedStyleTransformer.createPollFragmentViewStyle.authorViewStyle)
        }
    }

    //customize the poll question title and input box
    protected open fun customizePollQuestion(
        tvPollQuestionTitle: LMFeedTextView,
        etPollQuestion: LMFeedEditText
    ) {
        //customize the poll question title
        tvPollQuestionTitle.apply {
            setStyle(LMFeedStyleTransformer.createPollFragmentViewStyle.pollQuestionTitleViewStyle)
        }

        //customize the poll question edit text
        etPollQuestion.apply {
            setStyle(LMFeedStyleTransformer.createPollFragmentViewStyle.pollQuestionViewStyle)
        }
    }

    //customize the poll options title and add option text view
    protected open fun customizePollOptions(
        tvPollOptionsTitle: LMFeedTextView,
        tvAddOption: LMFeedTextView
    ) {
        tvPollOptionsTitle.apply {
            setStyle(LMFeedStyleTransformer.createPollFragmentViewStyle.pollOptionsTitleViewStyle)
        }

        tvAddOption.apply {
            setStyle(LMFeedStyleTransformer.createPollFragmentViewStyle.pollAddOptionViewStyle)
        }
    }

    //customize the poll expiry time title and text view
    protected open fun customizePollExpiryTime(
        tvPollExpireTitle: LMFeedTextView,
        tvPollExpireTime: LMFeedTextView
    ) {
        tvPollExpireTitle.apply {
            setStyle(LMFeedStyleTransformer.createPollFragmentViewStyle.pollExpiryTimeTitleViewStyle)
        }

        tvPollExpireTime.apply {
            setStyle(LMFeedStyleTransformer.createPollFragmentViewStyle.pollExpiryTimeViewStyle)
        }
    }

    //customize the advanced options title
    protected open fun customizeAdvancedOptionTitle(tvAdvancedOptions: LMFeedTextView) {
        tvAdvancedOptions.apply {
            setStyle(LMFeedStyleTransformer.createPollFragmentViewStyle.pollAdvanceOptionViewStyle)
        }
    }

    //customize the advance option switch options
    protected open fun customizeAdvanceOptionSwitchOptions(
        switchAnonymousPoll: LMFeedSwitch,
        switchLiveResults: LMFeedSwitch,
        switchAddNewOptions: LMFeedSwitch
    ) {
        switchAddNewOptions.apply {
            setStyle(LMFeedStyleTransformer.createPollFragmentViewStyle.pollAdvanceOptionSwitchViewStyle)
        }

        switchAnonymousPoll.apply {
            setStyle(LMFeedStyleTransformer.createPollFragmentViewStyle.pollAdvanceOptionSwitchViewStyle)
        }

        switchLiveResults.apply {
            setStyle(LMFeedStyleTransformer.createPollFragmentViewStyle.pollAdvanceOptionSwitchViewStyle)
        }
    }


    //initializes the UI
    private fun initUI() {
        initPollOptionListView()
        initPollMultiSelectStateSpinner()
        initPollMultiSelectPollOptionSpinner()
        initPollQuestionListeners()
    }

    //initializes the poll option list view
    private fun initPollOptionListView() {
        binding.rvPollOptions.apply {
            setAdapter(this@LMFeedCreatePollFragment)
            replaceOptions(viewModel.createInitialPollOptionList())
            updatePollItemCacheSize()
        }
    }

    //initializes the poll multi select state spinner
    private fun initPollMultiSelectStateSpinner() {
        val spinnerAdapter = LMFeedPollAdvancedOptionsAdapter(
            requireContext(),
            viewModel.getMultipleOptionStateList()
        )

        binding.spinnerMultipleOption.apply {
            setAdapter(spinnerAdapter)
            setSelection(0)
        }
    }

    //initializes the poll multi select poll option spinner
    private fun initPollMultiSelectPollOptionSpinner() {
        val spinnerAdapter = LMFeedPollAdvancedOptionsAdapter(
            requireContext(),
            viewModel.getMultipleOptionNoList().subList(0, binding.rvPollOptions.itemCount + 1)
        )

        binding.spinnerMultipleOptionValue.apply {
            setAdapter(spinnerAdapter)
            setSelection(0)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initPollQuestionListeners() {
        binding.etPollQuestion.apply {

            /**
             * As the scrollable edit text is inside a scroll view,
             * this touch listener handles the scrolling of the edit text.
             * When the edit text is touched and has focus then it disables scroll of scroll-view.
             */
            setOnTouchListener(View.OnTouchListener { v, event ->
                if (hasFocus()) {
                    v.parent.requestDisallowInterceptTouchEvent(true)
                    when (event.action and MotionEvent.ACTION_MASK) {
                        MotionEvent.ACTION_SCROLL -> {
                            v.parent.requestDisallowInterceptTouchEvent(false)
                            return@OnTouchListener true
                        }
                    }
                }
                false
            })

            addTextChangedListener {
                validatePoll()
            }
        }
    }

    //attaches the listeners
    private fun initListeners() {
        binding.apply {
            headerViewCreatePoll.setNavigationIconClickListener {
                onNavigationIconClicked()
            }

            tvAddOption.setOnClickListener {
                onAddPollOptionClicked()
            }

            tvPollExpireTime.setOnClickListener {
                onPollExpireTimeClicked()
            }

            tvAdvancedOptions.setOnClickListener {
                onAdvancedOptionsClicked()
            }

            headerViewCreatePoll.setSubmitButtonClickListener {
                onPollSubmitClicked()
            }
        }
    }

    //fetches the initial data
    private fun fetchInitialData() {
        viewModel.getLoggedInUser()
    }

    //observes the response data
    private fun observeData() {
        viewModel.loggedInUser.observe(viewLifecycleOwner) { user ->
            initAuthorView(user)
        }

        viewModel.errorEvent.onEach { response ->
            when (response) {
                is LMFeedCreatePollViewModel.ErrorEvent.GetLoggedInUserError -> {
                    LMFeedViewUtils.showErrorMessageToast(
                        requireContext(),
                        response.message
                    )
                }
            }
        }.observeInLifecycle(viewLifecycleOwner)
    }

    //initializes the author view
    private fun initAuthorView(user: LMFeedUserViewData) {
        binding.apply {
            nestedScrollCreatePoll.show()
            authorView.apply {
                setAuthorImage(user)
                setAuthorName(user.name)
            }
        }
    }

    //customize click of the navigation icon
    protected open fun onNavigationIconClicked() {
        requireActivity().onBackPressedDispatcher.onBackPressed()
    }

    //customize click of the add option text view
    protected open fun onAddPollOptionClicked() {
        binding.rvPollOptions.apply {
            addOption(itemCount, viewModel.getEmptyPollOption())
            updatePollItemCacheSize()
        }
        initPollMultiSelectPollOptionSpinner()
        validatePollOptionCount()
    }

    //customize the click of poll expiry time
    protected open fun onPollExpireTimeClicked() {
        // calendar instance
        val calendar = Calendar.getInstance()

        //date formatter
        val simpleDateFormat = SimpleDateFormat(
            "dd-MM-yy HH:mm",
            Locale.getDefault()
        )


        //date picker
        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText(getString(R.string.lm_feed_select_poll_expiry_time))
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .setCalendarConstraints(
                CalendarConstraints.Builder()
                    .setValidator(DateValidatorPointForward.now())
                    .build()
            )
            .build()

        //time picker
        val timePicker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_12H)
            .setTitleText(getString(R.string.lm_feed_select_poll_expiry_time))
            .build()

        //show the date picker
        datePicker.show(requireActivity().supportFragmentManager, DATE_PICKER_TAG)

        //date picker listener
        datePicker.addOnPositiveButtonClickListener { epoch ->
            calendar.timeInMillis = epoch
            timePicker.show(requireActivity().supportFragmentManager, TIME_PICKER_TAG)
        }

        //time picker listener
        timePicker.addOnPositiveButtonClickListener {
            calendar.set(Calendar.HOUR_OF_DAY, timePicker.hour)
            calendar.set(Calendar.MINUTE, timePicker.minute)

            val dateString = simpleDateFormat.format(calendar.time)

            if (calendar.time.after(Date())) {
                binding.tvPollExpireTime.text = dateString
                viewModel.setPollExpiryTime(calendar.timeInMillis)
                validatePoll()
            } else {
                LMFeedViewUtils.showErrorMessageToast(
                    requireContext(),
                    getString(R.string.lm_feed_error_invalid_time)
                )
            }
        }
    }

    //customize the click of advanced options
    protected open fun onAdvancedOptionsClicked() {
        binding.apply {
            if (isAdvancedOptionsVisible) {
                isAdvancedOptionsVisible = false
                tvAdvancedOptions.setCompoundDrawablesWithIntrinsicBounds(
                    0,
                    0,
                    R.drawable.lm_feed_ic_arrow_edge_down,
                    0
                )
                LMFeedAnimationUtils.collapse(clAdvancedOptions)
            } else {
                isAdvancedOptionsVisible = true
                tvAdvancedOptions.setCompoundDrawablesWithIntrinsicBounds(
                    0,
                    0,
                    R.drawable.lm_feed_ic_arrow_edge_up,
                    0
                )
                LMFeedAnimationUtils.expand(clAdvancedOptions)
            }
        }
    }

    //customize the click of submit button
    protected open fun onPollSubmitClicked() {

    }

    override fun onPollOptionRemoved(createPollOptionViewData: LMFeedCreatePollOptionViewData) {
        val index = binding.rvPollOptions.getAllOptions().indexOf(createPollOptionViewData)
        if (index.isValidIndex()) {
            binding.rvPollOptions.removeOption(index)
            viewModel.removeBindingFromMap(index)
            validatePollOptionCount()
            validatePoll()
        }
    }

    override fun onPollOptionBinded(position: Int, binding: LmFeedItemCreatePollOptionBinding) {
        viewModel.addBindingToMap(position, binding)
        validatePollOptionCount()
    }

    override fun onPollOptionFilled() {
        validatePoll()
    }

    //validates the poll option count and hide/show add option text view
    private fun validatePollOptionCount() {
        val optionCount = binding.rvPollOptions.itemCount
        if (optionCount == MAX_OPTIONS) {
            binding.tvAddOption.hide()
        } else {
            binding.tvAddOption.show()
        }
    }

    //validates the poll and enables/disables submit button
    private fun validatePoll() {
        val pollOptionsBinding = viewModel.getPollOptionBindingMap()
        val pollOptions = ArrayList<String>()

        //get poll options where option is entered
        pollOptionsBinding.forEach { entry ->
            val binding = entry.value
            val pollOption = binding.etOption.text.toString()
            if (pollOption.isNotEmpty()) {
                pollOptions.add(pollOption)
            }
        }

        //get poll question
        val pollQuestion = binding.etPollQuestion.text.toString()

        //get poll expiry time
        val pollExpiryTime = viewModel.pollExpiryTime

        //validate the poll and enable/disable submit button
        if (pollQuestion.isNotEmpty() && pollOptions.size >= 2 && pollExpiryTime != null) {
            binding.headerViewCreatePoll.setSubmitButtonEnabled(true)
        } else {
            binding.headerViewCreatePoll.setSubmitButtonEnabled(false)
        }
    }
}