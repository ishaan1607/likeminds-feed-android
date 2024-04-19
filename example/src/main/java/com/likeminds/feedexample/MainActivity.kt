package com.likeminds.feedexample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.likeminds.feed.android.core.LMFeedCore
import com.likeminds.feedexample.auth.util.AuthPreferences
import com.likeminds.feedexample.universalfeed.CustomLMUniversalFeedAdminFragment

class MainActivity : AppCompatActivity() {

    private lateinit var authPreferences: AuthPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        authPreferences = AuthPreferences(this)

        LMFeedCore.initiateUser(
            this,
            authPreferences.getApiKey(),
            authPreferences.getUserName(),
            authPreferences.getUserId(),
            authPreferences.getDeviceId(),
            success = {
                replaceFragment()
            },
            error = {
                Log.d("Example", "$it")
            }
        )
    }

    private fun replaceFragment() {
        val containerViewId = R.id.frame_layout
        val fragment = CustomLMUniversalFeedAdminFragment()

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(containerViewId, fragment, containerViewId.toString())
        transaction.commit()
    }
}