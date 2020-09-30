package com.example.koindemo.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.koindemo.data.model.Movie

@Database(entities = [Movie::class],version = 1, exportSchema = false)
abstract class TMDBDatabase: RoomDatabase() {

    abstract fun movieDAO(): MovieDAO

    companion object{

        const val DB_NAME = "TBDB_database"

        @Volatile
        private var INSTANCE : TMDBDatabase? = null
        fun getInstance(context: Context): TMDBDatabase {
            synchronized(this){
                var instance = INSTANCE
                if(instance==null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TMDBDatabase::class.java,
                        DB_NAME
                    ).build()
                }
                return instance
            }
        }

    }


}