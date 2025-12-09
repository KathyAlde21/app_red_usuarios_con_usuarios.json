package com.example.reddeusuarios.data.remote

import retrofit2.http.GET

interface UserApiService {

    // Solo el nombre del archivo, porque el resto está en BASE_URL
    @GET("usuarios.json")
    suspend fun getUsers(): List<UserDto>
}