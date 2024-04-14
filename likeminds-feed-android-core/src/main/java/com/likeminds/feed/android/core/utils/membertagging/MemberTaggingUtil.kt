package com.likeminds.feed.android.core.utils.membertagging

import com.likeminds.likemindsfeed.sdk.model.User
import com.likeminds.usertagging.model.TagUser
import com.likeminds.usertagging.view.UserTaggingSuggestionListView

object MemberTaggingUtil {

    /**
     * handles result and set result to [memberTagging] view as per [page]
     * */
    fun setMembersInView(
        memberTagging: UserTaggingSuggestionListView,
        result: Pair<Int, ArrayList<TagUser>>?
    ) {
        if (result != null) {
            val page = result.first
            val list = result.second
            if (page == 1) {
                //clear and set in adapter
                memberTagging.setMembers(list)
            } else {
                //add to the adapter
                memberTagging.addMembers(list)
            }
        } else {
            return
        }
    }

    //converts list of network model [User] to list of the tagging library model [TagUser]
    fun convertToTagUser(users: List<User>): List<TagUser> {
        return users.map { user ->
            TagUser.Builder()
                .id(user.id)
                .name(user.name)
                .imageUrl(user.imageUrl)
                .uuid(user.uuid)
                .build()
        }
    }
}