package com.example.testmobileca.retrofit

import com.example.testmobileca.data.model.BanksResponse
import retrofit2.http.GET


interface APIClient {

    @GET("banks")
    suspend fun getAllAccounts(): List<BanksResponse>
}
