package com.webviander.hackathonapp.view

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.webviander.hackathonapp.R
import com.webviander.hackathonapp.databinding.ActivityDetailsBinding
import com.webviander.hackathonapp.model.FeedItem
import com.webviander.hackathonapp.viewmodel.DetailPageViewModel

class DetailsActivity : AppCompatActivity() {


    lateinit var binding: ActivityDetailsBinding
    lateinit var feedItem: FeedItem
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        populateFromIntent(intent)
        initLayouts()
        setUpViewModel()
//        binding.viewModel?.let { setUpObserver(it) }
    }

    private fun populateFromIntent(intent: Intent?) {
        feedItem = intent?.getSerializableExtra(FEED_ITEM_EXTRA) as FeedItem
    }

    fun initLayouts() {
        binding = DataBindingUtil.setContentView<ActivityDetailsBinding>(this, R.layout.activity_details)
    }

    fun setUpViewModel() {
        binding.viewModel = DetailPageViewModel(feedItem,this)
    }


    companion object {
        val FEED_ITEM_EXTRA = "Feed_Item_Extra"
        fun getIntent(context: Context, feedItem: FeedItem): Intent {
            val i = Intent(context, DetailsActivity::class.java)
            i.putExtra(FEED_ITEM_EXTRA, feedItem)
            return i
        }
    }
}
