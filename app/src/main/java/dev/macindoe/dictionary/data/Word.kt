package dev.macindoe.dictionary.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "words")
data class Word(
    @PrimaryKey val id: String,
    val english: String,
    val chinese: String,
    val pinyin: String,
    val isFavorite: Boolean
)
