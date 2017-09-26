package com.webviander.hackathonapp.viewmodel

import android.app.Activity
import android.content.Context
import android.databinding.ObservableField
import android.util.Log
import android.view.View
import com.pixplicity.easyprefs.library.Prefs
import com.webviander.hackathonapp.data.ApiFactory
import com.webviander.hackathonapp.model.User
import com.webviander.hackathonapp.util.PreferenceUtil
import com.webviander.hackathonapp.view.FeedActivity
import com.webviander.hackathonapp.view.RegisterActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


/**
 * Created by vivek-3102 on 26/08/17.
 */
class LoginViewModel(val context: Context) : Observable() {

    val username = ObservableField<String>("")
    val password = ObservableField<String>("")

    init {
        if(Prefs.getString(PreferenceUtil.USERID,null) != null) {
            //user is logged in.
            context.startActivity(FeedActivity.getIntent(context))
            (context as Activity).finish()
        }
    }

    fun onLoginClick(view: View) {
        //Login is clicked
        val loginUserName = username.get()
        val loginPassword = password.get()

        validateLogin(loginUserName, loginPassword) { user ->

            if (user != null) {
                if (user.success) {
                    Prefs.putString(PreferenceUtil.USERNAME, user.name)
                    Prefs.putString(PreferenceUtil.USEREMAIL, user.email)
                    Prefs.putString(PreferenceUtil.USERID, user.id)
                    Prefs.putString(PreferenceUtil.USERTOKEN, user.token)
                    context.startActivity(FeedActivity.getIntent(context))
                    (context as Activity).finish()
                }
            }

        }

    }

    private fun validateLogin(loginUserName: String?, loginPassword: String?, loginCallback: (User?) -> Unit) {
        Log.d("validateLogin", "called with $loginUserName and $loginPassword")

        loginUserName?.let {
            loginPassword?.let {
                ApiFactory().createUserService().authenticateUser(loginUserName, loginPassword).enqueue(object : Callback<User> {
                    override fun onResponse(call: Call<User>?, response: Response<User>?) {
                        Log.d("onCall", call?.request()?.url().toString())
                        Log.d("onResponse", "called " + response?.body())
                        loginCallback(response?.body())
                    }

                    override fun onFailure(call: Call<User>?, t: Throwable?) {
                        Log.d("onFailure", "called")
                        t?.printStackTrace()
                    }

                })
            }
        }

    }

    fun onSignupClick(view: View) {
        //signup is clicked
        context.startActivity(RegisterActivity.getIntent(context))
    }
}