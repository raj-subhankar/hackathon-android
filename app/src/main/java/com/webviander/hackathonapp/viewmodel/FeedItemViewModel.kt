package com.webviander.hackathonapp.viewmodel

import android.app.Activity
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
import java.text.SimpleDateFormat

/**
 * Created by vivek-3102 on 23/09/17.
 */
class FeedItemViewModel(var feedItem: FeedItem, var context: Context, var isFromFeedsList: Boolean) : BaseObservable() {

    val dateFormat = SimpleDateFormat("dd-MM :: hh:mm")
    fun getTimeStamp(): String {
        return feedItem.timeStamp
    }

    fun getPickedUpBy(): String {
        return feedItem.pickedUpBy?.name.toString()
    }

    fun getPoster(): String {
        return feedItem.postedBy.name + ""
    }

    fun getTitleText(): String {
        return feedItem.messageTitle
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

    fun getLinesCount() = if (isFromFeedsList) {
        3
    } else {
        0
    }


    fun shouldShowPickup(): Int {
        val isRep = Prefs.getString(PreferenceUtil.ISREPRESENTATIVE, null)

        if (feedItem.pickedUpBy != null) {
            //already picked up
            return View.GONE
        }
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

    fun shouldShowAssignee(): Int {
        return if (feedItem.pickedUpBy != null) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    fun onItemClick(view: View) {
        if (isFromFeedsList) {
            context.startActivity(DetailsActivity.getIntent(view.context, feedItem))
            (context as Activity).finish()
        }
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
        val userId = Prefs.getString(PreferenceUtil.USERID, null)
        userId?.let {
            ApiFactory().createFeedsService().addAssignee(feedItem.id, it).enqueue(object : Callback<UpdatePostModel> {
                override fun onResponse(call: Call<UpdatePostModel>?, response: Response<UpdatePostModel>?) {
                    Log.d("onResponse", "${call?.request()?.url()} with field ${call?.request()?.body().toString()} called ${response?.body()}")
                    val updatePostModel = response?.body()
                    if (updatePostModel?.message.equals("Post updated")) {
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