package com.example.reddeusuarios.ui.users

import com.example.reddeusuarios.data.model.User

sealed interface UsersUiState {
    object Loading : UsersUiState
    data class Success(val users: List<User>) : UsersUiState
    data class Error(val message: String) : UsersUiState
}
