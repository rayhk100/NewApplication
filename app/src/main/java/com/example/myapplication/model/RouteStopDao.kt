package com.example.myapplication.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RouteStopDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRouteStops(vararg routeStop: RouteStop)

    @Query("SELECT DISTINCT title FROM route_stop")
    fun loadAllTitle(): List<String>

    @Query("SELECT bound FROM route_stop WHERE title = :title")
    fun findBoundsByTitle(title:String): List<Int>

    @Query("SELECT stop.* FROM stop INNER JOIN route_stop on stop.id = route_stop.stop_id WHERE route_stop.title = :title AND route_stop.bound = :bound ORDER BY route_stop.seq")
    fun findStopsByTitleAndBound(title:String, bound:Int): Array<Stop>
}