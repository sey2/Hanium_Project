package com.example.hanium

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.hanium.databinding.FragmentQuoteBinding

class QuoteFragment : Fragment() {

    private var _binding: FragmentQuoteBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            val webViewSetting = wvQuote.settings
            webViewSetting.javaScriptEnabled = true
            wvQuote.setInitialScale(90)
            wvQuote.loadUrl("https://www.susansijang.co.kr/nsis/miw/ko/info/miw3110")

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}