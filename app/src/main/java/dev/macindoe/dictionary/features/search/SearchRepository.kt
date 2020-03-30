package dev.macindoe.dictionary.features.search

import dev.macindoe.dictionary.data.WordDao

class SearchRepository(private val wordDao: WordDao) {
    fun search(query: String) = wordDao.search(query)
}