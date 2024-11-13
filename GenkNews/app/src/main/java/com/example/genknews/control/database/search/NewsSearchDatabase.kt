package com.example.genknews.control.database.search

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.genknews.control.database.relation.Converters
import com.example.genknews.control.entity.NewsSearchDB

@Database(
    entities = [NewsSearchDB::class],
    version = 1
)

@TypeConverters(Converters::class)
abstract class NewsSearchDatabase : RoomDatabase() {

    abstract fun getNewsDao() : NewsSearchDAO

    companion object{
        @Volatile
        private var instance : NewsSearchDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                NewsSearchDatabase::class.java,
                "news_search.db"
            ).build()
    }
}