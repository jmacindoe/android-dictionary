package dev.macindoe.dictionary.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "words")
data class Word(
    @PrimaryKey val id: String,
    val zh: String,
    val pinyin: String,
    val en: String,
    val isFavorite: Boolean
)
