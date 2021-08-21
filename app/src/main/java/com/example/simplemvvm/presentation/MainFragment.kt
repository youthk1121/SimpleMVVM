package com.example.simplemvvm.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simplemvvm.SimpleMvvmApplication
import com.example.simplemvvm.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModels{
        MainViewModel.Factory((requireActivity().application as SimpleMvvmApplication).diContainer.wordRepository)
    }

    private lateinit var binding: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        val wordListAdapter = WordListAdapter()
        binding.recycler.adapter = wordListAdapter

        viewModel.words.observe(viewLifecycleOwner) { words ->
            wordListAdapter.submitList(words)
        }
    }

}