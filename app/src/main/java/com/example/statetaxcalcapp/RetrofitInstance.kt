package com.example.statetaxcalcapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val api: ZipCodeAPI by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.api-ninjas.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ZipCodeAPI::class.java)
    }
}