package com.collections.my_collections_inventory.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.collections.my_collections_inventory.services.UserApiServices
import com.collections.my_collections_inventory.widget.PasswordBox
import com.collections.my_collections_inventory.widget.UsernameBox
import kotlinx.coroutines.launch

@Composable
fun CreationNewUserScreen(navController: NavController) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var user by remember { mutableStateOf<String?>(null) }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(bottom = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "New Account Creation",
                modifier = Modifier.padding(20.dp),
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
            UsernameBox(onUsernameChange = { username = it })
            Spacer(modifier = Modifier.height(16.dp))
            PasswordBox(onPasswordChange = { password = it })
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = {
                    val type = "add"
                    Toast.makeText(context, "Attempting to create user", Toast.LENGTH_SHORT).show()
                    val userApiService = UserApiServices()
                    coroutineScope.launch {
                        try {
                            user = userApiService.retrieveUserByUsernameAndPassword(context, type, username, password)
                            if (user != null) {
                                Toast.makeText(context, "creation user successful", Toast.LENGTH_SHORT)
                                    .show()
                                navController.navigate("home")
                            } else {
                                Toast.makeText(
                                    context,
                                    "Creation new user failed. Try again.",
                                    Toast.LENGTH_SHORT
                                ).show()
                                navController.navigate("login")
                            }
                        } catch (e: Exception) {
                            Toast.makeText(
                                context,
                                "Error during login: ${e.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                },

                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Blue,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text("Create user")
            }

        }
    }
}