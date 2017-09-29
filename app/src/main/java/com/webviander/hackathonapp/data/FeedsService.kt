package com.webviander.hackathonapp.data

import com.webviander.hackathonapp.model.FeedItem
import com.webviander.hackathonapp.model.User
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by vivek-3102 on 26/08/17.
 */
interface FeedsService {

    @GET("posts/all")
    fun getFeeds(@Query("lat") latitude: String, @Query("lng") longitude: String): Call<List<FeedItem>>

//    @FormUrlEncoded
//    @POST("users/add")
//    fun createUser(@Field("email") username: String, @Field("password") password: String): Call<User>
}
