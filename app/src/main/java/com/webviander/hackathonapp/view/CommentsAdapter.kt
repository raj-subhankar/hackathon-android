package com.webviander.hackathonapp.view

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.webviander.hackathonapp.R
import com.webviander.hackathonapp.databinding.CommentListItemBinding
import com.webviander.hackathonapp.model.Comment
import com.webviander.hackathonapp.viewmodel.CommentItemViewModel

/**
 * Created by vivek-3102 on 23/09/17.
 */
class CommentsAdapter : RecyclerView.Adapter<CommentItemsViewHolder>() {

    var commentsList: ArrayList<Comment> = ArrayList()
        set(list) {
            field = list
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int {
        return commentsList.size
    }

    override fun onBindViewHolder(holder: CommentItemsViewHolder?, position: Int) {
        holder?.bindFeed(commentsList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CommentItemsViewHolder {
        val itemBinding: CommentListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent?.context), R.layout.comment_list_item, parent, false)
        return CommentItemsViewHolder(itemBinding)
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