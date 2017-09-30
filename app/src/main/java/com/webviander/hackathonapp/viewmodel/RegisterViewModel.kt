package com.webviander.hackathonapp.viewmodel

import android.app.Activity
import android.content.Context
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.util.Log
import android.view.View
import android.widget.Toast
import com.pixplicity.easyprefs.library.Prefs
import com.webviander.hackathonapp.data.ApiFactory
import com.webviander.hackathonapp.model.User
import com.webviander.hackathonapp.util.PreferenceUtil
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
    val aadhar = ObservableField<String>("")
    val name = ObservableField<String>("")
    val isRepresentative = ObservableBoolean(false)


    fun onSignupClick(view: View) {
        //Login is clicked
        val loginEmail = username.get()
        val loginPassword = password.get()
        val loginAadhar = aadhar.get()
        val loginName = name.get()
        val isRepresentativeBool = isRepresentative.get()

        validateSignup(loginEmail, loginPassword, loginAadhar, loginName, isRepresentativeBool) { user ->
            if (user != null) {
                if (user.success) {
                    Prefs.putString(PreferenceUtil.USERNAME, user.name)
                    Prefs.putString(PreferenceUtil.USEREMAIL, user.email)
                    Prefs.putString(PreferenceUtil.USERID, user.id)
                    Prefs.putString(PreferenceUtil.ISREPRESENTATIVE, user.isRepresentative.toString())
                    context.startActivity(FeedActivity.getIntent(context))
                    (context as Activity).finish()
                } else {
                    Toast.makeText(context, user.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

//        context.startActivity(FeedActivity.getIntent(context))
    }

    private fun validateSignup(loginUserName: String?, loginPassword: String?, aadhar: String, name: String, isRepresentative: Boolean, signupCallback: (User?) -> Unit) {
        Log.d("validateLogin", "called with $loginUserName::$loginPassword with name::$name aadhar::$aadhar and isRep:: $isRepresentative")

        loginUserName?.let {
            loginPassword?.let {
                ApiFactory().createUserService().createUser(loginUserName, loginPassword, name, isRepresentative, aadhar).enqueue(object : Callback<User> {
                    override fun onResponse(call: Call<User>?, response: Response<User>?) {
                        Log.d("onResponse", "called " + response?.body())
                        signupCallback(response?.body())
                    }

                    override fun onFailure(call: Call<User>?, t: Throwable?) {
                        Log.d("onFailure", "called")
                        t?.printStackTrace()
                        Toast.makeText(context,"Something went wrong! Please try later",Toast.LENGTH_SHORT).show()
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