import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.io.IOException

interface CollectionApiCalls {
    @GET("all")
    suspend fun getAllMyCollection(): List<MangaDTO>
}

class CollectionApiService {
    private val dataIp = DataIp()

    //TODO change the url for get collection
    private val apiUrl: String = "http://${dataIp.getIp()}/"

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

    suspend fun retrieveAllMyCollection(): List<MangaDTO>? {
        return withContext(Dispatchers.IO) {
            try {
                api.getAllMyCollection()
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

