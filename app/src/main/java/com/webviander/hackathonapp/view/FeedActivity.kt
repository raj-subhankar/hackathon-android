package com.webviander.hackathonapp.view

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.webviander.hackathonapp.R
import com.webviander.hackathonapp.databinding.ActivityFeedBinding
import com.webviander.hackathonapp.model.FeedItem
import com.webviander.hackathonapp.viewmodel.FeedViewModel
import java.util.*

class FeedActivity : AppCompatActivity(), Observer {


    lateinit var binding: ActivityFeedBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLayouts()
        setUpViewModel()
        setUpFeeds(binding.feedsList)
        binding.viewModel?.let { setUpObserver(it) }
        binding.viewModel?.loadFeeds()


    }

    fun initLayouts() {
        binding = DataBindingUtil.setContentView<ActivityFeedBinding>(this, R.layout.activity_feed)
    }

    fun setUpViewModel() {
        binding.viewModel = FeedViewModel(this)
    }

    fun setUpFeeds(recyclerView: RecyclerView) {
        var adapter = FeedsAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    fun setUpObserver(observable: Observable) {
        observable.addObserver(this)
    }

    override fun update(p0: Observable?, p1: Any?) {
        Log.d("update", "called")
        if (p0 is FeedViewModel) {
            p1?.let {
                if (p1 == p0.backPressedTag) {
                    onBackPressed()
                } else if (p1 == p0.feedsLoadedTag) {
                    onFeedsLoaded(p0.feedsList)
                }
            }
        }
    }


    fun onFeedsLoaded(feedsList: ArrayList<FeedItem>) {
        val adapter = binding.feedsList.adapter as FeedsAdapter
        adapter.feedsList = feedsList
    }

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, FeedActivity::class.java)
        }
    }
}
