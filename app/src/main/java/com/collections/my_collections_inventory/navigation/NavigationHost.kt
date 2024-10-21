package com.collections.my_collections_inventory.navigation

import DisconnectionButton
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationHost() {
    val navController = rememberNavController()
    var navigationSelectedItem by remember { mutableIntStateOf(1) }
    var currentRoute by remember { mutableStateOf("login") }
    val noBottomNavDestinations = listOf("login", "newUser")

    DisposableEffect(navController) {
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            currentRoute = destination.route ?: "login"
        }
        navController.addOnDestinationChangedListener(listener)
        onDispose {
            navController.removeOnDestinationChangedListener(listener)
        }
    }

    Scaffold(
        bottomBar = {
            if (currentRoute !in noBottomNavDestinations) {
                BottomNavigationBar(navController, navigationSelectedItem) { index ->
                    navigationSelectedItem = index
                }
            }
        },
        topBar = {
            if (currentRoute !in noBottomNavDestinations) {
                DisconnectionButton(navController)
            }
        },
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "login",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("menu") { MenuScreen(navController) }
            composable("manga") { MangaScreen(navController) }
            composable("home") { HomeScreen(navController) }
            composable("collection") { CollectionScreen(navController) }
            composable("login") { LoginScreen(navController) }
            composable("newUser") { CreationNewUserScreen(navController) }
            composable("description_screen/{idManga}") { backStackEntry ->
                val idManga = backStackEntry.arguments?.getString("idManga")?.toInt() ?: 0
                DescriptionScreen(idManga)
            }
        }
    }
}
