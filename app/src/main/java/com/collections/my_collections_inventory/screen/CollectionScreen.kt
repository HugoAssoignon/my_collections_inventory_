package com.collections.my_collections_inventory.screen

import android.content.Context
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.collections.my_collections_inventory.view_models.CollectionViewModel
import com.collections.my_collections_inventory.widget.CreatedSearchBar
import com.collections.my_collections_inventory.widget.DisplayBox

@Composable
fun CollectionScreen(navController: NavController, collectionViewModel: CollectionViewModel) {
    val context = LocalContext.current
    val mangas by collectionViewModel.mangas
    val sharedPref = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    val userId = sharedPref.getString("USER_ID", null)

    LaunchedEffect(Unit) {
        collectionViewModel.fetchUserCollection(userId)
    }

    Scaffold(modifier =
    Modifier.fillMaxSize(), content = { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF647AA3))
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item {
                Text(
                    text = "My Collection",
                    modifier = Modifier
                        .padding(16.dp)
                        .background(Color.LightGray)
                )
            }
            item {
                CreatedSearchBar("Search manga")
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
                            manga.id, manga.title, manga.imageUrl, navController
                        )
                    }
                }
            }
        }
    })

}
