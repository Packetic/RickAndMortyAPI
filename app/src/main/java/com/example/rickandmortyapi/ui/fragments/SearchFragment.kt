package com.example.rickandmortyapi.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.rickandmortyapi.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnFind.setOnClickListener {
            // TODO: переписать нормально, это кринж
            if (binding.numId.text != null) {
                val text = binding.numId.text.toString().toInt()
                if (text in 1..20) {
                    // TODO: go to CharacterFragment with text as argument
                }
                else {
                    Toast.makeText(context, "Input correct number", Toast.LENGTH_LONG).show()
                }
            }
            else {
                Toast.makeText(context, "Input correct number", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}