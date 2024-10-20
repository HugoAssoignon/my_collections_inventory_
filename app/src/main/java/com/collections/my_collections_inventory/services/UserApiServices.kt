package com.collections.my_collections_inventory.services

import DataIp
import android.content.Context
import android.util.Log
import com.collections.my_collections_inventory.data_class.LoginRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApiCalls {
    @POST("login")
    suspend fun loginUser(@Body request: LoginRequest): String

    @POST("login/add")
    suspend fun createUser(@Body request: LoginRequest): String
}

class UserApiServices {
    private val dataIp = DataIp()
    private val apiUrl: String = "http://${dataIp.getIp()}/"

    private val api: UserApiCalls by lazy {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        Retrofit.Builder()
            .baseUrl(apiUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserApiCalls::class.java)
    }

    suspend fun loginUser(
        context: Context,
        username: String,
        password: String
    ): String? {
        if (username.isBlank() || password.isBlank()) {
            Log.d("ApiCalls", "Username or password is empty or null.")
            return null
        }
        val request = LoginRequest(username, password)

        return withContext(Dispatchers.IO) {
            try {
                val response = api.loginUser(request)

                response?.let { id->
                    val sharedPref = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
                    with(sharedPref.edit()) {
                        putString("USER_ID", id)
                        apply()
                    }
                    Log.d("ApiCalls", "API response successful: $id")
                    return@withContext id
                }
                Log.d("ApiCalls", "Error in API response")
                return@withContext null
            } catch (e: IOException) {
                Log.e("ApiCalls", "Network error when calling API", e)
                return@withContext null
            } catch (e: Exception) {
                Log.e("ApiCalls", "Unexpected error when calling API", e)
                return@withContext null
            }
        }
    }

    suspend fun createUser(
        context: Context,
        username: String,
        password: String
    ): String? {
        if (username.isBlank() || password.isBlank()) {
            Log.d("ApiCalls", "Username or password is empty or null.")
            return null
        }
        val request = LoginRequest(username, password)

        return withContext(Dispatchers.IO) {
            try {
                val responseString = api.createUser(request)

                responseString?.let { id->
                    val sharedPref = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
                    with(sharedPref.edit()) {
                        putString("USER_ID", id)
                        apply()
                    }
                    Log.d("ApiCalls", "API response successful: $id")
                    return@withContext id
                }
                Log.d("ApiCalls", "Error in API response")
                return@withContext null
            } catch (e: IOException) {
                Log.e("ApiCalls", "Network error when calling API", e)
                return@withContext null
            } catch (e: Exception) {
                Log.e("ApiCalls", "Unexpected error when calling API", e)
                return@withContext null
            }
        }
    }
}
