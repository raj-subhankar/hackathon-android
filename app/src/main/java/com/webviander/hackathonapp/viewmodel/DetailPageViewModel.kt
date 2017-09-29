package com.webviander.hackathonapp.viewmodel

import android.content.Context
import android.databinding.BaseObservable
import android.view.View
import com.pixplicity.easyprefs.library.Prefs
import com.webviander.hackathonapp.model.CommentItem
import com.webviander.hackathonapp.model.FeedItem
import com.webviander.hackathonapp.model.UserDetails
import com.webviander.hackathonapp.util.PreferenceUtil

/**
 * Created by vivek-3102 on 23/09/17.
 */
class DetailPageViewModel(var feedItem: FeedItem, var context: Context, var detailsPageCallback: DetailsPageCallback) : BaseObservable() {
    var commentList: ArrayList<CommentItem> = ArrayList()

    fun getTimeStamp(): String {
        return feedItem.timeStamp.toString()
    }

    fun getTitleText(): String {
        return feedItem.posterName + " has posted this message"
    }

    fun getPostBody(): String {
        return feedItem.postBody
    }

    fun getThumbsUp(): String {
        return feedItem.likesCount.toString()
    }

    fun getThumbsDown(): String {
        return feedItem.dislikesCount.toString()
    }

    fun getCommentsCount(): String {
        return feedItem.commentsCount.toString()
    }

    fun onBackClick(view: View) {
        detailsPageCallback.onBackPressed()
    }

    fun loadComments() {
        (0..10)
                .map {
                    CommentItem(it.toString(), "Some awesome comment $it", UserDetails(name = Prefs.getString(PreferenceUtil.USERNAME, "Guest")))
                }
                .forEach { commentList.add(it) }
        detailsPageCallback.onCommentsLoaded(commentList)
    }
}

interface DetailsPageCallback {
    fun onBackPressed()

    fun onCommentsLoaded(commentsList: ArrayList<CommentItem>)
}