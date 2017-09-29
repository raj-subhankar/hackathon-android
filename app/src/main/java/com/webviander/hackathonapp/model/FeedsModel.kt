package com.webviander.hackathonapp.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


/**
 * Created by vivek-3102 on 30/08/17.
 */


data class FeedItem(

        @SerializedName("_id")
        @Expose
        var id: String,
        @SerializedName("user")
        @Expose
        var postedBy: FeedUser,
        @SerializedName("__v")
        @Expose
        var v: Int,
        @SerializedName("commentCount")
        @Expose
        var commentCount: Int,
        @SerializedName("comments")
        @Expose
        var comments: List<Comment>,
        @SerializedName("downVotes")
        @Expose
        var downVotes: List<Any>,
        @SerializedName("downVoteCount")
        @Expose
        var downVoteCount: Int,
        @SerializedName("upVotes")
        @Expose
        var upVotes: List<String>,
        @SerializedName("upVoteCount")
        @Expose
        var upVoteCount: Int,
        @SerializedName("location")
        @Expose
        var location: Location,
        @SerializedName("imageUrl")
        @Expose
        var imageUrl: String,
        @SerializedName("messageBody")
        @Expose
        var messageBody: String,
        @SerializedName("messageTitle")
        @Expose
        var messageTitle: String,
        @SerializedName("timeStamp")
        @Expose
        var timeStamp: String
) : Serializable

data class FeedUser(

        @SerializedName("_id")
        @Expose
        var id: String? = null,
        @SerializedName("email")
        @Expose
        var email: String? = null,
        @SerializedName("aadhaarNumber")
        @Expose
        var aadhaarNumber: String? = null,
        @SerializedName("__v")
        @Expose
        var v: Int? = null,
        @SerializedName("name")
        @Expose
        var name: String? = null,
        @SerializedName("isRepresentative")
        @Expose
        var isRepresentative: Boolean? = null,
        @SerializedName("loginAttempts")
        @Expose
        var loginAttempts: Int? = null,
        @SerializedName("profilePic")
        @Expose
        var profilePic: String
) : Serializable

data class Comment(
        @SerializedName("_id")
        @Expose
        var id: String,
        @SerializedName("postedBy")
        @Expose
        var postedBy: FeedUser,
        @SerializedName("__v")
        @Expose
        var v: Int,
        @SerializedName("messageBody")
        @Expose
        var messageBody: String,
        @SerializedName("timeStamp")
        @Expose
        var timeStamp: String
) : Serializable

data class Location(
        @SerializedName("coordinates")
        @Expose
        var coordinates: List<Float>? = null,
        @SerializedName("type")
        @Expose
        var type: String? = null

) : Serializable

//
//data class UserDetails(
//
//        @SerializedName("_id")
//        @Expose
//        var id: String? = null,
//        @SerializedName("email")
//        @Expose
//        var email: String? = null,
//        @SerializedName("password")
//        @Expose
//        var password: String? = null,
//        @SerializedName("__v")
//        @Expose
//        var v: Int? = null,
//        var name: String,
//        @SerializedName("loginAttempts")
//        @Expose
//        var loginAttempts: Int? = null
//) : Serializable
//
//
//data class CommentItem(
//        var id: String,
//        var commentBody: String,
//        var postedBy: UserDetails
//) : Serializable