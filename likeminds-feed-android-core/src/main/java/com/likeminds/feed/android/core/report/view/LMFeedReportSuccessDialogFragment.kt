package com.likeminds.feed.android.core.report.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.databinding.LmFeedDialogFragmentReportSuccessBinding
import com.likeminds.feed.android.core.ui.widgets.alertdialog.view.LMFeedAlertDialogView
import com.likeminds.feed.android.core.utils.LMFeedStyleTransformer

open class LMFeedReportSuccessDialogFragment(
    private val type: String
) : DialogFragment() {

    companion object {
        const val TAG = "LMFeedReportSuccessDialog"
    }

    private lateinit var binding: LmFeedDialogFragmentReportSuccessBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LmFeedDialogFragmentReportSuccessBinding.inflate(layoutInflater)

        binding.apply {
            customizeReportSuccessDialog(alertDialogDelete)
            return root
        }
    }

    protected open fun customizeReportSuccessDialog(alertDialogDelete: LMFeedAlertDialogView) {
        val selfDeleteDialogFragmentStyle =
            LMFeedStyleTransformer.reportFragmentViewStyle.reportSuccessDialogFragmentStyle

        alertDialogDelete.setStyle(selfDeleteDialogFragmentStyle)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
    }

    //set title and sub title as per [type] received in constructor
    private fun initUI() {
        binding.alertDialogDelete.apply {
            setPositiveButtonEnabled(true)
            setAlertTitle(getString(R.string.lm_feed_s_is_reported_for_review, type))
            setAlertSubtitle(
                getString(
                    R.string.lm_feed_our_team_will_look_into_your_feedback_and_will_take_appropriate_action_on_this_s,
                    type
                )
            )
            setAlertPositiveButtonText(getString(R.string.lm_feed_okay))
            setPositiveButtonClickListener {
                dismiss()
            }
        }
    }
}