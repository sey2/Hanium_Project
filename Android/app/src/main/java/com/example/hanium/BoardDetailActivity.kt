package com.example.hanium

import android.content.ContentValues.TAG
import android.content.Intent
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.hanium.databinding.ActivityBoardWriteBinding
import com.example.hanium.databinding.BoardDetailActivityBinding
import com.example.hanium.databinding.CommentsViewBinding
import java.io.Serializable

class BoardDetailActivity: AppCompatActivity() {

    private lateinit var binding: BoardDetailActivityBinding
    private lateinit var cmtBinding: CommentsViewBinding
    private lateinit var data: BoardItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = BoardDetailActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            binding.postingBadge.text = intent.getStringExtra("badge")
            binding.postingTitle.text = intent.getStringExtra("title")
            binding.postingContent.text = intent.getStringExtra("content")
            binding.postingUserName.text = intent.getStringExtra("userNm")
            binding.postingDate.text = intent.getStringExtra("date")
        } else {
//            intent.getParcelableExtra("data") as? BoardItem
        }
    }

    fun postCmt(view: View){
        val comment = binding.boardCmtWrite.text.toString()
    }
}