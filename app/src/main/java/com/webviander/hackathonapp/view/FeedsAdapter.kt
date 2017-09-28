package com.webviander.hackathonapp.view

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.webviander.hackathonapp.R
import com.webviander.hackathonapp.databinding.FeedListItemBinding
import com.webviander.hackathonapp.model.FeedItem
import com.webviander.hackathonapp.viewmodel.FeedItemViewModel

/**
 * Created by vivek-3102 on 23/09/17.
 */
class FeedsAdapter : RecyclerView.Adapter<FeedsItemViewHolder>() {

    var feedsList: ArrayList<FeedItem> = ArrayList()
        set(list) {
            field = list
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int {
        return feedsList.size
    }

    override fun onBindViewHolder(holder: FeedsItemViewHolder?, position: Int) {
        holder?.bindFeed(feedsList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): FeedsItemViewHolder {
        val itemBinding: FeedListItemBinding = DataBindingUtil.inflate<FeedListItemBinding>(LayoutInflater.from(parent?.context), R.layout.feed_list_item, parent, false)
        return FeedsItemViewHolder(itemBinding)
    }


}

class FeedsItemViewHolder(val itemBinding: FeedListItemBinding?) : RecyclerView.ViewHolder(itemBinding?.root) {


    internal fun bindFeed(feedItem: FeedItem) {
        if (itemBinding?.viewModel == null) {
            itemBinding?.viewModel = FeedItemViewModel(feedItem, itemView.context)
        } else {
            itemBinding.viewModel?.feedItem = feedItem
            itemBinding.viewModel?.notifyChange()
        }
    }
}

