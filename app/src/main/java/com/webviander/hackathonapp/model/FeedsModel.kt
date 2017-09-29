package com.webviander.hackathonapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


/**
 * Created by vivek-3102 on 30/08/17.
 */


data class FeedItem(

        var id: String?,
        var posterName: String,
        var postBody: String,
        var timeStamp: Long,
        var likesCount: Int,
        var dislikesCount: Int,
        var commentsCount: Int) : Serializable
//        @SerializedName("_id")
//        @Expose
//        var id: String? = null,
//        @SerializedName("user")
//        @Expose
//        var user: UserDetails? = null,
//        @SerializedName("__v")
//        @Expose
//        var v: Int? = null,
//        @SerializedName("likes")
//        @Expose
//        var likes: List<Any>? = null,
//        @SerializedName("likesCount")
//        @Expose
//        var likesCount: Int? = null,
//        @SerializedName("location")
//        @Expose
//        var location: Location? = null,
//        @SerializedName("imageUrl")
//        @Expose
//        var imageUrl: String? = null,
//        @SerializedName("postBody")
//        @Expose
//        var postBody: String? = null
//)


data class Location(
        @SerializedName("coordinates")
        @Expose
        var coordinates: List<Float>? = null,
        @SerializedName("type")
        @Expose
        var type: String? = null

)


data class UserDetails(

        @SerializedName("_id")
        @Expose
        var id: String? = null,
        @SerializedName("email")
        @Expose
        var email: String? = null,
        @SerializedName("password")
        @Expose
        var password: String? = null,
        @SerializedName("__v")
        @Expose
        var v: Int? = null,
        var name:String,
        @SerializedName("loginAttempts")
        @Expose
        var loginAttempts: Int? = null
)


data class CommentItem(
        var id: String,
        var commentBody: String,
        var postedBy: UserDetails
)