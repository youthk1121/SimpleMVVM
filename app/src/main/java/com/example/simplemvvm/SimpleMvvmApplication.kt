package com.example.simplemvvm

import android.app.Application
import com.example.simplemvvm.infra.database.AppDatabase
import com.example.simplemvvm.repository.WordRepository

class SimpleMvvmApplication: Application() {
    val diContainer = DiContainer(this)
}

class DiContainer(application: Application) {
    private val appDatabase by lazy { AppDatabase.getInstance(application) }
    private val wordDao by lazy { appDatabase.wordDao() }
    val wordRepository by lazy { WordRepository(wordDao) }
}