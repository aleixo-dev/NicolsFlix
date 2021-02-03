package com.nicolas.nicolsflix.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nicolas.nicolsflix.data.db.dao.MovieDao
import com.nicolas.nicolsflix.data.db.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class NicolsDatabase : RoomDatabase() {

    abstract val movieDao: MovieDao

    companion object {
        @Volatile
        private var INSTANCE: NicolsDatabase? = null
        fun getInstance(context: Context): NicolsDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NicolsDatabase::class.java,
                    "movies-nicols"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}