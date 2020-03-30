package dev.macindoe.dictionary.features.favorites

import androidx.lifecycle.*
import dev.macindoe.dictionary.data.Word
import dev.macindoe.dictionary.features.word_list.FavoritesCallback
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val repository: FavoritesRepository
) : ViewModel(), FavoritesCallback {
    fun favorites(): LiveData<List<Word>> {
        return repository.getFavoriteWords()
    }

    override fun favoriteWord(word: Word) {
        viewModelScope.launch {
            repository.favoriteWord(word.id)
        }
    }

    override fun unfavoriteWord(word: Word) {
        viewModelScope.launch {
            repository.unfavoriteWord(word.id)
        }
    }
}
