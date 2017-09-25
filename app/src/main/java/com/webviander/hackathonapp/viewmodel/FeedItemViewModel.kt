package com.webviander.hackathonapp.viewmodel

import android.content.Context
import android.databinding.BaseObservable
import android.view.View
import com.webviander.hackathonapp.model.FeedItem
import com.webviander.hackathonapp.view.DetailsActivity

/**
 * Created by vivek-3102 on 23/09/17.
 */
class FeedItemViewModel(var feedItem: FeedItem,var context: Context): BaseObservable() {

    fun getTimeStamp() : String {
        return feedItem.timeStamp.toString()
    }
    fun getTitleText(): String {
        return feedItem.posterName+" has posted this message"
    }

    fun getPostBody():String {
        return feedItem.postBody
    }

    fun getThumbsUp() :String {
        return feedItem.likesCount.toString()
    }

    fun getThumbsDown() :String {
        return feedItem.dislikesCount.toString()
    }

    fun getCommentsCount() : String {
        return feedItem.commentsCount.toString()
    }

    fun onItemClick(view: View) {
        context.startActivity(DetailsActivity.getIntent(view.context,feedItem))
    }

}