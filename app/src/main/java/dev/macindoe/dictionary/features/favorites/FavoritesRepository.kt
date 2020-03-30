package dev.macindoe.dictionary.features.favorites

import dev.macindoe.dictionary.data.WordDao

class FavoritesRepository(private val wordDao: WordDao) {
    fun getFavoriteWords() = wordDao.getFavoriteWords()
    suspend fun favoriteWord(wordId: String) = wordDao.favoriteWord(wordId)
    suspend fun unfavoriteWord(wordId: String) = wordDao.unfavoriteWord(wordId)
}
