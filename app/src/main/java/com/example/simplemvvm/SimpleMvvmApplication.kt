package com.example.simplemvvm

import android.app.Application
import com.example.simplemvvm.infra.database.AppDatabase
import com.example.simplemvvm.repository.WordRepository
import com.example.simplemvvm.usecase.GetWordUseCase
import com.example.simplemvvm.usecase.GetWordUseCaseImpl
import com.example.simplemvvm.usecase.RegisterWordUseCase
import com.example.simplemvvm.usecase.RegisterWordUseCaseImpl

class SimpleMvvmApplication: Application() {
    val diContainer = DiContainer(this)
}

class DiContainer(application: Application) {
    private val appDatabase by lazy { AppDatabase.getInstance(application) }
    private val wordDao by lazy { appDatabase.wordDao() }
    private val wordRepository by lazy { WordRepository(wordDao) }
    val getWordUseCase: GetWordUseCase by lazy { GetWordUseCaseImpl(wordRepository) }
    val registerWordUseCase: RegisterWordUseCase by lazy { RegisterWordUseCaseImpl(wordRepository) }
}