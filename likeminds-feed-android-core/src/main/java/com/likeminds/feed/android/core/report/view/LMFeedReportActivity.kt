package com.likeminds.feed.android.core.report.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.databinding.LmFeedActivityReportBinding
import com.likeminds.feed.android.core.report.model.LMFeedReportExtras
import com.likeminds.feed.android.core.utils.*

class LMFeedReportActivity : AppCompatActivity() {

    private lateinit var binding: LmFeedActivityReportBinding

    private lateinit var reportExtras: LMFeedReportExtras

    //Navigation
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    companion object {
        const val LM_FEED_REPORT_EXTRAS = "LM_FEED_REPORT_EXTRAS"
        const val LM_FEED_REPORT_BUNDLE = "LM_FEED_REPORT_BUNDLE"
        const val TAG = "LMFeedReportActivity"

        @JvmStatic
        fun start(context: Context, extras: LMFeedReportExtras) {
            val intent = Intent(context, LMFeedReportActivity::class.java)
            val bundle = Bundle()
            bundle.putParcelable(LM_FEED_REPORT_EXTRAS, extras)
            intent.putExtra(LM_FEED_REPORT_BUNDLE, bundle)
            context.startActivity(intent)
        }

        @JvmStatic
        fun getIntent(context: Context, extras: LMFeedReportExtras): Intent {
            val intent = Intent(context, LMFeedReportActivity::class.java)
            val bundle = Bundle()
            bundle.putParcelable(LM_FEED_REPORT_EXTRAS, extras)
            intent.putExtra(LM_FEED_REPORT_BUNDLE, bundle)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LmFeedActivityReportBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.getBundleExtra(LM_FEED_REPORT_BUNDLE)

        if (bundle != null) {
            reportExtras = LMFeedExtrasUtil.getParcelable(
                bundle,
                LM_FEED_REPORT_EXTRAS,
                LMFeedReportExtras::class.java
            ) ?: throw emptyExtrasException(TAG)

            val args = Bundle().apply {
                putParcelable(LM_FEED_REPORT_EXTRAS, reportExtras)
            }

            //Navigation
            navHostFragment =
                supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            navController = navHostFragment.navController
            navController.setGraph(R.navigation.lm_feed_nav_graph_report, args)
        } else {
            redirectActivity()
        }
    }

    private fun redirectActivity() {
        LMFeedViewUtils.showSomethingWentWrongToast(this)
        supportFragmentManager.popBackStack()
        onBackPressedDispatcher.onBackPressed()
        overridePendingTransition(R.anim.lm_feed_slide_from_left, R.anim.lm_feed_slide_to_right)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}