package dev.macindoe.dictionary.features.definition

import dev.macindoe.dictionary.data.WordDao

class DefinitionRepository(private val wordDao: WordDao) {
    fun getWord(wordId: String) = wordDao.getWord(wordId)
    suspend fun favoriteWord(wordId: String) = wordDao.favoriteWord(wordId)
    suspend fun unfavoriteWord(wordId: String) = wordDao.unfavoriteWord(wordId)
}