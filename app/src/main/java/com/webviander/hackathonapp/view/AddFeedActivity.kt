package com.webviander.hackathonapp.view

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.webviander.hackathonapp.R
import com.webviander.hackathonapp.databinding.ActivityAddFeedBinding
import com.webviander.hackathonapp.viewmodel.AddFeedViewModel
import com.webviander.hackathonapp.viewmodel.FeedViewModel
import java.util.*

class AddFeedActivity : AppCompatActivity(), Observer {

    lateinit var binding: ActivityAddFeedBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initLayouts()
        setUpViewModel()
        binding.viewModel?.let { setUpObserver(it) }
    }

    fun initLayouts() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_feed)
    }

    fun setUpViewModel() {
        binding.viewModel = AddFeedViewModel(this)
    }

    fun setUpObserver(observable: Observable) {
        observable.addObserver(this)
    }

    override fun update(p0: Observable?, p1: Any?) {
        if (p0 is AddFeedViewModel) {
            p1?.let {
                if (p1 == p0.backPressedTag) {
                    onBackPressed()
                }
            }
        }
    }

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, AddFeedActivity::class.java)
        }
    }
}
