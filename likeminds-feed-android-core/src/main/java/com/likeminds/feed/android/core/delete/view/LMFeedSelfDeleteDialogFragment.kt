package com.likeminds.feed.android.core.delete.view

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.databinding.LmFeedDialogFragmentSelfDeleteBinding
import com.likeminds.feed.android.core.delete.model.DELETE_TYPE_POST
import com.likeminds.feed.android.core.delete.model.LMFeedDeleteExtras
import com.likeminds.feed.android.core.ui.widgets.alertdialog.view.LMFeedAlertDialogView
import com.likeminds.feed.android.core.utils.*

open class LMFeedSelfDeleteDialogFragment :
    DialogFragment() {

    companion object {
        private const val TAG = "LMFeedSelfDeleteDialogFragment"
        private const val LM_FEED_SELF_DELETE_EXTRAS = "LM_FEED_SELF_DELETE_EXTRAS"

        @JvmStatic
        fun showDialog(
            supportFragmentManager: FragmentManager,
            deleteExtras: LMFeedDeleteExtras
        ) {
            LMFeedSelfDeleteDialogFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(LM_FEED_SELF_DELETE_EXTRAS, deleteExtras)
                }
            }.show(supportFragmentManager, TAG)
        }
    }

    private var selfDeleteDialogListener: LMFeedSelfDeleteDialogListener? = null

    private lateinit var binding: LmFeedDialogFragmentSelfDeleteBinding
    private lateinit var deletedExtras: LMFeedDeleteExtras

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            selfDeleteDialogListener = parentFragment as LMFeedSelfDeleteDialogListener?
        } catch (e: ClassCastException) {
            throw ClassCastException("Calling fragment must implement LMFeedSelfDeleteDialogListener interface")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        receiveExtras()
    }

    private fun receiveExtras() {
        arguments?.let {
            deletedExtras = LMFeedExtrasUtil.getParcelable(
                it,
                LM_FEED_SELF_DELETE_EXTRAS,
                LMFeedDeleteExtras::class.java
            ) ?: throw emptyExtrasException(TAG)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LmFeedDialogFragmentSelfDeleteBinding.inflate(layoutInflater)

        binding.apply {
            customizeDeleteDialog(alertDialogDelete)
            return root
        }
    }

    protected open fun customizeDeleteDialog(alertDialogDelete: LMFeedAlertDialogView) {
        val selfDeleteDialogFragmentStyle = LMFeedStyleTransformer.selfDeleteDialogFragmentStyle

        alertDialogDelete.setStyle(selfDeleteDialogFragmentStyle)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
        initListeners()
    }

    private fun initUI() {
        binding.alertDialogDelete.apply {
            setPositiveButtonEnabled(true)

            if (deletedExtras.entityType == DELETE_TYPE_POST) {
                //todo:
//                val postAsVariable = deleteExtras.postAsVariable
                setAlertTitle(
                    getString(
                        R.string.lm_feed_delete_s_question,
//                    postAsVariable.pluralizeOrCapitalize(WordAction.ALL_SMALL_SINGULAR)
                    )
                )
                setAlertSubtitle(
                    getString(
                        R.string.lm_feed_delete_s_message,
//                        postAsVariable.pluralizeOrCapitalize(WordAction.ALL_SMALL_SINGULAR)
                    )
                )
            } else {
                setAlertTitle(getString(R.string.lm_feed_delete_comment_question))
                setAlertSubtitle(getString(R.string.lm_feed_delete_comment_message))
            }
        }
    }

    //sets click listeners
    private fun initListeners() {
        binding.alertDialogDelete.apply {
            setPositiveButtonClickListener {
                onDeleteAlertPositiveButtonClicked()
            }

            setNegativeButtonClickListener {
                onDeleteAlertNegativeButtonClicked()
            }
        }
    }

    protected open fun onDeleteAlertPositiveButtonClicked() {
        selfDeleteDialogListener?.onEntityDeletedByAuthor(deletedExtras)
        dismiss()
    }

    protected open fun onDeleteAlertNegativeButtonClicked() {
        dismiss()
    }
}

interface LMFeedSelfDeleteDialogListener {
    fun onEntityDeletedByAuthor(deleteExtras: LMFeedDeleteExtras)
}