package com.likeminds.feed.android.core.activityfeed.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.activityfeed.model.LMFeedActivityEntityType
import com.likeminds.feed.android.core.databinding.LmFeedActivityActivityFeedBinding

class LMFeedActivityFeedActivity : AppCompatActivity() {

    private lateinit var binding: LmFeedActivityActivityFeedBinding

    //Navigation
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    companion object {
        @JvmStatic
        fun start(context: Context) {
            val intent = Intent(context, LMFeedActivityEntityType::class.java)
            context.startActivity(intent)
        }

        @JvmStatic
        fun getIntent(context: Context): Intent {
            return Intent(context, LMFeedActivityFeedActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = LmFeedActivityActivityFeedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Navigation
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        navController.setGraph(R.navigation.lm_feed_nav_graph_activity_feed)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}