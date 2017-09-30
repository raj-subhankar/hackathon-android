package com.webviander.hackathonapp.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.webviander.hackathonapp.R
import com.webviander.hackathonapp.databinding.ActivityRegisterBinding
import com.webviander.hackathonapp.viewmodel.RegisterViewModel
import java.util.*

/**
 * Created by vivek-3102 on 26/08/17.
 */
class RegisterActivity : AppCompatActivity(), Observer {

    lateinit var binding: ActivityRegisterBinding


    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, RegisterActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        setUpViewModel()
        binding.viewModel?.let { setUpObserver(it) }
    }


    private fun initBinding() {
        binding = DataBindingUtil.setContentView<ActivityRegisterBinding>(this, R.layout.activity_register)
    }

    private fun setUpViewModel() {
        binding.viewModel = RegisterViewModel(this)
    }

    fun setUpObserver(observable: Observable) {
        observable.addObserver(this)
    }

    override fun update(p0: Observable?, p1: Any?) {
        if (p0 is RegisterViewModel) {
            p1?.let {
                if (p1 == p0.backPressedTag) {
                    startActivity(LoginActivity.getIntent(this))
                    finish()
                }
            }
        }
    }

}