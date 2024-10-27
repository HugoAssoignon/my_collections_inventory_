package com.collections.my_collections_inventory.repositories

import MangaDTO
import android.util.Log
import com.collections.my_collections_inventory.services.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

class MangaApiRepository {

    suspend fun retrieveMangaById(idManga: Int): MangaDTO? {
        return withContext(Dispatchers.IO) {
            try {
                RetrofitInstance.mangaApiService.retrieveMangaById(idManga)
            } catch (e: IOException) {
                Log.e("ApiCalls", "Network error when calling API", e)
                null
            } catch (e: Exception) {
                Log.e("ApiCalls", "Unexpected error", e)
                null
            }
        }
    }

    suspend fun retrieveAllManga(): List<MangaDTO>? {
        return withContext(Dispatchers.IO) {
            try {
                RetrofitInstance.mangaApiService.getAllManga()
            } catch (e: IOException) {
                Log.e("ApiCalls", "Network error when calling API", e)
                null
            } catch (e: Exception) {
                Log.e("ApiCalls", "Unexpected error", e)
                null
            }
        }
    }

    suspend fun retrieveTopTenManga(): List<MangaDTO>? {
        return withContext(Dispatchers.IO) {
            try {
                RetrofitInstance.mangaApiService.getTopTenManga()
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
