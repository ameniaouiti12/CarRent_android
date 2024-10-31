package com.esprit.car.rent.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "car",
    indices = [Index(value = ["constructor", "model"], unique = true)])
data class Car(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    @ColumnInfo(name = "constructor")
    val constructor : String?,
    @ColumnInfo(name = "model")
    val model : String?,
    @ColumnInfo(name = "price")
    val price :Float?,
    @ColumnInfo(name = "availability")
    val availability:String?,
    @ColumnInfo(name = "isFavorite")
    var isFavorite :Boolean,

)
