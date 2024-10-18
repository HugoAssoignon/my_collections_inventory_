package com.collections.my_collections_inventory.screen

import MangaApiService
import MangaDTO
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.launch


@Composable
fun DescriptionScreen(idManga: Int) {
    val mangaApiService = remember { MangaApiService() }
    var manga by remember { mutableStateOf<MangaDTO?>(null) }
    val coroutineScope = rememberCoroutineScope()


    LaunchedEffect(Unit) {
        coroutineScope.launch {
            val fetchedManga = mangaApiService.retrieveMangaById(idManga)
            if (fetchedManga != null) {
                manga = fetchedManga
            }
        }
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
                    .height(700.dp)
                    .background(Color(0xFF334195))
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(32.dp)
            ) {

                Image(
                    painter = rememberAsyncImagePainter(model = manga?.imageUrl),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(250.dp)
                        .height(160.dp)
                        .clip(RoundedCornerShape(10.dp))
                )
                manga?.let {
                    Text(
                        it.title, color = Color.White, style = TextStyle(
                            fontSize = 20.sp, fontWeight = FontWeight.Bold
                        )
                    )
                }

                val detailsTitles =
                    listOf("Authors:", "Status:", "Number of Books:", "Description:", "Categories:")
                val detailsValues = listOf(
                    manga?.authors ?: "N/A",
                    manga?.status ?: "N/A",
                    manga?.nbBooks.toString(),
                    manga?.description ?: "N/A",
                    manga?.categories ?: "N/A"
                )

                LazyColumn(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .width(300.dp)
                        .height(350.dp)
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
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(Color(0xFFD4F4DD)),
                    modifier = Modifier
                        .width(300.dp)
                        .height(100.dp)
                ) {
                    Text("Add to my collection", color = Color.Black)
                }
            }
        }
    })
}