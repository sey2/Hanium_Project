package com.example.hanium

import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import com.example.hanium.databinding.FragmentUserModifyBinding
import android.view.View
import android.view.ViewGroup
import android.os.Bundle
class UserModifyFragment : Fragment() {
    private var _binding: FragmentUserModifyBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserModifyBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}