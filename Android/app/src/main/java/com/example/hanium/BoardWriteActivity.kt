package com.example.hanium

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.example.hanium.databinding.ActivityBoardWriteBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class BoardWriteActivity : AppCompatActivity() {

    lateinit var binding: ActivityBoardWriteBinding
    private var pic: Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBoardWriteBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_board_write)
    }

    // 갤러리에서 이미지 가져오기
    private val requestLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult())
    {
        if(it.resultCode === RESULT_OK && it.data != null){
            Log.d("TAG", "checkingLog: image launching")
            Glide.with(this)
                .load(it.data!!.data)
                .fitCenter()
                .into(binding.btnAddPhoto)
        }
        pic = it.data!!.data
    }
    fun addPhoto(view: View){
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/*"
        requestLauncher.launch(intent)
    }

    fun postContent(view: View){

        val title = binding.postTitle.text.toString()
        val content = binding.postContent.text.toString()
        val badge = if(binding.questionBadge.isChecked) "질문" else "수다"
        val date = getTime()

        val intent = Intent()
        intent.putExtra("title", title)
        intent.putExtra("content", content)
        intent.putExtra("badge", badge)
        intent.putExtra("date", date)
        intent.putExtra("picture", pic)
        intent.putExtra("id", "10")

        setResult(RESULT_OK, intent)
        finish()
    }

    private fun getTime(): String {
        val currentDateTime = Calendar.getInstance().time
        return SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.KOREA).format(currentDateTime)
    }
}