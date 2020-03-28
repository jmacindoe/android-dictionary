package dev.macindoe.dictionary.app

import dev.macindoe.dictionary.data.AppDatabase
import org.koin.dsl.module

val appModule = module {
    single { AppDatabase.getInstance(get()) }
}