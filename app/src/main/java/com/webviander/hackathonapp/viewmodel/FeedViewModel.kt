package com.webviander.hackathonapp.viewmodel

import android.content.Context
import android.view.View
import com.webviander.hackathonapp.R
import com.webviander.hackathonapp.model.FeedItem
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by vivek-3102 on 30/08/17.
 */
class FeedViewModel(val context: Context) : Observable() {
    val backPressedTag = "BACK_PRESSED"
    val feedsLoadedTag = "FEEDS_LOADED"
    var feedsList: ArrayList<FeedItem> = ArrayList()

     fun loadFeeds() {
         (0..10)
                 .map { FeedItem(it.toString(), "Vivek Chanddru+$it", context.resources.getString(R.string.dummy_content), System.currentTimeMillis(), 10+ it, 20+ it, 30- it) }
                 .forEach { feedsList.add(it) }
        setChanged()
        notifyObservers(feedsLoadedTag)
    }

    fun onBackClick(view: View) {
        setChanged()
        notifyObservers(backPressedTag)
    }

}