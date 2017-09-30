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

    fun loadComments() {
        commentList.addAll(feedItem.comments)
        detailsPageCallback.onCommentsLoaded(commentList)
    }

    fun onBackClick(view: View) {
        detailsPageCallback.onBackPressed()
    }
}

interface DetailsPageCallback {
    fun onBackPressed()

    fun onCommentsLoaded(commentsList: ArrayList<Comment>)
}