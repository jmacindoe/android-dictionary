package dev.macindoe.dictionary.app

import dev.macindoe.dictionary.data.AppDatabase
import dev.macindoe.dictionary.features.definition.DefinitionRepository
import dev.macindoe.dictionary.features.definition.DefinitionViewModel
import dev.macindoe.dictionary.features.favorites.FavoritesRepository
import dev.macindoe.dictionary.features.favorites.FavoritesViewModel
import dev.macindoe.dictionary.features.search.SearchRepository
import dev.macindoe.dictionary.features.search.SearchViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { AppDatabase.getInstance(get()) }
    single { get<AppDatabase>().wordDao() }
    single { SearchRepository(get()) }
    single { FavoritesRepository(get()) }
    single { DefinitionRepository(get()) }

    viewModel { SearchViewModel(get()) }
    viewModel { FavoritesViewModel(get()) }

    viewModel { (wordId: String) -> DefinitionViewModel(wordId, get()) }
}