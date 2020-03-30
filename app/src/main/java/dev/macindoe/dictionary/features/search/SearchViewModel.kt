package dev.macindoe.dictionary.features.search

import androidx.lifecycle.*
import dev.macindoe.dictionary.data.Word

class SearchViewModel(
    private val repository: SearchRepository
) : ViewModel() {
    private val searchQuery = MutableLiveData("")

    fun searchResults(): LiveData<List<Word>> {
        return searchQuery
            .switchMap { repository.search(it) }
    }

    fun updateSearchQuery(query: String) {
        searchQuery.value = query
    }
}