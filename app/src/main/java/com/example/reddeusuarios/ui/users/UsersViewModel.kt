package com.example.reddeusuarios.ui.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.reddeusuarios.data.repository.UserRepository
import kotlinx.coroutines.launch

class UsersViewModel(
    private val repository: UserRepository
) : ViewModel() {

    private val _uiState = MutableLiveData<UsersUiState>()
    val uiState: LiveData<UsersUiState> = _uiState

    init {
        loadUsers()
    }

    fun loadUsers() {
        _uiState.value = UsersUiState.Loading

        viewModelScope.launch {
            val result = repository.getUsers()

            result
                .onSuccess { users ->
                    android.util.Log.d("UsersViewModel", "Usuarios recibidos: ${users.size}")
                    _uiState.value = UsersUiState.Success(users)
                }
                .onFailure { error ->
                    android.util.Log.e("UsersViewModel", "Error al obtener usuarios", error)
                    _uiState.value = UsersUiState.Error(
                        message = error.message ?: "Error al cargar usuarios"
                    )
                }
        }
    }

    class UsersViewModelFactory(
        private val repository: UserRepository
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(UsersViewModel::class.java)) {
                return UsersViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}