package com.likeminds.feed.android.core.post.edit.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.databinding.LmFeedActivityEditPostBinding
import com.likeminds.feed.android.core.post.edit.model.LMFeedEditPostExtras
import com.likeminds.feed.android.core.utils.LMFeedExtrasUtil
import com.likeminds.feed.android.core.utils.LMFeedViewUtils

class LMFeedEditPostActivity : AppCompatActivity() {

    private lateinit var binding: LmFeedActivityEditPostBinding

    private var editPostExtras: LMFeedEditPostExtras? = null

    //Navigation
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    companion object {
        const val LM_FEED_EDIT_POST_EXTRAS = "LM_FEED_EDIT_POST_EXTRAS"
        const val LM_FEED_EDIT_POST_BUNDLE = "lm_feed_bundle"

        @JvmStatic
        fun start(context: Context, extras: LMFeedEditPostExtras) {
            val intent = Intent(context, LMFeedEditPostActivity::class.java)
            val bundle = Bundle()
            bundle.putParcelable(LM_FEED_EDIT_POST_EXTRAS, extras)
            intent.putExtra(LM_FEED_EDIT_POST_BUNDLE, bundle)
            context.startActivity(intent)
        }

        @JvmStatic
        fun getIntent(context: Context, extras: LMFeedEditPostExtras): Intent {
            val intent = Intent(context, LMFeedEditPostActivity::class.java)
            val bundle = Bundle()
            bundle.putParcelable(LM_FEED_EDIT_POST_EXTRAS, extras)
            intent.putExtra(LM_FEED_EDIT_POST_BUNDLE, bundle)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = LmFeedActivityEditPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.getBundleExtra(LM_FEED_EDIT_POST_BUNDLE)

        if (bundle != null) {
            editPostExtras = LMFeedExtrasUtil.getParcelable(
                bundle,
                LM_FEED_EDIT_POST_EXTRAS,
                LMFeedEditPostExtras::class.java
            )

            val args = Bundle().apply {
                putParcelable(LM_FEED_EDIT_POST_EXTRAS, editPostExtras)
            }

            //Navigation
            navHostFragment =
                supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            navController = navHostFragment.navController
            navController.setGraph(R.navigation.lm_feed_nav_graph_edit_post, args)
        } else {
            redirectActivity(true)
        }
    }

    private fun redirectActivity(isError: Boolean) {
        if (isError) {
            LMFeedViewUtils.showSomethingWentWrongToast(this)
        }
        supportFragmentManager.popBackStack()
        onBackPressedDispatcher.onBackPressed()
        overridePendingTransition(R.anim.lm_feed_slide_from_left, R.anim.lm_feed_slide_to_right)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}