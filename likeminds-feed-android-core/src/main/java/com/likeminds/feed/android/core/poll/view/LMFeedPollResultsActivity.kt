package com.likeminds.feed.android.core.poll.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.likeminds.feed.android.core.databinding.LmFeedActivityActivityFeedBinding

class LMFeedPollResultsActivity : AppCompatActivity() {

    private lateinit var binding: LmFeedActivityActivityFeedBinding

    companion object {
        @JvmStatic
        fun start(context: Context) {
            val intent = Intent(context, LMFeedPollResultsActivity::class.java)
            context.startActivity(intent)
        }

        @JvmStatic
        fun getIntent(context: Context): Intent {
            return Intent(context, LMFeedPollResultsActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = LmFeedActivityActivityFeedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //inflates poll results fragment
        inflatePollResultsFragment()
    }

    private fun inflatePollResultsFragment() {
        //gets poll results fragment instance

    }
}