package com.collections.my_collections_inventory.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.collections.my_collections_inventory.data_class.MangaResponse
import com.collections.my_collections_inventory.services.MangaApiService
import com.google.gson.Gson
import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp

import coil.compose.rememberAsyncImagePainter


@Composable
fun DescriptionScreen(idManga: Int) {
    val mangaApiService = MangaApiService()
    var mangaResponse by remember { mutableStateOf<MangaResponse?>(null) }
    var jpgImageUrl by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(idManga) {
        val jsonResponse = mangaApiService.retrieveMangabyId(idManga)
        mangaResponse = Gson().fromJson(jsonResponse, MangaResponse::class.java)
        jpgImageUrl = mangaResponse?.data?.images?.jpg?.large_image_url
    }
    Text(text = "$jpgImageUrl")
    if (jpgImageUrl != null) {
        Scaffold(
            content = { padding ->
                Image(
                    painter = rememberAsyncImagePainter(model = "https://fr.web.img5.acsta.net/pictures/23/09/11/09/25/4087505.jpg"),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(200.dp)
                )
            }
        )
    } else {
        Text(text = "Loading image...")
    }
}