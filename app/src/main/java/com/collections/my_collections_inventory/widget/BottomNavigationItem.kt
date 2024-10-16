package com.collections.my_collections_inventory.widget

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationItem(
    val label : String = "",
    val icon : ImageVector = Icons.Filled.Home,
    val route : String = ""
) {

    fun bottomNavigationItems() : List<BottomNavigationItem> {
        return listOf(
            BottomNavigationItem(
                label = "Collection",
                icon = Icons.Filled.Book,
                route = "collection"
            ),
            BottomNavigationItem(
                label = "Home",
                icon = Icons.Filled.Home,
                route = "home"
            ),
            BottomNavigationItem(
                label = "Menu",
                icon = Icons.Filled.AddBox,
                route = "menu"
            ),
        )
    }
}

