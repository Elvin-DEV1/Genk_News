package com.example.genknews.control.database.category

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.genknews.control.database.relation.Converters
import com.example.genknews.control.entity.CategoryDB

@Database(
    entities = [CategoryDB::class],
    version = 1
)

@TypeConverters(Converters::class)
abstract class CategoryDatabase : RoomDatabase() {

    abstract fun getNewsDao() : CategoryDAO

    companion object{
        @Volatile
        private var instance : CategoryDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                CategoryDatabase::class.java,
                "category.db"
            ).build()
    }
}