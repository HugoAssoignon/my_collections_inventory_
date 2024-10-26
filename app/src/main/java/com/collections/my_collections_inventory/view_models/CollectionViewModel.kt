package com.collections.my_collections_inventory.view_models

import CollectionDTO
import MangaDTO
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.collections.my_collections_inventory.repositories.CollectionApiRepository
import kotlinx.coroutines.launch

class CollectionViewModel(
    private val collectionApiRepository: CollectionApiRepository = CollectionApiRepository()
) : ViewModel() {
    val mangas = mutableStateOf<List<MangaDTO>>(emptyList())

    fun fetchUserCollection(userId: String?) {
        viewModelScope.launch {
            val fetchedManga = collectionApiRepository.retrieveUserCollection(userId)
            mangas.value = fetchedManga ?: emptyList()
        }
    }

    fun addMangaToUser(collection: CollectionDTO) {
        viewModelScope.launch {
            collectionApiRepository.addMangaToUser(collection)
        }
    }

    fun removeMangaToUser(collection: CollectionDTO) {
        viewModelScope.launch {
            collectionApiRepository.removeMangaToUser(collection)
        }
    }
}