package com.example.myapplication

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RouteInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRouteInfo(vararg routeInfo: RouteInfo)

    @Query("SELECT * FROM route_info WHERE title = :title")
    fun findByTitle(title:String): List<RouteInfo>
}