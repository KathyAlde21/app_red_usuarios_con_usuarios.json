package com.example.reddeusuarios.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    // base_url eno lleva el nombre del archivo, porque es la parte común a todos los endpoints
    private const val BASE_URL =
        "https://raw.githubusercontent.com/KathyAlde21/usuarios.json/refs/heads/master/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL) // ¡ojo! debe terminar en "/"
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val userApi: UserApiService by lazy {
        retrofit.create(UserApiService::class.java)
    }
}