package com.likeminds.feed.android.core.universalfeed.view

import android.content.Context
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.*
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.universalfeed.adapter.LMFeedUniversalFeedAdapter
import com.likeminds.feed.android.core.universalfeed.adapter.LMFeedUniversalFeedAdapterListener
import com.likeminds.feed.android.core.universalfeed.model.*

class LMFeedUniversalListView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    private val linearLayoutManager: LinearLayoutManager
    private val dividerDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)

    private lateinit var universalFeedAdapter: LMFeedUniversalFeedAdapter

    init {
        setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(context)
        layoutManager = linearLayoutManager

        if (itemAnimator is SimpleItemAnimator)
            (itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false

        //item decorator to add spacing between items
        val dividerDrawable = ContextCompat.getDrawable(
            context,
            R.drawable.lm_feed_item_divider
        )
        dividerDrawable?.let { drawable ->
            dividerDecoration.setDrawable(drawable)
        }
    }

    fun setAdapter(
        listener: LMFeedUniversalFeedAdapterListener
    ) {
        universalFeedAdapter = LMFeedUniversalFeedAdapter(listener)
        adapter = universalFeedAdapter

        universalFeedAdapter.replace(
            listOf(
                LMFeedPostViewData.Builder()
                    .id("post-1")
                    .headerViewData(
                        LMFeedPostHeaderViewData.Builder()
                            .isEdited(true)
                            .isPinned(true)
                            .userId("user-1")
                            .user(
                                LMFeedUserViewData.Builder()
                                    .id(1)
                                    .name("Shubh Gupta")
                                    .imageUrl("https://images.unsplash.com/photo-1605559911160-a3d95d213904?q=80&w=1000&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxleHBsb3JlLWZlZWR8Mnx8fGVufDB8fHx8fA%3D%3D")
                                    .userUniqueId("user-1")
//                                    .customTitle("Community Manager")
                                    .isGuest(false)
                                    .isDeleted(false)
                                    .sdkClientInfoViewData(
                                        LMFeedSDKClientInfoViewData.Builder()
                                            .community(1)
                                            .user(1)
                                            .userUniqueId("user-1")
                                            .uuid("user-1")
                                            .build()
                                    )
                                    .uuid("user-1")
                                    .build()
                            )
                            .build()
                    )
                    .contentViewData(
                        LMFeedPostContentViewData.Builder()
                            .text("Customizable UI")
                            .alreadySeenFullContent(false)
                            .build()
                    )
                    .build()
            )
        )
    }
}