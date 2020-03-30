package dev.macindoe.dictionary.features.definition

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.macindoe.dictionary.data.Word
import dev.macindoe.dictionary.data.WordDao
import kotlinx.coroutines.launch

class DefinitionViewModel(
    private val wordId: String,
    private val wordDao: WordDao
) : ViewModel() {
    val word: LiveData<Word> = wordDao.getWord(wordId)

    fun toggleFavorite() {
        val isFavorite = word.value?.isFavorite
        if (isFavorite != null) {
            updateFavorite(!isFavorite)
        }
    }

    private fun updateFavorite(newValue: Boolean) {
        viewModelScope.launch {
            if (newValue) {
                wordDao.favoriteWord(wordId)
            } else {
                wordDao.unfavoriteWord(wordId)
            }
        }

    }
}