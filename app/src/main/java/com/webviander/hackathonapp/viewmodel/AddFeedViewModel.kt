package com.webviander.hackathonapp.viewmodel

import android.content.Context
import android.databinding.ObservableField
import android.util.Log
import android.view.View
import android.widget.EditText
import java.util.*

/**
 * Created by vivek-3102 on 28/09/17.
 */
class AddFeedViewModel(val context: Context) : Observable() {
    val backPressedTag = "BACK_PRESSED"
    val savePostTag = "SAVE_POST"

    var postBody: ObservableField<String> = ObservableField("")

    fun onBackClick(view: View) {
        setChanged()
        notifyObservers(backPressedTag)
    }

    fun onSavePost(view: View) {
        Log.d("SavePost", postBody.get())
    }
}