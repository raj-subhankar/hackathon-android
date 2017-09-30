package com.webviander.hackathonapp.viewmodel

import android.content.Context
import android.databinding.BaseObservable
import com.webviander.hackathonapp.model.Comment

/**
 * Created by vivek-3102 on 23/09/17.
 */
class CommentItemViewModel(var commentItem: Comment, var context: Context) : BaseObservable() {

    fun getPosterName(): String {
        commentItem.postedBy?.name?.let {
            return it
        }
        return "Guest"
    }

    fun getCommentText(): String {
        return commentItem.messageBody
    }
}