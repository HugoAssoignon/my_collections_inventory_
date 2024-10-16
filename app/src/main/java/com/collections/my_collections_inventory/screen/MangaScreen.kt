package com.collections.my_collections_inventory.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.collections.my_collections_inventory.widget.DisplayBox

@Composable
fun MangaScreen() {
    Scaffold(
        content = { padding ->
            Column(modifier = Modifier.padding(padding))
            {
                DisplayBox(
                    1,
                    "SoloLeveling",
                    "https://fr.web.img5.acsta.net/pictures/23/09/11/09/25/4087505.jpg"
                )
                // CreatedSearchBar("Rechercher un manga")
            }
        }
    )

}
