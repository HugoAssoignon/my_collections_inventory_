package com.collections.my_collections_inventory.services

import MangaDTO
import retrofit2.http.GET
import retrofit2.http.Path


interface MangaApiService {
    @GET("{id}")
    suspend fun retrieveMangaById(@Path("id") idManga: Int): MangaDTO

    @GET("top-ten")
    suspend fun getTopTenManga(): List<MangaDTO>

    @GET("all")
    suspend fun getAllManga(): List<MangaDTO>
}

