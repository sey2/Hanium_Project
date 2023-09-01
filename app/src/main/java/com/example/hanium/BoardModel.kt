package com.example.hanium

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BoardItem(
    val badge: String,
    val title: String,
    val content: String,
    val userName: String,
    val date: Long,
    val hits: Int
) : Parcelable

data class PostResult(
    val result: String? = null
)