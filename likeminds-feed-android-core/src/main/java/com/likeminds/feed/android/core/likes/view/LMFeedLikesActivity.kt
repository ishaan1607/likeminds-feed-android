package com.likeminds.feed.android.core.likes.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.databinding.LmFeedActivityLikesBinding
import com.likeminds.feed.android.core.likes.model.LMFeedLikesScreenExtras
import com.likeminds.feed.android.core.utils.*

class LMFeedLikesActivity : AppCompatActivity() {

    private lateinit var binding: LmFeedActivityLikesBinding

    private lateinit var likesScreenExtras: LMFeedLikesScreenExtras

    //Navigation
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    companion object {
        const val LM_FEED_LIKES_SCREEN_EXTRAS = "LM_FEED_LIKES_SCREEN_EXTRAS"
        private const val LM_FEED_LIKES_SCREEN_BUNDLE = "LM_FEED_LIKES_SCREEN_BUNDLE"
        const val TAG = "LMFeedLikesActivity"

        @JvmStatic
        fun start(context: Context, extras: LMFeedLikesScreenExtras) {
            val intent = Intent(context, LMFeedLikesActivity::class.java)
            val bundle = Bundle()
            bundle.putParcelable(LM_FEED_LIKES_SCREEN_EXTRAS, extras)
            intent.putExtra(LM_FEED_LIKES_SCREEN_BUNDLE, bundle)
            context.startActivity(intent)
        }

        @JvmStatic
        fun getIntent(context: Context, extras: LMFeedLikesScreenExtras): Intent {
            val intent = Intent(context, LMFeedLikesActivity::class.java)
            val bundle = Bundle()
            bundle.putParcelable(LM_FEED_LIKES_SCREEN_EXTRAS, extras)
            intent.putExtra(LM_FEED_LIKES_SCREEN_BUNDLE, bundle)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = LmFeedActivityLikesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.getBundleExtra(LM_FEED_LIKES_SCREEN_BUNDLE)

        if (bundle != null) {
            likesScreenExtras = LMFeedExtrasUtil.getParcelable(
                bundle,
                LM_FEED_LIKES_SCREEN_EXTRAS,
                LMFeedLikesScreenExtras::class.java
            ) ?: throw emptyExtrasException(TAG)

            val args = Bundle().apply {
                putParcelable(LM_FEED_LIKES_SCREEN_EXTRAS, likesScreenExtras)
            }

            //Navigation
            navHostFragment =
                supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            navController = navHostFragment.navController
            navController.setGraph(R.navigation.lm_feed_nav_graph_likes, args)
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