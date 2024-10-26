package com.collections.my_collections_inventory.view_models

import MangaDTO
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.collections.my_collections_inventory.repositories.MangaApiRepository
import kotlinx.coroutines.launch

class MangaViewModel(
    private val mangaApiRepository: MangaApiRepository = MangaApiRepository()
) : ViewModel() {
    val manga = mutableStateOf(MangaDTO())
    val mangas = mutableStateOf<List<MangaDTO>>(emptyList())
    val topTenManga = mutableStateOf<List<MangaDTO>>(emptyList())

    fun retrieveMangaById(idManga: Int) {
        viewModelScope.launch {
            val fetchedManga = mangaApiRepository.retrieveMangaById(idManga)
            manga.value = fetchedManga ?: MangaDTO()
        }
    }

    fun retrieveAllManga() {
        viewModelScope.launch {
            val fetchedMangas = mangaApiRepository.retrieveAllManga()
            mangas.value = fetchedMangas ?: emptyList()
        }
    }

    fun retrieveTopTenManga() {
        viewModelScope.launch {
            val fetchedTopTen = mangaApiRepository.retrieveTopTenManga()
            topTenManga.value = fetchedTopTen ?: emptyList()
        }
    }
}