package com.example.myapplication.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.RouteInfo
import com.example.myapplication.RouteInfoDao

@Database(entities = [RouteStop::class, Stop::class, RouteInfo::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun routeStopDao(): RouteStopDao
    abstract fun stopDao(): StopDao
    abstract fun routeInfoDao(): RouteInfoDao

    companion object {
        private var instance:AppDatabase? = null
        fun get(context: Context? = null):AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context!!,
                    AppDatabase::class.java, "appdb"
                ).build()
                return instance!!
            }
            return instance!!
        }
    }
}