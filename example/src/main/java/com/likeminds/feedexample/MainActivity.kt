package com.likeminds.feedexample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.likeminds.feedexample.universalfeed.CustomLMUniversalFeedFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val containerViewId = R.id.frame_layout
        val fragment = CustomLMUniversalFeedFragment()

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(containerViewId, fragment, containerViewId.toString())
        Log.d("PUI", "showing feed")
        transaction.commit()
    }
}