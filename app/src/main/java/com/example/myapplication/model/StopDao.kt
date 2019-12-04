package com.example.myapplication.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface StopDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStops(vararg stops: Stop)

    @Query("SELECT * FROM stop WHERE id = :id")
    fun findById(id:String): Stop
}