package com.example.simplemvvm.infra.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.simplemvvm.domainmodel.Word

@Entity(tableName = "word_t")
data class WordEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "word")
    val word: String
)

fun WordEntity.toWord(): Word = Word(word)

fun Word.toEntity() = WordEntity(0, word)