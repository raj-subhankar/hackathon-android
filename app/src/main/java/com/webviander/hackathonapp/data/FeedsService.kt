package com.webviander.hackathonapp.data

import com.webviander.hackathonapp.model.*
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
    fun upVote(@Field("post_id") postId: String, @Field("user_id") userId: String): Call<VoteModel>

    @FormUrlEncoded
    @POST("posts/downvote")
    fun downVote(@Field("post_id") postId: String, @Field("user_id") userId: String): Call<VoteModel>

    @FormUrlEncoded
    @POST("posts/add")
    fun addPost(@Field("user") userId: String,@Field("messageTitle") messageTitle: String,@Field("messageBody") messageBody: String, @Field("lat") latitude: String, @Field("lng") longitude: String): Call<AddPostModel>

    @FormUrlEncoded
    @POST("posts/{id}")
    fun addAssignee(@Path("id") postId: String, @Field("pickedUpBy") pickedUpBy: String): Call<UpdatePostModel>


//    @FormUrlEncoded
//    @POST("users/add")
//    fun createUser(@Field("email") username: String, @Field("password") password: String): Call<User>
}
