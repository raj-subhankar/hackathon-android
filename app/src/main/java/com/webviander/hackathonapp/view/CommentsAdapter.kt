package com.webviander.hackathonapp.view

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.webviander.hackathonapp.R
import com.webviander.hackathonapp.databinding.CommentListItemBinding
import com.webviander.hackathonapp.databinding.FeedListItemBinding
import com.webviander.hackathonapp.model.Comment
import com.webviander.hackathonapp.model.FeedItem
import com.webviander.hackathonapp.viewmodel.CommentItemViewModel
import com.webviander.hackathonapp.viewmodel.FeedItemViewModel

/**
 * Created by vivek-3102 on 23/09/17.
 */
class CommentsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var commentsList: ArrayList<Comment> = ArrayList()
        set(list) {
            field = list
            notifyDataSetChanged()
        }

    var feedItem: FeedItem? = null
        set(list) {
            field = list
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int {
        return commentsList.size+1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        when (holder) {
            is PostItemViewHolder -> {
                feedItem?.let {
                    holder.bindFeed(feedItem)
                }
            }
            is CommentItemsViewHolder -> {
                holder.bindFeed(commentsList[position])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 0) {
            val itemBinding: FeedListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent?.context), R.layout.feed_list_item, parent, false)
            return PostItemViewHolder(itemBinding)
        }

        val itemBinding: CommentListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent?.context), R.layout.comment_list_item, parent, false)
        return CommentItemsViewHolder(itemBinding)
    }


    override fun getItemViewType(position: Int): Int {
        return position
    }


}

class CommentItemsViewHolder(val itemBinding: CommentListItemBinding?) : RecyclerView.ViewHolder(itemBinding?.root) {

    internal fun bindFeed(commentItem: Comment) {
        if (itemBinding?.viewModel == null) {
            itemBinding?.viewModel = CommentItemViewModel(commentItem, itemView.context)
        } else {
            itemBinding.viewModel?.commentItem = commentItem
            itemBinding.viewModel?.notifyChange()
        }
    }
}

class PostItemViewHolder(val itemBinding: FeedListItemBinding?) : RecyclerView.ViewHolder(itemBinding?.root) {

    internal fun bindFeed(feedItem: FeedItem?) {
        feedItem?.let {
            if (itemBinding?.viewModel == null) {
                itemBinding?.viewModel = FeedItemViewModel(it, itemView.context,false)
            } else {
                itemBinding.viewModel?.feedItem = it
                itemBinding.viewModel?.notifyChange()
            }
        }
    }
}