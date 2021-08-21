package com.example.simplemvvm.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.simplemvvm.domainmodel.Word
import com.example.simplemvvm.infra.database.WordDao
import com.example.simplemvvm.infra.database.toEntity
import com.example.simplemvvm.infra.database.toWord
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WordRepository(
    private val wordDao: WordDao
) {
    fun getAllWords(): LiveData<List<Word>> {
        return Transformations.map(wordDao.getAll()) { entities ->
            entities.map { entity ->
                entity.toWord()
            }
        }
    }

    suspend fun add(word: Word) {
        withContext(Dispatchers.IO) {
            wordDao.insert(word.toEntity())
        }
    }
}