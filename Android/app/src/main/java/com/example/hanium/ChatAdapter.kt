package com.example.hanium

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hanium.databinding.ItemChatMessageBinding

class ChatAdapter() :
    RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    private val messages = mutableListOf<ChatMessage>()
    override fun getItemViewType(position: Int): Int {
        return messages[position].type.ordinal
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val binding =
            ItemChatMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(messages[position])
    }

    override fun getItemCount() = messages.size


    fun addMessage(message: String, type: MessageType) {
        val chatMessage = ChatMessage(message, type)
        messages.add(chatMessage)
        notifyItemInserted(messages.size - 1)
    }

    inner class ChatViewHolder(val binding: ItemChatMessageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(chatMessage: ChatMessage) {
            binding.apply {
               item = chatMessage
                Log.d("test", "${chatMessage.type} ${chatMessage.message}")
            }
        }
    }
}
