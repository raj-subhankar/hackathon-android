package com.webviander.hackathonapp.viewmodel

import android.content.Context
import android.databinding.ObservableField
import android.util.Log
import android.view.View
import com.webviander.hackathonapp.data.ApiFactory
import com.webviander.hackathonapp.model.User
import com.webviander.hackathonapp.view.FeedActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


/**
 * Created by vivek-3102 on 26/08/17.
 */
class RegisterViewModel(val context: Context) : Observable() {

    val backPressedTag = "BACK_PRESSED"


    val username = ObservableField<String>("")
    val password = ObservableField<String>("")


    fun onSignupClick(view: View) {
        //Login is clicked
        val loginUserName = username.get()
        val loginPassword = password.get()

//        validateSignup(loginUserName, loginPassword) {
//
//        }

        context.startActivity(FeedActivity.getIntent(context))
    }

    private fun validateSignup(loginUserName: String?, loginPassword: String?, signupCallback: () -> Unit) {
        Log.d("validateLogin", "called with $loginUserName and $loginPassword")

        loginUserName?.let {
            loginPassword?.let {
                ApiFactory().createUserService().createUser(loginUserName, loginPassword).enqueue(object : Callback<User> {
                    override fun onResponse(call: Call<User>?, response: Response<User>?) {
                        Log.d("onResponse", "called " + response?.body())
                    }

                    override fun onFailure(call: Call<User>?, t: Throwable?) {
                        Log.d("onFailure", "called")
                        t?.printStackTrace()
                    }

                })
            }
        }

    }

    fun onBackClick(view: View) {
        setChanged()
        notifyObservers(backPressedTag)
    }
}