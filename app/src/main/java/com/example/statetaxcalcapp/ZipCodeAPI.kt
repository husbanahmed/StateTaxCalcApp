package com.example.statetaxcalcapp

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ZipCodeAPI {

    @GET("v1/salestax")
    suspend fun getZipCode(
        @Header("X-Api-Key") apikey: String,
        @Query("zip_code") zipCode: String
    ): Response<List<ZipCode>>
}