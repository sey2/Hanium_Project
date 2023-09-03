package com.example.hanium

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.provider.MediaStore.Audio.Media
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.registerForActivityResult
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.hanium.databinding.ActivityBoardWriteBinding
import com.example.hanium.databinding.FragmentBoardBinding

class BoardWriteActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivityBoardWriteBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_write)
        binding = ActivityBoardWriteBinding.inflate(layoutInflater)

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_posting -> {

            }
            R.id.btn_add_photo -> {

            }
        }
    }
}