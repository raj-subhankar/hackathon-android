package com.webviander.hackathonapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * Created by vivek-3102 on 26/08/17.
 */
data class User(
        @SerializedName("success")
        @Expose
        var success: Boolean,
        @SerializedName("message")
        @Expose
        var message: String? = null,
        @SerializedName("token")
        @Expose
        var token: String? = null,
        @SerializedName("name")
        @Expose
        var name: String,
        @SerializedName("email")
        @Expose
        var email: String,
        @SerializedName("id")
        @Expose
        var id: String? = null,
        @SerializedName("profilePic")
        @Expose
        var profilePicUrl: String? = null,
        @SerializedName("isRepresentative")
        @Expose
        var isRepresentative: Boolean,
        @SerializedName("aadhaarNumber")
        @Expose
        var aadhaarNumber: String? = null

)