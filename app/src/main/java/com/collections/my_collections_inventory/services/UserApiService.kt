package com.collections.my_collections_inventory.services


import com.collections.my_collections_inventory.data_class.LoginRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApiService {
    @POST("/login")
    suspend fun loginUser(@Body request: LoginRequest): String

    @POST("/login/add")
    suspend fun createUser(@Body request: LoginRequest): String
}
