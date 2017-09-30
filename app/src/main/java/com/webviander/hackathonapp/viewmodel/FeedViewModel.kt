package com.webviander.hackathonapp.viewmodel

import android.content.Context
import android.util.Log
import android.view.View
import com.webviander.hackathonapp.data.ApiFactory
import com.webviander.hackathonapp.model.FeedItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by vivek-3102 on 30/08/17.
 */
class FeedViewModel(val context: Context) : Observable() {
    val backPressedTag = "BACK_PRESSED"
    val pendingLoadedTag = "PENDING_LOADED"
    val ongoingLoadedTag = "ONGOING_LOADED"
    val addFeedsClickedTag = "ADD_FEEDS"
    var pendingList: ArrayList<FeedItem> = ArrayList()
    var ongoingList: ArrayList<FeedItem> = ArrayList()

    enum class STATE {
        PENDING, ONGOING
    }

    var currentState: STATE = STATE.PENDING

    fun loadFeeds(latitude: Double?, longitude: Double?) {
        Log.d("getFeeds", "Calling with $latitude::$longitude")
        ApiFactory().createFeedsService().getFeeds(latitude.toString(), longitude.toString()).enqueue(object : Callback<List<FeedItem>> {
            override fun onFailure(call: Call<List<FeedItem>>?, t: Throwable?) {
                Log.d("onFailure", "called")
            }

            override fun onResponse(call: Call<List<FeedItem>>?, response: Response<List<FeedItem>>?) {
                Log.d("onResponse", "called ${response?.body()}")
                pendingList.clear()
                ongoingList.clear()
                response?.body()?.let {
                    for (feedItem in it) {
                        if (feedItem.pickedUpBy == null) {
                            pendingList.add(feedItem)
                        } else {
                            ongoingList.add(feedItem)
                        }
                    }
                }
                setChanged()
                if (currentState == STATE.PENDING) {
                    notifyObservers(pendingLoadedTag)
                } else {
                    notifyObservers(ongoingLoadedTag)
                }
            }

        })


    }

    fun onPendingClick(view: View) {
        currentState = STATE.PENDING
        setChanged()
        notifyObservers(pendingLoadedTag)
    }

    fun onOngoingClick(view: View) {
        currentState = STATE.ONGOING
        setChanged()
        notifyObservers(ongoingLoadedTag)
    }

    fun onBackClick(view: View) {
        setChanged()
        notifyObservers(backPressedTag)
    }

    fun onAddFeedClick(view: View) {
        setChanged()
        notifyObservers(addFeedsClickedTag)
    }

}