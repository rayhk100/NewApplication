package com.example.myapplication.model

import androidx.room.*

@Entity(
    tableName = "route_stop",
    foreignKeys = [
        ForeignKey(
            entity = Stop::class,
            parentColumns = ["id"],
            childColumns = ["stop_id"]
        )
    ]
) data class RouteStop(
    @PrimaryKey @Embedded val id:RouteStopId,
    @ColumnInfo(name = "stop_id") val stopId:String
)

data class RouteStopId(
    val title:String,
    val bound:Int,
    val seq:Int
)