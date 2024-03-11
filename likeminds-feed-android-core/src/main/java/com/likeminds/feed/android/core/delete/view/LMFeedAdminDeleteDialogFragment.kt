package com.likeminds.feed.android.core.delete.view

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.databinding.LmFeedDialogFragmentAdminDeleteBinding
import com.likeminds.feed.android.core.delete.model.*
import com.likeminds.feed.android.core.ui.widgets.alertdialog.view.LMFeedAlertDialogView
import com.likeminds.feed.android.core.utils.*

open class LMFeedAdminDeleteDialogFragment
    : DialogFragment(),
    LMFeedReasonChooseBottomSheetListener {

    companion object {
        private const val TAG = "LMFeedDeleteContentDialogFragment"
        private const val LM_FEED_DELETE_EXTRAS = "LM_FEED_DELETE_EXTRAS"

        @JvmStatic
        fun showDialog(
            supportFragmentManager: FragmentManager, deleteExtras: LMFeedDeleteExtras
        ) {
            LMFeedAdminDeleteDialogFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(LM_FEED_DELETE_EXTRAS, deleteExtras)
                }
            }.show(supportFragmentManager, TAG)
        }
    }

    private lateinit var binding: LmFeedDialogFragmentAdminDeleteBinding
    private lateinit var deleteExtras: LMFeedDeleteExtras

    private var deleteDialogListener: LMFeedDeleteDialogListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            deleteDialogListener = parentFragment as LMFeedDeleteDialogListener?
        } catch (e: ClassCastException) {
            throw ClassCastException("Calling fragment must implement LMFeedDeleteDialogListener interface")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        receiveExtras()
    }

    private fun receiveExtras() {
        arguments?.let {
            deleteExtras = LMFeedExtrasUtil.getParcelable(
                it,
                LM_FEED_DELETE_EXTRAS,
                LMFeedDeleteExtras::class.java
            ) ?: throw emptyExtrasException(TAG)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LmFeedDialogFragmentAdminDeleteBinding.inflate(layoutInflater)

        binding.apply {
            customizeDeleteDialog(alertDialogDelete)
            return root
        }
    }

    protected open fun customizeDeleteDialog(alertDialogDelete: LMFeedAlertDialogView) {
        val adminDeleteDialogStyle =
            LMFeedStyleTransformer.adminDeleteDialogFragmentStyle.adminDeleteDialogStyle

        alertDialogDelete.setStyle(adminDeleteDialogStyle)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initListeners()
    }

    //sets data as per content type [COMMENT/POST]
    private fun initView() {
        binding.apply {
            if (deleteExtras.entityType == DELETE_TYPE_POST) {
                //todo:
                alertDialogDelete.setAlertTitle(
                    getString(
                        R.string.lm_feed_delete_s_question,
//                        deleteExtras.postAsVariable.pluralizeOrCapitalize(WordAction.ALL_SMALL_SINGULAR)
                    )
                )
                alertDialogDelete.setAlertSubtitle(
                    getString(
                        R.string.lm_feed_delete_s_message,
//                        deleteExtras.postAsVariable.pluralizeOrCapitalize(WordAction.ALL_SMALL_SINGULAR)
                    )
                )
            } else {
                alertDialogDelete.setAlertTitle(getString(R.string.lm_feed_delete_comment_question))
                alertDialogDelete.setAlertSubtitle(getString(R.string.lm_feed_delete_comment_message))
            }
        }
    }

    private fun initListeners() {
        binding.alertDialogDelete.apply {
            setAlertSelectorClickListener {
                onAlertSelectorClicked()
            }

            setPositiveButtonClickListener {
                onDeleteAlertPositiveButtonClicked()
            }

            setNegativeButtonClickListener {
                onDeleteAlertNegativeButtonClicked()
            }

            // sets listener to the reason edit text
            etReason.doAfterTextChanged {
                val reason = it.toString().trim()
                setPositiveButtonEnabled(reason.isNotEmpty())
            }
        }
    }

    protected open fun onAlertSelectorClicked() {
        LMFeedReasonChooseBottomSheetFragment.newInstance(childFragmentManager)
    }

    protected open fun onDeleteAlertPositiveButtonClicked() {
        binding.apply {
            val data = reasonData ?: return
            val tag = data.value
            val reason = if (tag == "Others") {
                alertDialogDelete.getAlertInputReason()
            } else {
                tag
            }

            if (reason.isEmpty()) {
                return
            }

            deleteDialogListener?.onEntityDeletedByAdmin(deleteExtras, reason)
            dismiss()
        }
    }

    protected open fun onDeleteAlertNegativeButtonClicked() {
        dismiss()
    }

    override fun onReasonSelected(position: Int, reasonViewData: LMFeedReasonChooseViewData) {
        binding.apply {
            reasonData = reasonViewData
            alertDialogDelete.setAlertSelectorText(reasonViewData.value)
            alertDialogDelete.etReason.setText("")

            if (reasonViewData.value == "Others") {
                alertDialogDelete.setAlertInputReasonVisibility(true)
                alertDialogDelete.setPositiveButtonEnabled(false)
            } else {
                alertDialogDelete.setAlertInputReasonVisibility(false)
                alertDialogDelete.setPositiveButtonEnabled(true)
            }
        }
    }
}

interface LMFeedDeleteDialogListener {
    fun onEntityDeletedByAdmin(deleteExtras: LMFeedDeleteExtras, reason: String)
}