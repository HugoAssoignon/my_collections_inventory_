import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.io.IOException

interface MangaApiCalls {
    @GET("{id}")
    suspend fun retrieveMangaById(@Path("id") idManga: Int): MangaDTO

    @GET("top-ten")
    suspend fun getTopTenManga(): List<MangaDTO>

    @GET("all")
    suspend fun getAllManga(): List<MangaDTO>
}

class MangaApiService {
    private val dataIp = DataIp()
    private val apiUrl: String = "http://${dataIp.getIp()}/manga/"

    private val api: MangaApiCalls by lazy {
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
            .create(MangaApiCalls::class.java)
    }

    suspend fun retrieveMangaById(idManga: Int): MangaDTO? {
        return withContext(Dispatchers.IO) {
            try {
                api.retrieveMangaById(idManga)
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
                api.getAllManga()
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
                api.getTopTenManga()
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

