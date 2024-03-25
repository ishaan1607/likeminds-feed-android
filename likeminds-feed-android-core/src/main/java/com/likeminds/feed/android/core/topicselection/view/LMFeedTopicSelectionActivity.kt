package com.likeminds.feed.android.core.topicselection.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.databinding.LmFeedActivityTopicSelectionBinding
import com.likeminds.feed.android.core.topicselection.model.LMFeedTopicSelectionExtras
import com.likeminds.feed.android.core.utils.LMFeedExtrasUtil
import com.likeminds.feed.android.core.utils.emptyExtrasException

class LMFeedTopicSelectionActivity : AppCompatActivity() {

    private lateinit var binding: LmFeedActivityTopicSelectionBinding

    private lateinit var topicSelectionExtras: LMFeedTopicSelectionExtras

    //Navigation
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    companion object {
        const val TAG = "LMFeedTopicSelectionActivity"
        const val LM_FEED_TOPIC_SELECTION_EXTRAS = "LM_FEED_TOPIC_SELECTION_EXTRAS"
        private const val LM_FEED_TOPIC_SELECTION_BUNDLE = "lm_feed_bundle"
        const val LM_FEED_TOPIC_SELECTION_RESULT_EXTRAS = "LM_FEED_TOPIC_SELECTION_RESULT_EXTRAS"

        @JvmStatic
        fun start(context: Context, extras: LMFeedTopicSelectionExtras) {
            val intent = Intent(context, LMFeedTopicSelectionActivity::class.java)
            val bundle = Bundle()
            bundle.putParcelable(LM_FEED_TOPIC_SELECTION_EXTRAS, extras)
            intent.putExtra(LM_FEED_TOPIC_SELECTION_BUNDLE, bundle)
            context.startActivity(intent)
        }

        @JvmStatic
        fun getIntent(context: Context, extras: LMFeedTopicSelectionExtras): Intent {
            val intent = Intent(context, LMFeedTopicSelectionActivity::class.java)
            val bundle = Bundle()
            bundle.putParcelable(LM_FEED_TOPIC_SELECTION_EXTRAS, extras)
            intent.putExtra(LM_FEED_TOPIC_SELECTION_BUNDLE, bundle)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = LmFeedActivityTopicSelectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.getBundleExtra(LM_FEED_TOPIC_SELECTION_BUNDLE)

        bundle?.let {
            topicSelectionExtras = LMFeedExtrasUtil.getParcelable(
                it,
                LM_FEED_TOPIC_SELECTION_EXTRAS,
                LMFeedTopicSelectionExtras::class.java
            ) ?: throw emptyExtrasException(TAG)

            val args = Bundle().apply {
                putParcelable(LM_FEED_TOPIC_SELECTION_EXTRAS, topicSelectionExtras)
            }

            //Navigation
            navHostFragment =
                supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            navController = navHostFragment.navController
            navController.setGraph(R.navigation.lm_feed_nav_graph_topic_selection, args)
        }
    }
}