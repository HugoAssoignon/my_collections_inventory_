package com.collections.my_collections_inventory.services


import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.FormBody
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException


class UserApiServices {
    private val apiUrl: String = "http://172.20.10.3:8080/login/add/"

    suspend fun retrieveUserByEmailAndPassword(email: String, password: String): String? {
        if (email.isBlank() || password.isBlank()) {
            Log.i("ApiCalls", "Email ou mot de passe vide ou nul.")
            return null
        }


        val client = OkHttpClient()

        // Crée un objet JSON pour envoyer les données
        val jsonObject = JSONObject()
        jsonObject.put("email", email)
        jsonObject.put("passwd", password)

        val mediaType = "application/json; charset=utf-8".toMediaTypeOrNull()
        val requestBody: RequestBody = jsonObject.toString().toRequestBody(mediaType)

        // Construire la requête POST avec le corps JSON
        val request = Request.Builder()
            .url("http://172.20.10.3:8080/login/add")  // Remplacer par l'adresse correcte
            .post(requestBody)
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


data class User(
    val email: String,
    val passwd: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (email != other.email) return false
        if (passwd != other.passwd) return false


        return true
    }



}


