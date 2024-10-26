package com.collections.my_collections_inventory.services

import DataIp
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val dataIp = DataIp()
    private val apiUrl: String = "http://${dataIp.getIp()}"

    val collectionApiService: CollectionApiService by lazy {
        Retrofit.Builder()
            .baseUrl("$apiUrl/collection/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CollectionApiService::class.java)
    }

    val mangaApiService: MangaApiService by lazy {
        Retrofit.Builder()
            .baseUrl("$apiUrl/manga/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MangaApiService::class.java)
    }

    val userApiService: UserApiService by lazy {
        Retrofit.Builder()
            .baseUrl("$apiUrl/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserApiService::class.java)
    }
}