package com.example.simplemvvm.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.simplemvvm.databinding.LayoutListItemBinding
import com.example.simplemvvm.domainmodel.Word

class WordListAdapter : ListAdapter<Word, WordViewHolder>(
    object : DiffUtil.ItemCallback<Word>() {
        override fun areItemsTheSame(old: Word, new: Word): Boolean = old == new
        override fun areContentsTheSame(old: Word, new: Word): Boolean = old.word == new.word
    }
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val binding = LayoutListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WordViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class WordViewHolder(
    private val binding: LayoutListItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(word: Word) {
        binding.word.text = word.word
    }
}