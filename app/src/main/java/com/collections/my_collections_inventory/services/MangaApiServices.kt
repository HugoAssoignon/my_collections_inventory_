package com.collections.my_collections_inventory.services

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


class MangaApiService {
    private val apiUrl: String = "http://172.20.10.3:8080/externalManga/"

    suspend fun retrieveMangabyId(idManga: Int): String? {
        val urltoCall: String = apiUrl + idManga
        val client = OkHttpClient()

        val request = Request.Builder()
            .url(urltoCall)
            .build()

        return withContext(Dispatchers.IO) {
            try {
                val response: Response = client.newCall(request).execute()

                if (response.isSuccessful) {
                    response.body?.string().also {
                        Log.i("ApiCalls", "API response: $it")
                    }
                } else {
                    Log.i("ApiCalls", "Error calling API, code: ${response.code}")
                    null
                }
            } catch (e: IOException) {
                Log.i("ApiCalls", "Error when calling API", e)
                null
            }
        }
    }
}


data class Manga(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val author: String,
    val status: String,
    val nbBooks: Int,
    val description: String,
    val genre: Array<String>,
    val note: Float
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Manga

        if (id != other.id) return false
        if (title != other.title) return false
        if (imageUrl != other.imageUrl) return false
        if (author != other.author) return false
        if (status != other.status) return false
        if (nbBooks != other.nbBooks) return false
        if (description != other.description) return false
        if (!genre.contentEquals(other.genre)) return false
        if (note != other.note) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + title.hashCode()
        result = 31 * result + imageUrl.hashCode()
        result = 31 * result + author.hashCode()
        result = 31 * result + status.hashCode()
        result = 31 * result + nbBooks
        result = 31 * result + description.hashCode()
        result = 31 * result + genre.contentHashCode()
        result = 31 * result + note.hashCode()
        return result
    }


}
