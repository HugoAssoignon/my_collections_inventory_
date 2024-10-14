package com.collections.my_collections_inventory.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.collections.my_collections_inventory.R

@Composable
fun MenuScreen(navController: NavController) {
    Scaffold(
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF647AA3))
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        16.dp,
                        Alignment.CenterHorizontally
                    ),
                    modifier = Modifier.padding(16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .height(150.dp)
                            .width(150.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color(0xFFD4F4DD))
                            .clickable { navController.navigate("manga") },
                        contentAlignment = Alignment.Center
                    ) {
                        Column {
                            Icon(
                                Icons.Outlined.Book,
                                contentDescription = "Manga Icon",
                                tint = Color.Black,
                                modifier = Modifier.padding(horizontal = 8.dp)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("Manga", color = Color.Black)
                        }

                    }
                    Box(
                        modifier = Modifier
                            .height(150.dp)
                            .width(150.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color(0xFFD4F4DD)),
                        contentAlignment = Alignment.Center
                    ) {
                        Column {
                            Icon(
                                Icons.Outlined.Movie,
                                contentDescription = "Movie Icon",
                                tint = Color.Black,
                                modifier = Modifier.padding(horizontal = 8.dp)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("Animé", color = Color.Black)
                        }

                    }
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        16.dp,
                        Alignment.CenterHorizontally
                    ),
                    modifier = Modifier.padding(16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .height(150.dp)
                            .width(150.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color(0xFFD4F4DD)),
                        contentAlignment = Alignment.Center
                    ) {
                        Column {
                            Image(
                                painter = painterResource(id = R.drawable.card),
                                contentDescription = "Carte Icon",
                                modifier = Modifier.size(40.dp),
                                colorFilter = ColorFilter.tint(Color.Black)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("Carte", color = Color.Black)
                        }
                    }
                    Box(
                        modifier = Modifier
                            .height(150.dp)
                            .width(150.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color(0xFFD4F4DD)),
                        contentAlignment = Alignment.Center
                    ) {
                        Column { Text("", color = Color.Black) }

                    }
                }
            }
        }
    )

}
