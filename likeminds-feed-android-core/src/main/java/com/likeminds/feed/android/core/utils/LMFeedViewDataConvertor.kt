package com.likeminds.feed.android.core.utils

import com.likeminds.feed.android.core.overflowmenu.model.LMFeedOverflowMenuItemViewData
import com.likeminds.feed.android.core.post.detail.model.LMFeedCommentViewData
import com.likeminds.feed.android.core.post.detail.model.LMFeedCommentsCountViewData
import com.likeminds.feed.android.core.post.model.*
import com.likeminds.feed.android.core.topics.model.LMFeedTopicViewData
import com.likeminds.feed.android.core.universalfeed.model.*
import com.likeminds.feed.android.core.utils.user.LMFeedUserViewData
import com.likeminds.likemindsfeed.comment.model.Comment
import com.likeminds.likemindsfeed.post.model.*
import com.likeminds.likemindsfeed.sdk.model.SDKClientInfo
import com.likeminds.likemindsfeed.sdk.model.User
import com.likeminds.likemindsfeed.topic.model.Topic

object LMFeedViewDataConvertor {

    /**--------------------------------
     * Network Model -> View Data Model
    --------------------------------*/

    // converts response of the universal feed post to list of LMFeedPostViewData
    fun convertUniversalFeedPosts(
        posts: List<Post>,
        usersMap: Map<String, User>,
        topicsMap: Map<String, Topic>
    ): List<LMFeedPostViewData> {
        return posts.map { post ->
            convertPost(post, usersMap, topicsMap)
        }
    }

    /**
     * converts [Post] which is network model to [LMFeedPostViewData] which is view data model
     * @param post: [Post] instance
     * @param usersMap: Map of [String, User]
     * @param topicsMap: Map of [String, Topic]
     */
    fun convertPost(
        post: Post,
        usersMap: Map<String, User>,
        topicsMap: Map<String, Topic>
    ): LMFeedPostViewData {
        val postCreatorUUID = post.uuid
        val postCreator = usersMap[postCreatorUUID]
        val postId = post.id
        val replies = post.replies?.toMutableList()
        val topicsIds = post.topicIds ?: emptyList()

        //get user view data
        val userViewData = if (postCreator == null) {
            createDeletedUser()
        } else {
            convertUser(postCreator)
        }

        //get topics view data
        val topicsViewData = topicsIds.mapNotNull { topicId ->
            topicsMap[topicId]
        }.map { topic ->
            convertTopic(topic)
        }

        //post header view data
        val postHeaderViewData = LMFeedPostHeaderViewData.Builder()
            .isEdited(post.isEdited)
            .isPinned(post.isPinned)
            .user(userViewData)
            .userId(postCreatorUUID)
            .createdAt(post.createdAt)
            .updatedAt(post.updatedAt)
            .menuItems(convertOverflowMenuItems(post.menuItems))
            .build()


        //post content view data
        val postContentViewData = LMFeedPostContentViewData.Builder()
            .text(post.text)
            .build()

        //post media view data
        val postMediaViewData = LMFeedMediaViewData.Builder()
            .attachments(convertAttachments(post.attachments, postId))
            .build()

        //post footer view data
        val postFooterViewData = LMFeedPostFooterViewData.Builder()
            .likesCount(post.likesCount)
            .commentsCount(post.commentsCount)
            .isSaved(post.isSaved)
            .isLiked(post.isLiked)
            .replies(
                convertComments(
                    replies,
                    usersMap,
                    postId
                )
            )
            .build()

        //creating a final instance
        return LMFeedPostViewData.Builder()
            .id(postId)
            .headerViewData(postHeaderViewData)
            .contentViewData(postContentViewData)
            .mediaViewData(postMediaViewData)
            .footerViewData(postFooterViewData)
            .topicsViewData(topicsViewData)
            .build()
    }

    /**
     * Converts [User] which is a network model to [LMFeedUserViewData] which is view data model
     * @param user: instance of [User] | nullable
     */
    fun convertUser(
        user: User?
    ): LMFeedUserViewData {
        if (user == null) return LMFeedUserViewData.Builder().build()
        return LMFeedUserViewData.Builder()
            .id(user.id)
            .name(user.name)
            .imageUrl(user.imageUrl)
            .userUniqueId(user.userUniqueId)
            .customTitle(user.customTitle)
            .isGuest(user.isGuest)
            .isDeleted(user.isDeleted)
            .uuid(user.uuid)
            .sdkClientInfoViewData(convertSDKClientInfo(user.sdkClientInfo))
            .build()
    }

    /**
     * Converts [SDKClientInfo] which is a network model to [LMFeedSDKClientInfoViewData] which is view data model
     */
    fun convertSDKClientInfo(sdkClientInfo: SDKClientInfo): LMFeedSDKClientInfoViewData {
        return LMFeedSDKClientInfoViewData.Builder()
            .user(sdkClientInfo.user)
            .uuid(sdkClientInfo.uuid)
            .userUniqueId(sdkClientInfo.userUniqueId)
            .community(sdkClientInfo.community)
            .build()
    }

    //created a deleted user object
    private fun createDeletedUser(): LMFeedUserViewData {
        val tempUserId = (System.currentTimeMillis() / 1000).toInt()
        return LMFeedUserViewData.Builder()
            .id(tempUserId)
            .name("Deleted User")
            .imageUrl("")
            .userUniqueId("$tempUserId")
            .customTitle(null)
            .isGuest(false)
            .isDeleted(true)
            .build()
    }

    /**
     * convert list of [MenuItem] to [LMFeedOverflowMenuItemViewData]
     * @param menuItems: list of [MenuItem]
     * */
    private fun convertOverflowMenuItems(
        menuItems: List<MenuItem>
    ): List<LMFeedOverflowMenuItemViewData> {
        return menuItems.map { menuItem ->
            LMFeedOverflowMenuItemViewData.Builder()
                .id(menuItem.id)
                .title(menuItem.title)
                .build()
        }
    }

    /**
     * converts list of [Attachment] to list of [LMFeedAttachmentViewData]
     * @param attachments: list of [Attachment]
     * @param postId: id of the post
     */
    fun convertAttachments(
        attachments: List<Attachment>?,
        postId: String
    ): List<LMFeedAttachmentViewData> {
        if (attachments == null) return emptyList()
        return attachments.map { attachment ->
            LMFeedAttachmentViewData.Builder()
                .attachmentType(attachment.attachmentType)
                .attachmentMeta(convertAttachmentMeta(attachment.attachmentMeta))
                .postId(postId)
                .build()
        }
    }

    /**
     * converts list of [AttachmentMeta] to list of [LMFeedAttachmentMetaViewData]
     * @param attachmentMeta: instance of [AttachmentMeta]
     */
    private fun convertAttachmentMeta(attachmentMeta: AttachmentMeta?): LMFeedAttachmentMetaViewData {
        if (attachmentMeta == null) {
            return LMFeedAttachmentMetaViewData.Builder().build()
        }

        return LMFeedAttachmentMetaViewData.Builder()
            .name(attachmentMeta.name)
            .url(attachmentMeta.url)
            .format(attachmentMeta.format)
            .size(attachmentMeta.size)
            .duration(attachmentMeta.duration)
            .pageCount(attachmentMeta.pageCount)
            .ogTags(convertLinkOGTags(attachmentMeta.ogTags))
            .build()
    }

    /**
     * convert [LinkOGTags] to [LMFeedLinkOGTagsViewData]
     * @param linkOGTags: object of [LinkOGTags]
     **/
    fun convertLinkOGTags(linkOGTags: LinkOGTags): LMFeedLinkOGTagsViewData {
        return LMFeedLinkOGTagsViewData.Builder()
            .url(linkOGTags.url)
            .description(linkOGTags.description)
            .title(linkOGTags.title)
            .image(linkOGTags.image)
            .build()
    }

    /**
     * convert [Topic] which is a network model to [LMFeedTopicViewData] which is view data model
     * */
    fun convertTopic(topic: Topic): LMFeedTopicViewData {
        return LMFeedTopicViewData.Builder()
            .id(topic.id)
            .name(topic.name)
            .isEnabled(topic.isEnabled)
            .isSelected(false)
            .build()
    }

    /**
     * convert list of [Comment] and usersMap [Map] of String to User
     * to list of [CommentViewData]
     *
     * @param comments: list of [Comment]
     * @param usersMap: [Map] of String to User
     * @param postId: postId of post
     * */
    private fun convertComments(
        comments: MutableList<Comment>?,
        usersMap: Map<String, User>,
        postId: String,
        parentCommentId: String? = null
    ): MutableList<LMFeedCommentViewData> {
        if (comments == null) return mutableListOf()
        return comments.map { comment ->
            convertComment(
                comment,
                usersMap,
                postId,
                parentCommentId
            )
        }.toMutableList()
    }

    /**
     * converts [Comment] which is network model to [LMFeedCommentViewData]
     * @param postId: id of the post
     * @param usersMap: Map of [String, User]
     * @param parentCommentId: Id of the comment to which the user has replied
     */
    fun convertComment(
        comment: Comment,
        usersMap: Map<String, User>,
        postId: String,
        parentCommentId: String? = null
    ): LMFeedCommentViewData {
        val commentCreator = comment.uuid
        val user = usersMap[commentCreator]
        val replies = comment.replies?.toMutableList()
        val parentId = parentCommentId ?: comment.parentComment?.id

        val userViewData = if (user == null) {
            createDeletedUser()
        } else {
            convertUser(user)
        }

        return LMFeedCommentViewData.Builder()
            .id(comment.id)
            .postId(postId)
            .isLiked(comment.isLiked)
            .isEdited(comment.isEdited)
            .userId(commentCreator)
            .text(comment.text)
            .level(comment.level)
            .likesCount(comment.likesCount)
            .repliesCount(comment.commentsCount)
            .user(userViewData)
            .createdAt(comment.createdAt)
            .updatedAt(comment.updatedAt)
            .menuItems(convertOverflowMenuItems(comment.menuItems))
            .replies(
                convertComments(
                    replies,
                    usersMap,
                    postId,
                    comment.id
                )
            )
            .parentId(parentId)
            .parentComment(
                comment.parentComment?.let {
                    convertComment(
                        it,
                        usersMap,
                        postId
                    )
                }
            )
            .uuid(commentCreator)
            .tempId(comment.tempId)
            .build()
    }

    fun convertCommentsCount(commentsCount: Int): LMFeedCommentsCountViewData {
        return LMFeedCommentsCountViewData.Builder()
            .commentsCount(commentsCount)
            .build()
    }
}