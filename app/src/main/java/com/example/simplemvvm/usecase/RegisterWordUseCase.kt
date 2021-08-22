package com.example.simplemvvm.usecase

import com.example.simplemvvm.domainmodel.Word
import com.example.simplemvvm.repository.WordRepository

interface RegisterWordUseCase {
    suspend fun registerWord(word: String)
}

class RegisterWordUseCaseImpl(
    private val repository: WordRepository
): RegisterWordUseCase {
    override suspend fun registerWord(word: String) {
        repository.add(Word(word))
    }
}