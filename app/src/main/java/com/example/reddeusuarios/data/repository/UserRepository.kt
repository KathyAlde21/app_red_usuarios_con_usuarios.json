package com.example.reddeusuarios.data.repository

import com.example.reddeusuarios.data.model.User
import com.example.reddeusuarios.data.remote.UserApiService

class UserRepository(
    private val api: UserApiService
) {

    suspend fun getUsers(): Result<List<User>> {
        return try {
            val dtoList = api.getUsers()
            val users = dtoList.map { it.toDomain() }
            Result.success(users)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
