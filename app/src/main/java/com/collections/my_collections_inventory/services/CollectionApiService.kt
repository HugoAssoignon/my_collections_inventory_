import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.io.IOException

interface CollectionApiCalls {
    @GET("{id}/get")
    suspend fun retrieveUserCollection(@Path("id") userId: String?): List<MangaDTO>

    @POST("add")
    suspend fun addMangaToUser(@Body collection: CollectionDTO)

    @POST("delete")
    suspend fun removeMangaToUser(@Body collection: CollectionDTO)
}

class CollectionApiService {
    private val dataIp = DataIp()
    private val apiUrl: String = "http://${dataIp.getIp()}/collection/"

    private val api: CollectionApiCalls by lazy {
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
            .create(CollectionApiCalls::class.java)
    }

    suspend fun retrieveUserCollection(userId: String?): List<MangaDTO>? {
        return withContext(Dispatchers.IO) {
            try {
                api.retrieveUserCollection(userId)
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
                api.addMangaToUser(collection)
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
                api.removeMangaToUser(collection)
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

