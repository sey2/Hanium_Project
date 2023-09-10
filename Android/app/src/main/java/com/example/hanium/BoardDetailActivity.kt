package com.example.hanium

import android.content.ContentValues.TAG
import android.content.Intent
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.os.PersistableBundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.hanium.databinding.ActivityBoardWriteBinding
import com.example.hanium.databinding.BoardDetailActivityBinding
import java.io.Serializable

class BoardDetailActivity: AppCompatActivity() {

    private lateinit var binding: BoardDetailActivityBinding
    private lateinit var data: BoardItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = BoardDetailActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//            intent.getParcelableExtra("data", BoardItem::class.java)
            binding.postingBadge.text = intent.getStringExtra("badge")
            binding.postingTitle.text = intent.getStringExtra("title")
            binding.postingContent.text = intent.getStringExtra("content")
            binding.postingUserName.text = intent.getStringExtra("userNm")
            binding.postingDate.text = intent.getStringExtra("date")
        } else {
//            intent.getParcelableExtra("data") as? BoardItem
        }

        // 데이터 입력
//        binding.postingBadge.text = data?.badge
//        binding.postingTitle.text = data?.title
//        binding.postingContent.text = data?.content
    }
}