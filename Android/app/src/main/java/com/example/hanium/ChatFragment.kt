package com.example.hanium

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hanium.databinding.FragmentChatBinding
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import org.json.JSONObject
import org.json.JSONArray
import java.io.IOException

class ChatFragment : Fragment() {
    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!

    private var json: MediaType = MediaType.get("application/json; charset=utf-8")
    private val apiKey: String = BuildConfig.GPT_API_KEY
    private var client = OkHttpClient()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChatBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvChat.layoutManager = LinearLayoutManager(context)

        binding.apply {
            rvChat.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            rvChat.adapter = ChatAdapter()

            ivSend.setOnClickListener {
                val userInput = etMessageInput.text.toString()

                if (userInput != "") {
                    addMessageToRecyclerView(userInput, MessageType.USER)
                    etMessageInput.text.clear()

                    sendMessageToGPT3(userInput)
                }
            }
        }
    }

    private fun addMessageToRecyclerView(message: String, type: MessageType) {
        (binding.rvChat.adapter as ChatAdapter).addMessage(message, type)
    }

    private fun sendMessageToGPT3(input: String) {
        val obj = JSONObject().apply {
            put("model", "text-davinci-003")
            put("prompt", input)
            put("max_tokens", 4000)
            put("temperature", 0)
        }

        val body = RequestBody.create(json, obj.toString())
        val request = Request.Builder()
            .url("https://api.openai.com/v1/completions")
            .header("Authorization", "Bearer $apiKey")
            .post(body)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
            }

            override fun onResponse(call: Call, response: Response) {
                activity?.runOnUiThread {
                    if (response.isSuccessful) {
                        try {
                            val responseBody = JSONObject(response.body()?.string() ?: "")
                            val choicesArray: JSONArray = responseBody.getJSONArray("choices")
                            val result = choicesArray.getJSONObject(0).getString("text")
                            addMessageToRecyclerView(result.trim(), MessageType.CHATBOT)
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    } else {
                        addMessageToRecyclerView("Failed to load response due to ${response.body()?.string()}", MessageType.CHATBOT)
                    }
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}