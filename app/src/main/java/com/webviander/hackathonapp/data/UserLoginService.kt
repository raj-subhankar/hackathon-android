package com.webviander.hackathonapp.data

import com.webviander.hackathonapp.model.User
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by vivek-3102 on 26/08/17.
 */
interface UserLoginService {

    @FormUrlEncoded
    @POST("authenticate")
    fun authenticateUser(@Field("email") username: String, @Field("password") password: String): Call<User>

    @FormUrlEncoded
    @POST("users/add")
    fun createUser(@Field("email") email: String, @Field("password") password: String, @Field("name") name: String, @Field("isRepresentative") isRepresentative: Boolean, @Field("aadhaarNumber") aadhaarNumber: String): Call<User>
}
