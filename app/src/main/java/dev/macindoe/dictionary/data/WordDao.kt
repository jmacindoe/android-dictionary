package dev.macindoe.dictionary.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WordDao {
    @Query("SELECT * FROM words ORDER BY pinyin")
    fun getWords(): LiveData<List<Word>>

    @Query("SELECT * FROM words WHERE isFavorite = 1 ORDER BY pinyin")
    fun getFavoriteWords(): LiveData<List<Word>>

    @Query("SELECT * FROM words WHERE id = :wordId")
    fun getWord(wordId: String): LiveData<Word>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(words: List<Word>)
}