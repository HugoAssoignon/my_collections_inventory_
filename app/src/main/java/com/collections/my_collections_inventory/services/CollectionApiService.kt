package com.collections.my_collections_inventory.services

import CollectionDTO
import MangaDTO
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CollectionApiService {
    @GET("{id}/get")
    suspend fun retrieveUserCollection(@Path("id") userId: String?): List<MangaDTO>

    @POST("add")
    suspend fun addMangaToUser(@Body collection: CollectionDTO)

    @POST("delete")
    suspend fun removeMangaToUser(@Body collection: CollectionDTO)
}

