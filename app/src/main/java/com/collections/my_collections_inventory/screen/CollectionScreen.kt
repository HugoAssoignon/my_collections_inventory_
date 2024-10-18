package com.collections.my_collections_inventory.screen

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.collections.my_collections_inventory.widget.CreatedSearchBar
import com.collections.my_collections_inventory.widget.DisplayBox

@Composable
fun CollectionScreen(navController: NavController) {
    //remplir mangas avec l'appel api
    val mangas = listOf(
        "SoloLeveling" to "https://fr.web.img5.acsta.net/pictures/23/09/11/09/25/4087505.jpg",
        "Naruto" to "https://fr.web.img5.acsta.net/pictures/23/09/11/09/25/4087505.jpg",
        "Attack on Titan" to "https://fr.web.img5.acsta.net/pictures/23/09/11/09/25/4087505.jpg",
        "One Piece" to "https://fr.web.img5.acsta.net/pictures/23/09/11/09/25/4087505.jpg"
    )

    Scaffold(modifier =
    Modifier.fillMaxSize(), content = { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF647AA3))
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item { Text("Ma collection") }
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
                    for ((title, imageUrl) in pairOfMangas) {
                        DisplayBox(
                            1, title, imageUrl, navController
                        )
                    }
                }
            }
        }
    })

}
