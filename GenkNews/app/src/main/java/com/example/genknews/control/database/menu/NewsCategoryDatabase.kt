package com.example.genknews.control.database.menu

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.genknews.control.database.relation.Converters
import com.example.genknews.control.entity.NewsCategoryDB

@Database(
    entities = [NewsCategoryDB::class],
    version = 1
)

@TypeConverters(Converters::class)
abstract class NewsCategoryDatabase : RoomDatabase() {

    abstract fun getNewsDao() : NewsCategoryDAO

    companion object{
        @Volatile
        private var instance : NewsCategoryDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                NewsCategoryDatabase::class.java,
                "news_category.db"
            ).build()
    }
}