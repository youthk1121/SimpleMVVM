package com.example.simplemvvm.infra.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WordDao {
    @Query("SELECT * FROM word_t")
    fun getAll(): LiveData<List<WordEntity>>

    @Insert
    suspend fun insert(wordEntity: WordEntity)
}