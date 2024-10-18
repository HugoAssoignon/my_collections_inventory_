package com.collections.my_collections_inventory.services

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


class UserApiServices {
    private var apiUrl: String = "http://192.168.208.125:8080/login"


    suspend fun retrieveUserByUsernameAndPassword(type: String, username: String, password: String): String? {
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
                    response.body?.string().also {
                        Log.d("ApiCalls", "API response successful: $it")
                    }
                } else {
                    Log.d("ApiCalls", "Error calling API, code: ${response.code}")
                    null
                }
            } catch (e: IOException) {
                Log.e("ApiCalls", "Error when calling API : ${e.message}", e)
                null
            }
        }
    }


    private fun getRequest(type: String, username: String, password: String): Request {
        val jsonObject = JSONObject()
        jsonObject.put("username", username)
        jsonObject.put("password", password)


        val mediaType = "application/json; charset=utf-8".toMediaTypeOrNull()
        val requestBody: RequestBody = jsonObject.toString().toRequestBody(mediaType)
        if(type=="add"){
            apiUrl += "/add";
        }
        val request = Request.Builder()
            .url(apiUrl)
            .post(requestBody)
            .build()
        return request
    }
}
