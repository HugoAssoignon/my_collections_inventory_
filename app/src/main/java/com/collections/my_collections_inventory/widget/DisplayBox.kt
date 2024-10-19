package com.collections.my_collections_inventory.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter


@Composable
fun DisplayBox(idManga: Int, mangaName: String, mangaPic: String, navController: NavController) {
    Box(
        modifier = Modifier
            .height(155.dp)
            .width(178.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFFD4F4DD))
            .clickable { navController.navigate("description_screen/$idManga") },
        contentAlignment = Alignment.BottomCenter
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = mangaPic),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
                .padding(5.dp)
                .width(125.dp)
                .height(18.dp),
            contentAlignment = Alignment.Center
        )
        { Text(mangaName, color = Color.Black) }
    }
}