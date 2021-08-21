package com.example.simplemvvm.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.simplemvvm.repository.WordRepository
import com.example.simplemvvm.domainmodel.Word
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: WordRepository
) : ViewModel() {

    val inputText: MutableLiveData<String> = MutableLiveData("")

    val addButtonEnabled: LiveData<Boolean> = Transformations.map(inputText) { text -> text.isNotEmpty() }

    val words: LiveData<List<Word>> = repository.getAllWords()

    fun onClickAddButton() {
        inputText.value?.let {
            viewModelScope.launch {
                repository.add(Word(it))
            }
        }
        inputText.value = ""
    }

    class Factory(private val repository: WordRepository): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}