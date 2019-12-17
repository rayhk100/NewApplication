package com.example.myapplication

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "route_info")
data class RouteInfo(
    @PrimaryKey @Embedded val id: RouteInfoId,
    val service:String,
    val source:String,
    val destination:String,
    val airport:String,
    val overnight:String,
    val racecourse:String,
    val special:String,
    val type:String? = null
): Parcelable

@Parcelize
data class RouteInfoId(
    val title:String,
    val bound:Int,
    @ColumnInfo(name = "service_type") val serviceType:Int
): Parcelable