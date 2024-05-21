package com.likeminds.feed.android.core.poll.create.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.likeminds.feed.android.core.databinding.LmFeedActivityPollResultsBinding
import com.likeminds.feed.android.core.poll.result.model.LMFeedPollViewData
import com.likeminds.feed.android.core.utils.LMFeedExtrasUtil

class LMFeedCreatePollActivity : AppCompatActivity() {

    private lateinit var binding: LmFeedActivityPollResultsBinding
    private var poll: LMFeedPollViewData? = null

    companion object {
        const val TAG = "LMFeedCreatePollActivity"
        const val LM_FEED_CREATE_POLL_BUNDLE = "LM_FEED_CREATE_POLL_BUNDLE"
        const val LM_FEED_CREATE_POLL_EXTRAS = "LM_FEED_CREATE_POLL_EXTRAS"

        @JvmStatic
        fun start(context: Context, poll: LMFeedPollViewData? = null) {
            val intent = Intent(context, LMFeedCreatePollActivity::class.java)
            val bundle = Bundle()
            bundle.putParcelable(LM_FEED_CREATE_POLL_EXTRAS, poll)
            intent.putExtra(LM_FEED_CREATE_POLL_BUNDLE, bundle)
            context.startActivity(intent)
        }

        @JvmStatic
        fun getIntent(context: Context, poll: LMFeedPollViewData? = null): Intent {
            val intent = Intent(context, LMFeedCreatePollActivity::class.java)
            val bundle = Bundle()
            bundle.putParcelable(LM_FEED_CREATE_POLL_EXTRAS, poll)
            intent.putExtra(LM_FEED_CREATE_POLL_BUNDLE, bundle)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //assign binding
        binding = LmFeedActivityPollResultsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //parse extras
        assignExtras()
    }

    private fun assignExtras() {
        //get bundle
        val bundle = intent.getBundleExtra(LM_FEED_CREATE_POLL_BUNDLE)

        //assign to global variable
        if (bundle != null) {
            poll = LMFeedExtrasUtil.getParcelable(
                bundle,
                LM_FEED_CREATE_POLL_EXTRAS,
                LMFeedPollViewData::class.java
            )
        } else {
            poll = null
        }
    }
}