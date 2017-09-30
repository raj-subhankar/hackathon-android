package com.webviander.hackathonapp.viewmodel

import android.content.Context
import android.databinding.BaseObservable
import android.databinding.Bindable
import android.util.Log
import android.view.View
import android.widget.Toast
import com.android.databinding.library.baseAdapters.BR
import com.pixplicity.easyprefs.library.Prefs
import com.webviander.hackathonapp.data.ApiFactory
import com.webviander.hackathonapp.model.FeedItem
import com.webviander.hackathonapp.model.FeedUser
import com.webviander.hackathonapp.model.UpdatePostModel
import com.webviander.hackathonapp.model.VoteModel
import com.webviander.hackathonapp.util.PreferenceUtil
import com.webviander.hackathonapp.view.DetailsActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by vivek-3102 on 23/09/17.
 */
class FeedItemViewModel(var feedItem: FeedItem, var context: Context) : BaseObservable() {

    fun getTimeStamp(): String {
        return feedItem.timeStamp.toString()
    }

    fun getTitleText(): String {
        return feedItem.postedBy.name + " has posted this message"
    }

    fun getPostBody(): String {
        return feedItem.messageBody
    }

    @Bindable
    fun getThumbsUp(): String {
        return feedItem.upVoteCount.toString()
    }

    @Bindable
    fun getThumbsDown(): String {
        return feedItem.downVoteCount.toString()
    }

    fun getCommentsCount(): String {
        return feedItem.commentCount.toString()
    }

    fun shouldShowPickup(): Int {
        val isRep = Prefs.getString(PreferenceUtil.ISREPRESENTATIVE, null)
        isRep?.let {
            val isRepBool = isRep.toBoolean()
            return if (isRepBool) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
        return View.GONE
    }

    fun onItemClick(view: View) {
        context.startActivity(DetailsActivity.getIntent(view.context, feedItem))
    }

    fun onThumbsUp(view: View) {
        val userId = Prefs.getString(PreferenceUtil.USERID, null)
        userId?.let {
            ApiFactory().createFeedsService().upVote(feedItem.id, userId).enqueue(object : Callback<VoteModel> {
                override fun onFailure(call: Call<VoteModel>?, t: Throwable?) {
                    Log.d("onFailure", "called")
                    t?.printStackTrace()
                }

                override fun onResponse(call: Call<VoteModel>?, response: Response<VoteModel>?) {
                    Log.d("upVote", "called ${response?.body()}")
                    val voteModel = response?.body()
                    voteModel?.let {
                        Toast.makeText(context, voteModel.message, Toast.LENGTH_SHORT).show()
                        feedItem.upVoteCount = voteModel.upVoteCount
                        feedItem.downVoteCount = voteModel.downVoteCount
                        notifyPropertyChanged(BR.thumbsUp)
                        notifyPropertyChanged(BR.thumbsDown)
                    }
                }

            })
        }
    }

    fun onThumbsDown(view: View) {
        val userId = Prefs.getString(PreferenceUtil.USERID, null)
        userId?.let {
            ApiFactory().createFeedsService().downVote(feedItem.id, userId).enqueue(object : Callback<VoteModel> {
                override fun onFailure(call: Call<VoteModel>?, t: Throwable?) {
                    Log.d("onFailure", "called " + call?.request()?.url() + "")
                    t?.printStackTrace()
                }

                override fun onResponse(call: Call<VoteModel>?, response: Response<VoteModel>?) {
                    Log.d("upVote", "called ${response?.body()}")
                    val voteModel = response?.body()
                    voteModel?.let {
                        Toast.makeText(context, voteModel.message, Toast.LENGTH_SHORT).show()
                        feedItem.upVoteCount = voteModel.upVoteCount
                        feedItem.downVoteCount = voteModel.downVoteCount
                        notifyPropertyChanged(BR.thumbsUp)
                        notifyPropertyChanged(BR.thumbsDown)
                    }
                }

            })
        }
    }

    fun onPickupClick(view: View) {
        val userId = Prefs.getString(PreferenceUtil.USERID,null)
        userId?.let {
            ApiFactory().createFeedsService().addAssignee(feedItem.id,it).enqueue(object : Callback<UpdatePostModel> {
                override fun onResponse(call: Call<UpdatePostModel>?, response: Response<UpdatePostModel>?) {
                    Log.d("onResponse", "${call?.request()?.url()} with field ${call?.request()?.body().toString()} called ${response?.body()}")
                    val updatePostModel = response?.body()
                    if(updatePostModel?.message.equals("Post updated")) {
                        //success
                        feedItem.postedBy = FeedUser(userId)
                    }
                }

                override fun onFailure(call: Call<UpdatePostModel>?, t: Throwable?) {
                    Log.d("onFailure", "called")
                    t?.printStackTrace()

                }

            })
        }
    }

}