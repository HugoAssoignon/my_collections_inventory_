package com.collections.my_collections_inventory.navigation

import BottomNavigationBar
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.collections.my_collections_inventory.screen.CollectionScreen
import com.collections.my_collections_inventory.screen.HomeScreen
import com.collections.my_collections_inventory.screen.MangaScreen
import com.collections.my_collections_inventory.screen.MenuScreen

@Composable
fun NavigationHost() {
    val navController = rememberNavController()
    var navigationSelectedItem by remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController, navigationSelectedItem) { index ->
                navigationSelectedItem = index
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("menu") { MenuScreen(navController) }
            composable("manga") { MangaScreen() }
            composable("anime") { }
            composable("carte") { }
            composable("home") { HomeScreen(navController) }
            composable("collection") { CollectionScreen(navController) }
        }
    }
}
