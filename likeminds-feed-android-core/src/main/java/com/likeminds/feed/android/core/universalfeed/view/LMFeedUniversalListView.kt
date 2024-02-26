package com.likeminds.feed.android.core.universalfeed.view

import android.content.Context
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.*
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.post.model.*
import com.likeminds.feed.android.core.universalfeed.adapter.LMFeedUniversalFeedAdapter
import com.likeminds.feed.android.core.universalfeed.adapter.LMFeedUniversalFeedAdapterListener
import com.likeminds.feed.android.core.universalfeed.model.*
import com.likeminds.feed.android.core.utils.LMFeedViewUtils

class LMFeedUniversalListView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    private val linearLayoutManager: LinearLayoutManager
    private val dividerDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)

    private lateinit var universalFeedAdapter: LMFeedUniversalFeedAdapter

    val itemCount: Int get() = universalFeedAdapter.itemCount

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

        addItemDecoration(dividerDecoration)
    }

    fun setAdapter(
        listener: LMFeedUniversalFeedAdapterListener
    ) {
        universalFeedAdapter = LMFeedUniversalFeedAdapter(listener)
        adapter = universalFeedAdapter
    }

    fun replace(feed: List<LMFeedPostViewData>) {
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
                    .build(),
                LMFeedPostViewData.Builder()
                    .id("post-2")
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
                            .text("Customizable UI dsf sdfsdf sdf s f sd\n \n aserdasdg \n aseara \nsdfsd\n")
                            .alreadySeenFullContent(false)
                            .build()
                    )
                    .mediaViewData(
                        LMFeedMediaViewData.Builder()
                            .attachments(
                                listOf(
                                    LMFeedAttachmentViewData.Builder()
                                        .attachmentType(DOCUMENT)
                                        .attachmentMeta(
                                            LMFeedAttachmentMetaViewData.Builder()
                                                .name("Doc-1")
                                                .size(10000)
                                                .pageCount(10)
                                                .build()
                                        )
                                        .postId("post-2")
                                        .build(),
                                    LMFeedAttachmentViewData.Builder()
                                        .attachmentType(DOCUMENT)
                                        .attachmentMeta(
                                            LMFeedAttachmentMetaViewData.Builder()
                                                .name("Doc-1")
                                                .size(10000)
                                                .pageCount(10)
                                                .build()
                                        )
                                        .postId("post-2")
                                        .build(),
                                    LMFeedAttachmentViewData.Builder()
                                        .attachmentType(DOCUMENT)
                                        .attachmentMeta(
                                            LMFeedAttachmentMetaViewData.Builder()
                                                .name("Doc-1")
                                                .size(10000)
                                                .pageCount(10)
                                                .build()
                                        )
                                        .postId("post-2")
                                        .build(),
                                    LMFeedAttachmentViewData.Builder()
                                        .attachmentType(DOCUMENT)
                                        .attachmentMeta(
                                            LMFeedAttachmentMetaViewData.Builder()
                                                .name("Doc-1")
                                                .size(10000)
                                                .pageCount(10)
                                                .build()
                                        )
                                        .postId("post-2")
                                        .build(),
                                    LMFeedAttachmentViewData.Builder()
                                        .attachmentType(DOCUMENT)
                                        .attachmentMeta(
                                            LMFeedAttachmentMetaViewData.Builder()
                                                .name("Doc-1")
                                                .size(10000)
                                                .pageCount(10)
                                                .build()
                                        )
                                        .postId("post-2")
                                        .build()
                                )
                            )
                            .isExpanded(false)
                            .build()
                    )
                    .build(),
                LMFeedPostViewData.Builder()
                    .id("post-3")
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
                            .text("Customizable UI dsf sdfsdf sdf s f sd\n \n aserdasdg \n aseara \nsdfsd\n")
                            .alreadySeenFullContent(false)
                            .build()
                    )
                    .mediaViewData(
                        LMFeedMediaViewData.Builder()
                            .attachments(
                                listOf(
                                    LMFeedAttachmentViewData.Builder()
                                        .attachmentType(IMAGE)
                                        .attachmentMeta(
                                            LMFeedAttachmentMetaViewData.Builder()
                                                .name("Image-1")
                                                .size(10000)
                                                .url("https://cdn.pixabay.com/photo/2022/01/28/18/32/leaves-6975462_1280.png")
                                                .build()
                                        )
                                        .postId("post-2")
                                        .build()
                                )
                            )
                            .isExpanded(false)
                            .build()
                    )
                    .build(),
                LMFeedPostViewData.Builder()
                    .id("post-4")
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
                            .text("Customizable UI dsf sdfsdf sdf s f sd\n \n aserdasdg \n aseara \nsdfsd\n")
                            .alreadySeenFullContent(false)
                            .build()
                    )
                    .mediaViewData(
                        LMFeedMediaViewData.Builder()
                            .attachments(
                                listOf(
                                    LMFeedAttachmentViewData.Builder()
                                        .attachmentType(IMAGE)
                                        .attachmentMeta(
                                            LMFeedAttachmentMetaViewData.Builder()
                                                .name("Image-1")
                                                .size(10000)
                                                .url("https://cdn.pixabay.com/photo/2022/01/28/18/32/leaves-6975462_1280.png")
                                                .build()
                                        )
                                        .postId("post-2")
                                        .build(),
                                    LMFeedAttachmentViewData.Builder()
                                        .attachmentType(IMAGE)
                                        .attachmentMeta(
                                            LMFeedAttachmentMetaViewData.Builder()
                                                .name("Image-2")
                                                .size(10000)
                                                .url("https://www.shutterstock.com/image-vector/sample-red-square-grunge-stamp-260nw-338250266.jpg")
                                                .build()
                                        )
                                        .postId("post-2")
                                        .build(),
                                    LMFeedAttachmentViewData.Builder()
                                        .attachmentType(VIDEO)
                                        .attachmentMeta(
                                            LMFeedAttachmentMetaViewData.Builder()
                                                .name("Image-1")
                                                .size(10000)
                                                .url("https://storage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4")
                                                .build()
                                        )
                                        .postId("post-2")
                                        .build(),
                                    LMFeedAttachmentViewData.Builder()
                                        .attachmentType(IMAGE)
                                        .attachmentMeta(
                                            LMFeedAttachmentMetaViewData.Builder()
                                                .name("Image-3")
                                                .size(10000)
                                                .url("https://images.unsplash.com/photo-1579353977828-2a4eab540b9a?q=80&w=1000&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8c2FtcGxlfGVufDB8fDB8fHww")
                                                .build()
                                        )
                                        .postId("post-2")
                                        .build()
                                )
                            )
                            .isExpanded(false)
                            .build()
                    )
                    .build(),
                LMFeedPostViewData.Builder()
                    .id("post-2")
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
                            .text("Customizable UI dsf sdfsdf sdf s f sd\n \n aserdasdg \n aseara \nsdfsd\n")
                            .alreadySeenFullContent(false)
                            .build()
                    )
                    .mediaViewData(
                        LMFeedMediaViewData.Builder()
                            .attachments(
                                listOf(
                                    LMFeedAttachmentViewData.Builder()
                                        .attachmentType(VIDEO)
                                        .attachmentMeta(
                                            LMFeedAttachmentMetaViewData.Builder()
                                                .name("Image-1")
                                                .size(10000)
                                                .url("https://storage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4")
                                                .build()
                                        )
                                        .postId("post-2")
                                        .build()
                                )
                            )
                            .isExpanded(false)
                            .build()
                    )
                    .build()
            )
        )
    }

    /**
     * Scroll to a position with offset from the top header
     * @param position Index of the item to scroll to
     */
    fun scrollToPositionWithOffset(position: Int) {
        post {
            val px = (LMFeedViewUtils.dpToPx(75) * 1.5).toInt()
            (layoutManager as? LinearLayoutManager)?.scrollToPositionWithOffset(
                position,
                px
            )
        }
    }

    fun update(position: Int, postItem: LMFeedPostViewData) {
        universalFeedAdapter.update(position, postItem)
    }
}