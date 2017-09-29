package com.webviander.hackathonapp.data

import com.webviander.hackathonapp.model.FeedItem
import com.webviander.hackathonapp.model.User
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by vivek-3102 on 26/08/17.
 */
interface FeedsService {

    @GET("posts/all")
    fun getFeeds(@Query("lat") latitude: String, @Query("lng") longitude: String): Call<List<FeedItem>>

    @FormUrlEncoded
    @POST("posts/upvote")
    fun upVote(@Field("post_id") postId: String, @Field("user_id") userId: String): Call<String>

    @FormUrlEncoded
    @POST("posts/downvote")
    fun downVote(@Field("post_id") postId: String, @Field("user_id") userId: String): Call<String>

    @FormUrlEncoded
    @POST("posts/add")
    fun downVote(@Field("user") userId: String,@Field("messageTitle") messageTitle: String,@Field("messageBody") messageBody: String, @Field("location") location: String): Call<String>

//    @FormUrlEncoded
//    @POST("users/add")
//    fun createUser(@Field("email") username: String, @Field("password") password: String): Call<User>
}
