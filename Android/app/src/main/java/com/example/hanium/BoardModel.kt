package com.example.hanium

import android.os.Parcel
import android.os.Parcelable

data class BoardItem(
    val badge: String,
    val title: String,
    val content: String,
    val userName: String,
    val date: String,
    val id: String
)

data class CommentItem(
    val id: String,
    val comment: String,
    val userName: String
)

data class PostingData(
    var title: String,
    var content: String,
    var MainImageUrl: String
)

data class PostResult(
    var board_id: String
)