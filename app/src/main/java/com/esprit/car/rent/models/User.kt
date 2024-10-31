package com.esprit.car.rent.models

import androidx.room.Entity

@Entity
data class User(
    val username : String,
    val password : String,
    val rememberMe : Boolean,
)
