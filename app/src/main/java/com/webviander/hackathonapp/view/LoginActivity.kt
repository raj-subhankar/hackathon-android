package com.webviander.hackathonapp.view

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.webviander.hackathonapp.R
import com.webviander.hackathonapp.databinding.ActivityLoginBinding
import com.webviander.hackathonapp.viewmodel.LoginViewModel
import java.util.*

class LoginActivity : AppCompatActivity(), Observer {

    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLayouts()
        setUpViewModel()
        binding.viewModel?.let { setUpObserver(it) }
    }


    fun initLayouts() {
        binding = DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login)
    }

    fun setUpViewModel() {
        binding.viewModel = LoginViewModel(this)
    }

    fun setUpObserver(observable: Observable) {
        observable.addObserver(this)
    }

    override fun update(p0: Observable?, p1: Any?) {
        Log.d("update", "called")
    }

    companion object {
        fun getIntent(context: Context) : Intent {
            return Intent(context,LoginActivity::class.java)
        }
    }

}
