package com.webviander.hackathonapp.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by vivek-3102 on 26/08/17.
 */
class ApiFactory {
    private val BASE_URL = "http://ec2-54-149-192-204.us-west-2.compute.amazonaws.com:3000/"

    @Suppress("HasPlatformType")
    val retrofit by lazy {
        Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    fun createUserService(): UserLoginService {
        return retrofit.create(UserLoginService::class.java)
    }

    fun createFeedsService(): FeedsService {
        return retrofit.create(FeedsService::class.java)
    }
}