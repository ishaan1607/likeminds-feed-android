package com.likeminds.feed.android.core.post.util

import com.likeminds.feed.android.core.universalfeed.model.LMFeedPostViewData

// to trigger post change events and notify observers
class LMFeedPostEvent {
    companion object {
        private var postEvent: LMFeedPostEvent? = null

        @JvmStatic
        fun getPublisher(): LMFeedPostEvent {
            if (postEvent == null) {
                postEvent = LMFeedPostEvent()
            }
            return postEvent!!
        }
    }

    // maintains the set of all the observers
    private var observers = hashSetOf<PostObserver>()

    // subscribes the observer to listen to the changes
    fun subscribe(postObserver: PostObserver) {
        observers.add(postObserver)
    }

    // unsubscribes the observer
    fun unsubscribe(postObserver: PostObserver) {
        observers.remove(postObserver)
    }

    // notifies all the observers with the new data
    fun notify(postData: Pair<String, LMFeedPostViewData?>) {
        for (listener in observers) {
            listener.update(postData)
        }
    }
}

interface PostObserver {
    /*
    * called whenever post changes are notified the observer
    * postData - Pair of postId and Post data
    * */
    fun update(postData: Pair<String, LMFeedPostViewData?>)
}