package dev.macindoe.dictionary.features.favorites

import androidx.lifecycle.*
import dev.macindoe.dictionary.data.Word
import dev.macindoe.dictionary.data.WordDao
import dev.macindoe.dictionary.features.word_list.FavoritesCallback
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val wordDao: WordDao
) : ViewModel(), FavoritesCallback {
    fun favorites(): LiveData<List<Word>> {
        return wordDao.getFavoriteWords()
    }

    override fun favoriteWord(word: Word) {
        viewModelScope.launch {
            wordDao.favoriteWord(word.id)
        }
    }

    override fun unfavoriteWord(word: Word) {
        viewModelScope.launch {
            wordDao.unfavoriteWord(word.id)
        }
    }
}
