package com.collections.my_collections_inventory.services

import DataIp
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException
import android.content.Context


class UserApiServices {
    private val dataIp = DataIp()
    private var apiUrl: String = "http://${dataIp.getIp()}/login"

    suspend fun retrieveUserByUsernameAndPassword(
        context: Context,
        type: String,
        username: String,
        password: String
    ): String? {
        if (username.isBlank() || password.isBlank()) {
            Log.d("ApiCalls", "Username or password empty or null.")
            return null
        }
        val client = OkHttpClient()
        val request = getRequest(type, username, password)

        return withContext(Dispatchers.IO) {
            try {
                val response: Response = client.newCall(request).execute()

                if (response.isSuccessful) {
                    val responseBody = response.body?.string()
                    Log.d("ApiCalls", "API response successful: $responseBody")

                    val jsonObject = responseBody?.let { JSONObject(it) }
                    val userId = jsonObject?.getString("user_id")

                    val sharedPref = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
                    with(sharedPref.edit()) {
                        putString("USER_ID", userId)
                        apply()
                    }

                    return@withContext userId
                } else {
                    Log.d("ApiCalls", "Error calling API, code: ${response.code}")
                    return@withContext null
                }
            } catch (e: IOException) {
                Log.e("ApiCalls", "Error when calling API : ${e.message}", e)
                return@withContext null
            }
        }
    }

    private fun getRequest(type: String, username: String, password: String): Request {
        val jsonObject = JSONObject()
        jsonObject.put("username", username)
        jsonObject.put("password", password)

        val mediaType = "application/json; charset=utf-8".toMediaTypeOrNull()
        val requestBody: RequestBody = jsonObject.toString().toRequestBody(mediaType)
        if (type == "add") {
            apiUrl += "/add"
        }
        val request = Request.Builder()
            .url(apiUrl)
            .post(requestBody)
            .build()
        return request
    }
}
