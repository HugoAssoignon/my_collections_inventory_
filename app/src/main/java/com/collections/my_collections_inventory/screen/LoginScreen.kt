package com.collections.my_collections_inventory.screen

import EmailBox
import PasswordBox
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.collections.my_collections_inventory.services.UserApiServices


@Composable
fun LoginScreen(navController: NavController) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    var email by remember { mutableStateOf("") }
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
                text = "Connexion",
                modifier = Modifier.padding(20.dp),
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
            EmailBox(onEmailChange = { email = it })
            Spacer(modifier = Modifier.height(16.dp))
            PasswordBox(onPasswordChange = { password = it })
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = {
                    Toast.makeText(context, "Tentative de connexion", Toast.LENGTH_SHORT).show()
                    val userApiService = UserApiServices();
                    coroutineScope.launch {
                        try {

                            user = userApiService.retrieveUserByEmailAndPassword(email, password)
                            if (user != null) {
                                Toast.makeText(context, "Connexion réussie", Toast.LENGTH_SHORT)
                                    .show()
                                navController.navigate("home")
                            } else {
                                Toast.makeText(
                                    context,
                                    "Utilisateur introuvable. Réessayez.",
                                    Toast.LENGTH_SHORT
                                ).show()
                                navController.navigate("login")
                            }
                        } catch (e: Exception) {
                            Toast.makeText(
                                context,
                                "Erreur lors de la connexion: ${e.message}",
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
                Text("Se connecter")
            }
            /*Button(
                onClick = {
                    Toast.makeText(context, "Création nouvel utilisateur", Toast.LENGTH_SHORT)
                        .show()
                    //
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text("Création nouvel utilisateur")
            }*/
        }
    }
}
