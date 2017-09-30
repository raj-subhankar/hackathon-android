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

        @SerializedName("pickedUpBy")
        @Expose
        var pickedUpBy: FeedUser? = null,
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
        var profilePic: String? = null
) : Serializable

data class Comment(
        @SerializedName("_id")
        @Expose
        var id: String,
        @SerializedName("postedBy")
        @Expose
        var postedBy: FeedUser?= null,
        @SerializedName("__v")
        @Expose
        var v: Int?= null,
        @SerializedName("messageBody")
        @Expose
        var messageBody: String,
        @SerializedName("timeStamp")
        @Expose
        var timeStamp: String?= null
) : Serializable

data class Location(
        @SerializedName("coordinates")
        @Expose
        var coordinates: List<Float>? = null,
        @SerializedName("type")
        @Expose
        var type: String? = null

) : Serializable


data class AddPostModel(
        @SerializedName("__v")
        @Expose
        var v: Int? = null,
        @SerializedName("user")
        @Expose
        var user: String? = null,
        @SerializedName("_id")
        @Expose
        var id: String? = null,
        @SerializedName("commentCount")
        @Expose
        var commentCount: Int? = null,
        @SerializedName("comments")
        @Expose
        var comments: List<Any>? = null,
        @SerializedName("downVotes")
        @Expose
        var downVotes: List<Any>? = null,
        @SerializedName("downVoteCount")
        @Expose
        var downVoteCount: Int? = null,
        @SerializedName("upVotes")
        @Expose
        var upVotes: List<Any>? = null,
        @SerializedName("upVoteCount")
        @Expose
        var upVoteCount: Int? = null,
        @SerializedName("location")
        @Expose
        var location: Location? = null,
        @SerializedName("imageUrl")
        @Expose
        var imageUrl: String? = null,
        @SerializedName("messageBody")
        @Expose
        var messageBody: String? = null,
        @SerializedName("messageTitle")
        @Expose
        var messageTitle: String? = null,
        @SerializedName("timeStamp")
        @Expose
        var timeStamp: String? = null
)

data class VoteModel(
        @SerializedName("message")
        @Expose
        var message: String? = null,
        @SerializedName("upVoteCount")
        @Expose
        var upVoteCount: Int,
        @SerializedName("downVoteCount")
        @Expose
        var downVoteCount: Int
)

data class UpdatePostModel(
        @SerializedName("message")
        @Expose
        var message: String
)