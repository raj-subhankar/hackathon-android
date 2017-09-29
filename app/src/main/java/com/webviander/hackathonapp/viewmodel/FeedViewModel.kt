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
    val feedsLoadedTag = "FEEDS_LOADED"
    val addFeedsClickedTag = "ADD_FEEDS"
    var feedsList: ArrayList<FeedItem> = ArrayList()

    fun loadFeeds() {
//        (0..10)
//                .map { FeedItem(it.toString(), "Vivek Chanddru+$it", context.resources.getString(R.string.dummy_content), System.currentTimeMillis(), 10 + it, 20 + it, 30 - it) }
//                .forEach { feedsList.add(it) }

        ApiFactory().createFeedsService().getFeeds("12.8333", "80.0667").enqueue(object : Callback<List<FeedItem>> {
            override fun onFailure(call: Call<List<FeedItem>>?, t: Throwable?) {
                Log.d("onFailure", "called")

            }

            override fun onResponse(call: Call<List<FeedItem>>?, response: Response<List<FeedItem>>?) {
                Log.d("onResponse", "called ${response?.body()}")
                feedsList.clear()
                response?.body()?.let { feedsList.addAll(it) }
                setChanged()
                notifyObservers(feedsLoadedTag)
            }

        })


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