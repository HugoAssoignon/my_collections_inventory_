package com.collections.my_collections_inventory.screen

import CollectionDTO
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.collections.my_collections_inventory.view_models.CollectionViewModel
import com.collections.my_collections_inventory.view_models.MangaViewModel


@Composable
fun DescriptionScreen(
    idManga: Int,
    collectionViewModel: CollectionViewModel,
    mangaViewModel: MangaViewModel
) {
    val context = LocalContext.current
    val manga by mangaViewModel.manga
    val sharedPref = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    val userId = sharedPref.getString("USER_ID", null)


    LaunchedEffect(Unit) {
        mangaViewModel.retrieveMangaById(idManga)
    }

    Scaffold(modifier = Modifier.fillMaxSize(), content = { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF647AA3))
                .padding(padding), contentAlignment = Alignment.Center
        ) {

            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .width(380.dp)
                    .height(800.dp)
                    .background(Color(0xFF334195))
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(25.dp)
            ) {

                Image(
                    painter = rememberAsyncImagePainter(model = manga.imageUrl),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(250.dp)
                        .height(160.dp)
                        .clip(RoundedCornerShape(10.dp))
                )
                manga.let {
                    Text(
                        it.title, color = Color.White, style = TextStyle(
                            fontSize = 20.sp, fontWeight = FontWeight.Bold
                        )
                    )
                }

                val detailsTitles =
                    listOf("Authors:", "Status:", "Description:", "Categories:")
                val detailsValues = listOf(
                    manga.author,
                    manga.status,
                    manga.description,
                    manga.category
                )

                LazyColumn(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .width(300.dp)
                        .height(320.dp)
                        .background(Color(0xFFD8D4F4))
                        .padding(16.dp),
                ) {

                    itemsIndexed(detailsValues) { index, value ->
                        Text(
                            detailsTitles[index], color = Color.Black, fontWeight = FontWeight.Bold
                        )
                        Text(value, color = Color.Black)
                    }
                }
                Button(
                    onClick = {
                        collectionViewModel.addMangaToUser(
                            CollectionDTO(userId, manga.id)
                        )
                        Toast.makeText(
                            context,
                            "Added to your collection",
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    colors = ButtonDefaults.buttonColors(Color(0xFFD4F4DD)),
                    modifier = Modifier
                        .width(200.dp)
                        .height(35.dp)
                ) {
                    Text("Add to my collection", color = Color.Black)
                }
                Button(
                    onClick = {
                        collectionViewModel.removeMangaToUser(
                            CollectionDTO(userId, manga.id)
                        )
                        Toast.makeText(
                            context,
                            "removed from your collection",
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    colors = ButtonDefaults.buttonColors(Color(0xFFD4F4DD)),
                    modifier = Modifier
                        .width(220.dp)
                        .height(35.dp)
                ) {
                    Text("Remove from my collection", color = Color.Black)
                }
            }
        }
    })
}