package com.collections.my_collections_inventory.repositories

import android.content.Context
import android.util.Log
import com.collections.my_collections_inventory.data_class.LoginRequest
import com.collections.my_collections_inventory.services.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

class UserApiRepository {

    suspend fun loginOrCreateUser(
        type: String,
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
                val response: String = if (type == "login") {
                    RetrofitInstance.userApiService.loginUser(request)
                } else {
                    RetrofitInstance.userApiService.createUser(request)
                }


                response.let { userId ->
                    val sharedPref = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
                    with(sharedPref.edit()) {
                        putString("USER_ID", userId)
                        apply()
                    }
                    Log.d("ApiCalls", "API response successful: $userId")
                    return@withContext userId
                }
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