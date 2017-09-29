package com.webviander.hackathonapp.viewmodel

import android.content.Context
import android.databinding.BaseObservable
import android.view.View
import com.webviander.hackathonapp.model.Comment
import com.webviander.hackathonapp.model.FeedItem

/**
 * Created by vivek-3102 on 23/09/17.
 */
class DetailPageViewModel(var feedItem: FeedItem, var context: Context, var detailsPageCallback: DetailsPageCallback) : BaseObservable() {
    var commentList: ArrayList<Comment> = ArrayList()

    fun getTimeStamp(): String {
        return feedItem.timeStamp.toString()
    }

    fun getTitleText(): String {
        return feedItem.postedBy.name + " has posted this message"
    }

    fun getPostBody(): String {
        return feedItem.messageBody
    }

    fun getThumbsUp(): String {
        return feedItem.upVoteCount.toString()
    }

    fun getThumbsDown(): String {
        return feedItem.downVoteCount.toString()
    }

    fun getCommentsCount(): String {
        return feedItem.commentCount.toString()
    }

    fun onBackClick(view: View) {
        detailsPageCallback.onBackPressed()
    }

    fun loadComments() {
        commentList.addAll(feedItem.comments)
        detailsPageCallback.onCommentsLoaded(commentList)
    }
}

interface DetailsPageCallback {
    fun onBackPressed()

    fun onCommentsLoaded(commentsList: ArrayList<Comment>)
}