package com.likeminds.feed.android.core.utils

import android.util.Base64
import com.likeminds.customgallery.media.model.SingleUriData
import com.likeminds.feed.android.core.activityfeed.model.LMFeedActivityEntityViewData
import com.likeminds.feed.android.core.activityfeed.model.LMFeedActivityViewData
import com.likeminds.feed.android.core.delete.model.LMFeedReasonChooseViewData
import com.likeminds.feed.android.core.likes.model.LMFeedLikeViewData
import com.likeminds.feed.android.core.overflowmenu.model.LMFeedOverflowMenuItemViewData
import com.likeminds.feed.android.core.post.create.model.LMFeedFileUploadViewData
import com.likeminds.feed.android.core.post.detail.model.LMFeedCommentViewData
import com.likeminds.feed.android.core.post.detail.model.LMFeedCommentsCountViewData
import com.likeminds.feed.android.core.post.model.*
import com.likeminds.feed.android.core.report.model.LMFeedReportTagViewData
import com.likeminds.feed.android.core.topics.model.LMFeedTopicViewData
import com.likeminds.feed.android.core.universalfeed.model.*
import com.likeminds.feed.android.core.utils.mediauploader.utils.LMFeedAWSKeys
import com.likeminds.feed.android.core.utils.user.LMFeedUserViewData
import com.likeminds.likemindsfeed.comment.model.Comment
import com.likeminds.likemindsfeed.moderation.model.ReportTag
import com.likeminds.likemindsfeed.notificationfeed.model.Activity
import com.likeminds.likemindsfeed.notificationfeed.model.ActivityEntityData
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
     * to list of [LMFeedCommentViewData]
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

    /**
     * convert list of [Activity] and usersMap [Map] of String to User
     * to list of [LMFeedActivityViewData]
     *
     * @param activities: list of [Activity]
     * @param usersMap: [Map] of String to User
     * */
    fun convertActivities(
        activities: List<Activity>,
        usersMap: Map<String, User>
    ): List<LMFeedActivityViewData> {
        return activities.map {
            convertActivity(it, usersMap)
        }
    }

    /**
     * converts [Activity] and usersMap [Map] of String to User
     * to [LMFeedActivityViewData]
     *
     * @param activity: an activity [LMFeedActivityViewData]
     * @param usersMap: [Map] of String to User
     * */
    private fun convertActivity(
        activity: Activity,
        usersMap: Map<String, User>
    ): LMFeedActivityViewData {
        val activityByUser = if (activity.actionBy.isNotEmpty()) {
            convertUser(usersMap[activity.actionBy.last()])
        } else {
            LMFeedUserViewData.Builder().build()
        }

        return LMFeedActivityViewData.Builder()
            .id(activity.id)
            .isRead(activity.isRead)
            .actionOn(activity.actionOn)
            .actionBy(activity.actionBy)
            .entityType(activity.entityType)
            .entityId(activity.entityId)
            .entityOwnerId(activity.entityOwnerId)
            .action(activity.action)
            .cta(activity.cta)
            .activityText(activity.activityText)
            .activityEntityData(
                convertActivityEntityData(
                    activity.activityEntityData,
                    usersMap
                )
            )
            .activityByUser(activityByUser)
            .createdAt(activity.createdAt)
            .updatedAt(activity.updatedAt)
            .uuid(activity.uuid)
            .build()
    }

    private fun convertActivityEntityData(
        activityEntityData: ActivityEntityData?,
        usersMap: Map<String, User>
    ): LMFeedActivityEntityViewData? {

        if (activityEntityData == null) {
            return null
        }
        val entityCreator = activityEntityData.uuid
        val user = usersMap[entityCreator]
        val replies = activityEntityData.replies?.toMutableList()

        val userViewData = if (user == null) {
            createDeletedUser()
        } else {
            convertUser(user)
        }

        return LMFeedActivityEntityViewData.Builder()
            .id(activityEntityData.id)
            .text(activityEntityData.text)
            .deleteReason(activityEntityData.deleteReason)
            .deletedBy(activityEntityData.deletedBy)
            .heading(activityEntityData.heading)
            .attachments(
                convertAttachments(
                    activityEntityData.attachments,
                    activityEntityData.id
                )
            )
            .communityId(activityEntityData.communityId)
            .isEdited(activityEntityData.isEdited)
            .isPinned(activityEntityData.isPinned)
            .userId(activityEntityData.userId)
            .user(userViewData)
            .replies(
                convertComments(
                    replies,
                    usersMap,
                    activityEntityData.postId ?: activityEntityData.id
                )
            )
            .level(activityEntityData.level)
            .createdAt(activityEntityData.createdAt)
            .updatedAt(activityEntityData.updatedAt)
            .uuid(activityEntityData.uuid)
            .deletedByUUID(activityEntityData.deletedByUUID)
            .build()
    }

    /**
     * convert list of [Like] to list of [LMFeedLikeViewData]
     * @param likes: list of [Like]
     * @param users: [Map] of String to User
     * */
    fun convertLikes(
        likes: List<Like>,
        users: Map<String, User>
    ): List<LMFeedLikeViewData> {
        return likes.map { like ->
            //get user id
            val likedById = like.uuid

            //get user
            val likedBy = users[likedById]

            //convert view data
            val likedByViewData = if (likedBy == null) {
                createDeletedUser()
            } else {
                convertUser(likedBy)
            }

            //create like view data
            LMFeedLikeViewData.Builder()
                .id(like.id)
                .userId(like.userId)
                .createdAt(like.createdAt)
                .updatedAt(like.updatedAt)
                .user(likedByViewData)
                .build()
        }
    }

    /**
     * convert list of [ReportTag] to list of [LMFeedReportTagViewData]
     * @param tags: list of [ReportTag]
     * */
    fun convertReportTag(
        tags: List<ReportTag>
    ): List<LMFeedReportTagViewData> {
        return tags.map { tag ->
            LMFeedReportTagViewData.Builder()
                .id(tag.id)
                .name(tag.name)
                .isSelected(false)
                .build()
        }
    }

    /**
     * convert list of [ReportTag] to list of [LMFeedReasonChooseViewData]
     * @param tags: list of [ReportTag]
     * */
    fun convertDeleteTag(
        tags: List<ReportTag>
    ): MutableList<LMFeedReasonChooseViewData> {
        return tags.map { tag ->
            LMFeedReasonChooseViewData.Builder()
                .value(tag.name)
                .build()
        }.toMutableList()
    }

    /**
     * convert [SingleUriData] to [LMFeedFileUploadViewData]
     * @param singleUriData: [SingleUriData]
     * */
    fun convertFileUploadViewData(
        singleUriData: SingleUriData
    ): LMFeedFileUploadViewData {
        return LMFeedFileUploadViewData.Builder()
            .uri(singleUriData.uri)
            .fileType(singleUriData.fileType)
            .width(singleUriData.width)
            .height(singleUriData.height)
            .thumbnailUri(singleUriData.thumbnailUri)
            .size(singleUriData.size)
            .mediaName(singleUriData.mediaName)
            .pdfPageCount(singleUriData.pdfPageCount)
            .duration(singleUriData.duration)
            .build()
    }

    /**--------------------------------
     * View Data Model -> Network Model
    --------------------------------*/

    fun convertPost(
        temporaryId: Long,
        uuid: String,
        text: String?,
        fileUris: List<LMFeedFileUploadViewData>
    ): Post {
        return Post.Builder()
            .tempId(temporaryId.toString())
            .id(temporaryId.toString())
            .uuid(uuid)
            .text(text ?: "")
            .attachments(convertAttachments(fileUris))
            .build()
    }

    //creates a list of network model of attachments from the provided list of attachment view data
    fun createAttachments(
        attachments: List<LMFeedAttachmentViewData>
    ): List<Attachment> {
        return attachments.map {
            convertAttachment(it)
        }
    }

    //creates a network model of attachment from the provided attachment view data
    fun convertAttachment(
        attachment: LMFeedAttachmentViewData
    ): Attachment {
        return Attachment.Builder()
            .attachmentType(attachment.attachmentType)
            .attachmentMeta(convertAttachmentMeta(attachment.attachmentMeta))
            .build()
    }

    private fun convertAttachmentMeta(
        attachmentMeta: LMFeedAttachmentMetaViewData
    ): AttachmentMeta {
        return AttachmentMeta.Builder().name(attachmentMeta.name)
            .ogTags(convertOGTags(attachmentMeta.ogTags))
            .url(attachmentMeta.url)
            .size(attachmentMeta.size)
            .duration(attachmentMeta.duration)
            .pageCount(attachmentMeta.pageCount)
            .format(attachmentMeta.format)
            .build()
    }

    // creates attachment list of Network Model for link attachment
    fun convertAttachments(
        linkOGTagsViewData: LMFeedLinkOGTagsViewData
    ): List<Attachment> {
        return listOf(
            Attachment.Builder()
                .attachmentType(LINK)
                .attachmentMeta(convertAttachmentMeta(linkOGTagsViewData))
                .build()
        )
    }

    // creates AttachmentMeta Network Model for link attachment meta
    private fun convertAttachmentMeta(
        linkOGTagsViewData: LMFeedLinkOGTagsViewData
    ): AttachmentMeta {
        return AttachmentMeta.Builder()
            .ogTags(convertOGTags(linkOGTagsViewData))
            .build()
    }

    // converts LinkOGTags view data model to network model
    private fun convertOGTags(
        linkOGTagsViewData: LMFeedLinkOGTagsViewData
    ): LinkOGTags {
        return LinkOGTags.Builder()
            .title(linkOGTagsViewData.title)
            .image(linkOGTagsViewData.image)
            .description(linkOGTagsViewData.description)
            .url(linkOGTagsViewData.url)
            .build()
    }

    // converts list of [LMFeedFileUploadViewData] to list of network [Attachment] model
    fun convertAttachments(fileUris: List<LMFeedFileUploadViewData>): List<Attachment> {
        return fileUris.map {
            convertAttachment(it)
        }
    }

    // converts [LMFeedFileUploadViewData] to network [Attachment] model
    private fun convertAttachment(fileUri: LMFeedFileUploadViewData): Attachment {
        val attachmentType = when (fileUri.fileType) {
            com.likeminds.customgallery.media.model.IMAGE -> {
                IMAGE
            }

            com.likeminds.customgallery.media.model.VIDEO -> {
                VIDEO
            }

            else -> {
                DOCUMENT
            }
        }

        return Attachment.Builder()
            .attachmentType(attachmentType)
            .attachmentMeta(convertAttachmentMeta(fileUri))
            .build()
    }

    //converts [LMFeedFileUploadViewData] to network [AttachmentMeta] model
    private fun convertAttachmentMeta(fileUri: LMFeedFileUploadViewData): AttachmentMeta {
        val url = String(
            Base64.decode(
                LMFeedAWSKeys.getBucketBaseUrl(),
                Base64.DEFAULT
            )
        ) + fileUri.awsFolderPath
        return AttachmentMeta.Builder()
            .url(url)
            .awsFolderPath(fileUri.awsFolderPath)
            .format(fileUri.format)
            .localFilePath(fileUri.localFilePath)
            .localUri(fileUri.uri)
            .name(fileUri.mediaName)
            .pageCount(fileUri.pdfPageCount)
            .size(fileUri.size)
            .awsFolderPath(fileUri.awsFolderPath)
            .thumbnailUrl(fileUri.thumbnailUri.toString())
            .duration(fileUri.duration)
            .build()
    }

    // converts list of Topic view data model to list of network model
    fun convertTopics(topics: List<LMFeedTopicViewData>?): List<Topic> {
        if (topics == null) {
            return emptyList()
        }

        return topics.map {
            convertTopic(it)
        }
    }

    // converts Topic view data model to network model
    private fun convertTopic(topic: LMFeedTopicViewData): Topic {
        return Topic.Builder()
            .id(topic.id)
            .isEnabled(topic.isEnabled)
            .name(topic.name)
            .build()
    }

    fun convertCommentsCount(commentsCount: Int): LMFeedCommentsCountViewData {
        return LMFeedCommentsCountViewData.Builder()
            .commentsCount(commentsCount)
            .build()
    }
}