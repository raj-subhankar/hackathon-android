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
        return feedItem.postedBy.name+" has posted this message"
    }

    fun getPostBody():String {
        return feedItem.messageBody
    }

    fun getThumbsUp() :String {
        return feedItem.upVoteCount.toString()
    }

    fun getThumbsDown() :String {
        return feedItem.downVoteCount.toString()
    }

    fun getCommentsCount() : String {
        return feedItem.commentCount.toString()
    }

    fun onItemClick(view: View) {
        context.startActivity(DetailsActivity.getIntent(view.context,feedItem))
    }

}