package com.example.simplemvvm.usecase

import androidx.lifecycle.LiveData
import com.example.simplemvvm.domainmodel.Word
import com.example.simplemvvm.repository.WordRepository

interface GetWordUseCase {
    fun getAllWords(): LiveData<List<Word>>
}

class GetWordUseCaseImpl(
    private val repository: WordRepository
): GetWordUseCase {
    override fun getAllWords(): LiveData<List<Word>> = repository.getAllWords()
}