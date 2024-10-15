package com.collections.my_collections_inventory.data_class

data class MangaResponse(
    val data: Manga
)

data class Manga(
    val mal_id: Int,
    val url: String,
    val images: Images,
    val title: String,
    val title_english: String,
    val title_japanese: String,
    val type: String,
    val chapters: Int,
    val volumes: Int,
    val status: String,
    val synopsis: String,
    val authors: List<Author>,
    val genres: List<Genre>
)

data class Images(
    val jpg: ImageUrls,
    val webp: ImageUrls
)

data class ImageUrls(
    val image_url: String,
    val small_image_url: String,
    val large_image_url: String
)

data class Author(
    val mal_id: Int,
    val type: String,
    val name: String,
    val url: String
)

data class Genre(
    val mal_id: Int,
    val type: String,
    val name: String,
    val url: String
)