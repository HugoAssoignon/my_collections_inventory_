import android.content.Context
import android.util.Log
import com.collections.my_collections_inventory.data_class.LoginRequest
import com.collections.my_collections_inventory.data_class.LoginResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

import retrofit2.http.Body
import retrofit2.http.POST

interface UserApiCalls {
    @POST("/login")
    suspend fun loginUser(@Body request: LoginRequest): LoginResponse

    @POST("/login/add")
    suspend fun createUser(@Body request: LoginRequest): LoginResponse
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

    suspend fun retrieveUserByUsernameAndPassword(
        context: Context,
        type: String,
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
                val response = if (type == "login") {
                    api.loginUser(request)
                } else {
                    api.createUser(request)
                }

                if (response.user_id != null) {
                    val sharedPref = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
                    with(sharedPref.edit()) {
                        putString("USER_ID", response.user_id)
                        apply()
                    }
                    Log.d("ApiCalls", "API response successful: ${response.user_id}")
                    return@withContext response.user_id
                } else {
                    Log.d("ApiCalls", "Error in API response:")
                    return@withContext null
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
