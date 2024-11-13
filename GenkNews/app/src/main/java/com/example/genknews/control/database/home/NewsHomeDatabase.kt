package com.example.genknews.control.database.home

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.genknews.control.database.relation.Converters
import com.example.genknews.control.entity.NewsHomeDB

@Database(
    entities = [NewsHomeDB::class],
    version = 1
)

@TypeConverters(Converters::class)
abstract class NewsHomeDatabase : RoomDatabase() {

    abstract fun getNewsDao() : NewsHomeDAO

    companion object{
        @Volatile
        private var instance : NewsHomeDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                NewsHomeDatabase::class.java,
                "news_home.db"
            ).build()
    }
}