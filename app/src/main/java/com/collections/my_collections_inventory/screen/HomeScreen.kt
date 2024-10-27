package com.collections.my_collections_inventory.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.collections.my_collections_inventory.view_models.MangaViewModel
import com.collections.my_collections_inventory.widget.CreatedSearchBar
import com.collections.my_collections_inventory.widget.DisplayBox

@Composable
fun HomeScreen(navController: NavController, mangaViewModel: MangaViewModel) {
    val mangas by mangaViewModel.topTenManga

    LaunchedEffect(Unit) {
        mangaViewModel.retrieveTopTenManga()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF647AA3))
                    .padding(all = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                Text("All Universe", color = Color.White)
                CreatedSearchBar("Search manga")
                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text("Manga top 10", color = Color.White)
                    LazyRow(
                        modifier = Modifier.wrapContentHeight(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        items(mangas) { manga ->
                            DisplayBox(
                                manga.id, manga.title, manga.imageUrl, navController
                            )
                        }
                    }
                }
            }
        }
    )

}
