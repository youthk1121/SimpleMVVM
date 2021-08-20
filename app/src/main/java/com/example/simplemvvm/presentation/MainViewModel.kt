package com.example.simplemvvm.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.simplemvvm.domainmodel.Word

class MainViewModel : ViewModel() {

    val inputText: MutableLiveData<String> = MutableLiveData("")

    val addButtonEnabled: LiveData<Boolean> = Transformations.map(inputText) { text -> text.isNotEmpty() }

    private val _words: MutableLiveData<List<Word>> = MutableLiveData(emptyList())
    val words: LiveData<List<Word>>
        get() = _words

    fun onClickAddButton() {
        inputText.value?.let {
            _words.value = mutableListOf<Word>().apply {
                _words.value?.let { addAll(it) }
                add(Word(it))
            }
        }
    }
}