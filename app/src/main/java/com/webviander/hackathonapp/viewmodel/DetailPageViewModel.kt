package com.webviander.hackathonapp.viewmodel

import android.content.Context
import android.databinding.BaseObservable
import android.databinding.ObservableField
import android.util.Log
import android.view.View
import com.pixplicity.easyprefs.library.Prefs
import com.webviander.hackathonapp.data.ApiFactory
import com.webviander.hackathonapp.model.Comment
import com.webviander.hackathonapp.model.FeedItem
import com.webviander.hackathonapp.model.FeedUser
import com.webviander.hackathonapp.model.UpdatePostModel
import com.webviander.hackathonapp.util.PreferenceUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by vivek-3102 on 23/09/17.
 */
class DetailPageViewModel(var feedItem: FeedItem, var context: Context, var detailsPageCallback: DetailsPageCallback) : BaseObservable() {
    var commentList: ArrayList<Comment> = ArrayList()

    var commentText: ObservableField<String> = ObservableField("")
    fun loadComments() {
        commentList.addAll(feedItem.comments)
        detailsPageCallback.onCommentsLoaded(commentList,false)
    }

    fun onBackClick(view: View) {
        detailsPageCallback.onBackPressed()
    }

    fun onSendClick(view: View) {
        Log.d("onSend", commentText.get()+" "+feedItem.id+" "+Prefs.getString(PreferenceUtil.USERID,""))

        val userId = Prefs.getString(PreferenceUtil.USERID,null)
        val userName = Prefs.getString(PreferenceUtil.USERNAME,"Guest")
        userId?.let {
            ApiFactory().createFeedsService().addComment(feedItem.id,it,commentText.get()).enqueue(object : Callback<UpdatePostModel> {
                override fun onResponse(call: Call<UpdatePostModel>?, response: Response<UpdatePostModel>?) {
                    Log.d("onResponse", "called")
                    val body = response?.body()
                    body?.let {
                        if(it.message == "comment inserted") {
                            //success
                            commentList.add(Comment("", FeedUser(id=userId,name = userName),messageBody = commentText.get()))
                            detailsPageCallback.onCommentsLoaded(commentList,true)
                            commentText.set("")
                        }
                    }
                }

                override fun onFailure(call: Call<UpdatePostModel>?, t: Throwable?) {
                    Log.d("onFailure", "called")

                }
            })

        }
    }
}

interface DetailsPageCallback {
    fun onBackPressed()

    fun onCommentsLoaded(commentsList: ArrayList<Comment>, newCommentAdded: Boolean)
}