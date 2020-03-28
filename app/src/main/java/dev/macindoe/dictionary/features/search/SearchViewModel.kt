package dev.macindoe.dictionary.features.search

import androidx.lifecycle.*
import dev.macindoe.dictionary.data.Word
import dev.macindoe.dictionary.data.WordDao

class SearchViewModel(
    private val wordDao: WordDao
) : ViewModel() {
    private val searchQuery = MutableLiveData("")

    fun searchResults(): LiveData<List<Word>> {
        return searchQuery
            .switchMap { wordDao.search(it) }
    }

    fun updateSearchQuery(query: String) {
        searchQuery.value = query
    }
}