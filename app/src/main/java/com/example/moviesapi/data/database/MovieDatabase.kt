package com.example.moviesapi.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MovieDbModel::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    companion object {

        private var db: MovieDatabase? = null
        private const val DB_NAME = "movie.db"
        private val LOCK = Any()

        fun getInstance(context: Context): MovieDatabase {
            synchronized(LOCK) {
                db?.let { return it }
                val instance =
                    Room.databaseBuilder(
                        context,
                        MovieDatabase::class.java,
                        DB_NAME
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                db = instance
                return instance
            }
        }
    }

    abstract fun movieDao(): MovieDao
}