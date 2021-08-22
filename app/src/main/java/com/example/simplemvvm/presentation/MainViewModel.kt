package com.example.simplemvvm.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.simplemvvm.domainmodel.Word
import com.example.simplemvvm.usecase.GetWordUseCase
import com.example.simplemvvm.usecase.RegisterWordUseCase
import kotlinx.coroutines.launch

class MainViewModel(
    getWordUseCase: GetWordUseCase,
    private val registerWordUseCase: RegisterWordUseCase
) : ViewModel() {

    val inputText: MutableLiveData<String> = MutableLiveData("")

    val addButtonEnabled: LiveData<Boolean> =
        Transformations.map(inputText) { text -> text.isNotEmpty() }

    val words: LiveData<List<Word>> = getWordUseCase.getAllWords()

    fun onClickAddButton() {
        inputText.value?.let {
            viewModelScope.launch {
                registerWordUseCase.registerWord(it)
            }
        }
        inputText.value = ""
    }

    class Factory(
        private val getWordUseCase: GetWordUseCase,
        private val registerWordUseCase: RegisterWordUseCase
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(getWordUseCase, registerWordUseCase) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}