package com.collections.my_collections_inventory.view_models

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.collections.my_collections_inventory.repositories.UserApiRepository
import kotlinx.coroutines.launch

class UserViewModel(
    private val userApiRepository: UserApiRepository = UserApiRepository()
) : ViewModel() {
    var userId = mutableStateOf("")

    fun loginOrCreateUser(type: String, context: Context, username: String, password: String) {
        viewModelScope.launch {
            val responseUserId =
                userApiRepository.loginOrCreateUser(type, context, username, password)
            userId.value = responseUserId ?: "0"
        }
    }

}