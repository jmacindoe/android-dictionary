package dev.macindoe.dictionary.features.definition

import androidx.lifecycle.MutableLiveData
import dev.macindoe.dictionary.coroutineGroup
import dev.macindoe.dictionary.data.Word
import io.mockk.*
import org.spekframework.spek2.Spek

object DefinitionViewModelTest : Spek({
    coroutineGroup("DefinitionViewModel") {
        it("favorites a word") {
            // Given
            val wordId = "1"
            val repo = mockk<DefinitionRepository>()
            every { repo.getWord(wordId) } returns MutableLiveData(Word(wordId, "en", "cn", "py", false))
            coEvery { repo.favoriteWord(wordId) } returns Unit
            val viewModel = DefinitionViewModel(wordId, repo)

            // When
            viewModel.toggleFavorite()

            // Then
            coVerify { repo.favoriteWord(wordId) }
        }
    }
})