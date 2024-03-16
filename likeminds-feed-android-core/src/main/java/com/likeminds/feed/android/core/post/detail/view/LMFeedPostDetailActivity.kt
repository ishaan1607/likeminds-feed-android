package com.likeminds.feed.android.core.post.detail.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.databinding.LmFeedActivityPostDetailBinding
import com.likeminds.feed.android.core.post.detail.model.LMFeedPostDetailExtras
import com.likeminds.feed.android.core.utils.LMFeedExtrasUtil
import com.likeminds.feed.android.core.utils.LMFeedViewUtils

class LMFeedPostDetailActivity : AppCompatActivity() {

    private lateinit var binding: LmFeedActivityPostDetailBinding

    private var postDetailExtras: LMFeedPostDetailExtras? = null

    //Navigation
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    companion object {
        const val LM_FEED_POST_DETAIL_EXTRAS = "LM_FEED_POST_DETAIL_EXTRAS"
        const val LM_FEED_POST_DETAIL_BUNDLE = "lm_feed_bundle"

        @JvmStatic
        fun start(context: Context, extras: LMFeedPostDetailExtras) {
            val intent = Intent(context, LMFeedPostDetailActivity::class.java)
            val bundle = Bundle()
            bundle.putParcelable(LM_FEED_POST_DETAIL_EXTRAS, extras)
            intent.putExtra(LM_FEED_POST_DETAIL_BUNDLE, bundle)
            context.startActivity(intent)
        }

        @JvmStatic
        fun getIntent(context: Context, extras: LMFeedPostDetailExtras): Intent {
            val intent = Intent(context, LMFeedPostDetailActivity::class.java)
            val bundle = Bundle()
            bundle.putParcelable(LM_FEED_POST_DETAIL_EXTRAS, extras)
            intent.putExtra(LM_FEED_POST_DETAIL_BUNDLE, bundle)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = LmFeedActivityPostDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.getBundleExtra(LM_FEED_POST_DETAIL_BUNDLE)

        if (bundle != null) {
            postDetailExtras = LMFeedExtrasUtil.getParcelable(
                bundle,
                LM_FEED_POST_DETAIL_EXTRAS,
                LMFeedPostDetailExtras::class.java
            )
            val args = Bundle().apply {
                putParcelable(LM_FEED_POST_DETAIL_EXTRAS, postDetailExtras)
            }

            //Navigation
            navHostFragment =
                supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            navController = navHostFragment.navController
            navController.setGraph(R.navigation.lm_feed_nav_graph_post_detail, args)
        } else {
            redirectActivity()
        }
    }

    private fun redirectActivity() {
        LMFeedViewUtils.showShortToast(this, getString(R.string.lm_feed_request_not_processed))
        supportFragmentManager.popBackStack()
        onBackPressedDispatcher.onBackPressed()
        overridePendingTransition(R.anim.lm_feed_slide_from_left, R.anim.lm_feed_slide_to_right)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}