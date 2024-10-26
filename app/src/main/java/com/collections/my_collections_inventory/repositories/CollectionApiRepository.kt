package com.collections.my_collections_inventory.repositories

import CollectionDTO
import MangaDTO
import android.util.Log
import com.collections.my_collections_inventory.services.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

class CollectionApiRepository {

    suspend fun retrieveUserCollection(userId: String?): List<MangaDTO>? {
        return withContext(Dispatchers.IO) {
            try {
                RetrofitInstance.collectionApiService.retrieveUserCollection(userId)
            } catch (e: IOException) {
                Log.e("ApiCalls", "Network error when calling API", e)
                null
            } catch (e: Exception) {
                Log.e("ApiCalls", "Unexpected error", e)
                null
            }
        }
    }

    suspend fun addMangaToUser(collection: CollectionDTO): Unit? {
        return withContext(Dispatchers.IO) {
            try {
                RetrofitInstance.collectionApiService.addMangaToUser(collection)
            } catch (e: IOException) {
                Log.e("ApiCalls", "Network error when calling API", e)
                null
            } catch (e: Exception) {
                Log.e("ApiCalls", "Unexpected error", e)
                null
            }
        }
    }

    suspend fun removeMangaToUser(collection: CollectionDTO): Unit? {
        return withContext(Dispatchers.IO) {
            try {
                RetrofitInstance.collectionApiService.removeMangaToUser(collection)
            } catch (e: IOException) {
                Log.e("ApiCalls", "Network error when calling API", e)
                null
            } catch (e: Exception) {
                Log.e("ApiCalls", "Unexpected error", e)
                null
            }
        }
    }
}