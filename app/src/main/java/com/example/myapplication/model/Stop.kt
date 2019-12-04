package com.example.myapplication.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stop")
data class Stop(
    @PrimaryKey(autoGenerate = false) val id:String,
    val name:String,
    val location:String,
    val lat:Double,
    val lng:Double
)