package com.example.api

import com.example.api.Constants.Companion.Base_url
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object retrofitinstance {
    private val retrofit by lazy {
        Retrofit.Builder().baseUrl(Base_url)
            .addConverterFactory(GsonConverterFactory.create()).build()

    }
    val api : mainapi by lazy {
        retrofit.create(mainapi::class.java)
    }

}