package com.webviander.hackathonapp.viewmodel

import android.content.Context
import android.databinding.BaseObservable
import com.webviander.hackathonapp.model.CommentItem

/**
 * Created by vivek-3102 on 23/09/17.
 */
class CommentItemViewModel(var commentItem: CommentItem, var context: Context) : BaseObservable() {

    fun getPosterName(): String {
        return commentItem.postedBy.name
    }

    fun getCommentText(): String {
        return commentItem.commentBody
    }
}