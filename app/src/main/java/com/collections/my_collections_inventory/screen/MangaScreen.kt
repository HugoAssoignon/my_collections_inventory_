package com.collections.my_collections_inventory.screen

import MangaApiService
import MangaDTO
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.collections.my_collections_inventory.widget.CreatedSearchBar
import com.collections.my_collections_inventory.widget.DisplayBox
import kotlinx.coroutines.launch

@Composable
fun MangaScreen() {
    val mangaApiService = remember { MangaApiService() }
    var mangas by remember { mutableStateOf<List<MangaDTO>>(emptyList()) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            val fetchedMangas = mangaApiService.retrieveAllManga()
            if (fetchedMangas != null) {
                mangas = fetchedMangas
            }
        }
    }
    Scaffold(modifier = Modifier.fillMaxSize(), content = { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF647AA3))
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item { Text("Univers Manga") }
            item {
                CreatedSearchBar("Rechercher un manga")
            }

            items(mangas.chunked(2)) { pairOfMangas ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        16.dp, Alignment.CenterHorizontally
                    ), modifier = Modifier
                        .wrapContentHeight()
                        .padding(top = 10.dp)
                ) {
                    pairOfMangas.forEach { manga ->
                        DisplayBox(
                            manga.id, manga.title, manga.imageUrl
                        )
                    }
                }
            }
        }
    })
}