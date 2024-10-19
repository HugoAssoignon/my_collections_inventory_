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
    @GET("{id}")
    suspend fun getAllMyCollection(@Path("id") idUser: Int): List<MangaDTO>

    @POST("add")
    suspend fun addToCollection(@Body manga: MangaDTO): MangaDTO

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

    suspend fun retrieveAllMyCollection(idUser: Int): List<MangaDTO>? {
        return withContext(Dispatchers.IO) {
            try {
                api.getAllMyCollection(idUser)
            } catch (e: IOException) {
                Log.e("ApiCalls", "Network error when calling API", e)
                null
            } catch (e: Exception) {
                Log.e("ApiCalls", "Unexpected error", e)
                null
            }
        }
    }

    suspend fun addCollection(manga: MangaDTO): MangaDTO? {
        return withContext(Dispatchers.IO) {
            try {
                api.addToCollection(manga)
            } catch (e: IOException) {
                Log.e("ApiCalls", "Network error when adding to collection", e)
                null
            } catch (e: Exception) {
                Log.e("ApiCalls", "Unexpected error", e)
                null
            }
        }
    }
}

