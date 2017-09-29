package com.webviander.hackathonapp.viewmodel

import android.content.Context
import android.databinding.BaseObservable
import android.util.Log
import android.view.View
import com.pixplicity.easyprefs.library.Prefs
import com.webviander.hackathonapp.data.ApiFactory
import com.webviander.hackathonapp.model.FeedItem
import com.webviander.hackathonapp.util.PreferenceUtil
import com.webviander.hackathonapp.view.DetailsActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

    fun onThumbsUp(view: View) {
        val userId = Prefs.getString(PreferenceUtil.USERID,null)
        userId?.let {
            ApiFactory().createFeedsService().upVote(feedItem.id,userId).enqueue(object : Callback<String> {
                override fun onFailure(call: Call<String>?, t: Throwable?) {
                    Log.d("onFailure", "called")

                }

                override fun onResponse(call: Call<String>?, response: Response<String>?) {
                    Log.d("upVote", "called ${response?.body()}")
                    //Post upvoted

                }

            })
        }
    }

    fun onThumbsDown(view: View) {
        val userId = Prefs.getString(PreferenceUtil.USERID,null)
        userId?.let {
            ApiFactory().createFeedsService().downVote(feedItem.id,userId).enqueue(object : Callback<String> {
                override fun onFailure(call: Call<String>?, t: Throwable?) {
                    Log.d("onFailure", "called")

                }

                override fun onResponse(call: Call<String>?, response: Response<String>?) {
                    Log.d("upVote", "called ${response?.body()}")
                    //Post upvoted

                }

            })
        }
    }

}