package com.collections.my_collections_inventory.screen

import android.annotation.SuppressLint
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import com.collections.my_collections_inventory.services.MangaApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun DescriptionScreen(idManga: Int) {
    var manga by remember { mutableStateOf<String?>(null) }
    val mangaApiService = MangaApiService();
    CoroutineScope(Dispatchers.Main).launch {
        manga = mangaApiService.retrieveMangabyId(idManga)
    }

    if (manga != null) {
        Text(text = manga!!)
    } else {
        Text(text = "Chargement des détails du manga...")
    }
}