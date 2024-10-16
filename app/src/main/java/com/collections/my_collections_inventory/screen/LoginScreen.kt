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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.collections.my_collections_inventory.widget.EmailBox
import com.collections.my_collections_inventory.widget.PasswordBox

@Composable
fun LoginScreen() {
    val context = LocalContext.current
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
            EmailBox()
            Spacer(modifier = Modifier.height(16.dp))
            PasswordBox()
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = {
                    Toast.makeText(context, "Tentative de connexion", Toast.LENGTH_SHORT).show()
                    // performLogin("http://localhost:8080/login")
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue, contentColor = Color.White),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text("Se connecter")
            }
            Button(
                onClick = {
                    Toast.makeText(context, "Création nouvel utilisateur", Toast.LENGTH_SHORT).show()
                    // performLogin("http://localhost:8080/login")
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red, contentColor = Color.White),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text("Création nouvel utilisateur")
            }
        }
    }
}
