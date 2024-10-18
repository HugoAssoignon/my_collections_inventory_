package com.collections.my_collections_inventory.navigation

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
import com.collections.my_collections_inventory.screen.CreationNewUserScreen
import com.collections.my_collections_inventory.screen.DescriptionScreen
import com.collections.my_collections_inventory.screen.HomeScreen
import com.collections.my_collections_inventory.screen.LoginScreen
import com.collections.my_collections_inventory.screen.MangaScreen
import com.collections.my_collections_inventory.screen.MenuScreen
import com.collections.my_collections_inventory.widget.BottomNavigationBar

@Composable
fun NavigationHost() {
    val navController = rememberNavController()
    var navigationSelectedItem by remember { mutableStateOf(1) }
    val noBottomNavDestinations = listOf("login", "newUser")
    Scaffold(
        bottomBar = {
            if (navController.currentDestination?.route !in noBottomNavDestinations) {
                BottomNavigationBar(navController, navigationSelectedItem) { index ->
                    navigationSelectedItem = index
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "login",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("menu") { MenuScreen(navController) }
            composable("manga") { MangaScreen(navController) }
            composable("anime") { }
            composable("carte") { }
            composable("home") { HomeScreen(navController) }
            composable("collection") { CollectionScreen(navController) }
            composable("login") { LoginScreen(navController) }
            composable("description_screen/{idManga}") { backStackEntry ->
                val idManga = backStackEntry.arguments?.getString("idManga")?.toInt() ?: 0
                DescriptionScreen(idManga)
            }
            composable("login") { LoginScreen(navController) }
            composable("description_screen/{idManga}") { backStackEntry ->
                val idManga = backStackEntry.arguments?.getString("idManga")?.toInt() ?: 0
                DescriptionScreen(idManga)
            }
            composable("newUser") { CreationNewUserScreen(navController) }
            }
        }
    }
}
