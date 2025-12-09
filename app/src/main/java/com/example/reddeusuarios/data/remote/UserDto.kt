package com.example.reddeusuarios.data.remote

import com.example.reddeusuarios.data.model.User
import com.google.gson.annotations.SerializedName

data class UserDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String
) {
    fun toDomain(): User = User(
        id = id,
        name = name,
        email = email
    )
}
