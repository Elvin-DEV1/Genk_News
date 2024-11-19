package com.example.genknews.control.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "category")
data class CategoryDB(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_table")
    val idTable: Int = 0,

    @ColumnInfo(name = "domain")
    val domain: String,

    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "logo")
    val logo: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "short_url")
    val shortURL: String
) : Serializable