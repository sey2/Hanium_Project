package com.example.hanium

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.parcelize.Parceler

@Parcelize
data class BoardItem(
    val badge: String,
    val title: String,
    val content: String,
    val userName: String,
    val date: Long
) : Parcelable {
    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    companion object : Parceler<BoardItem> {
        override fun BoardItem.write(p0: Parcel, p1: Int) {
            TODO("Not yet implemented")
        }
        override fun create(parcel: Parcel): BoardItem = TODO()
    }
}

data class PostingData(
    var title: String,
    var content: String,
    var MainImageUrl: String
)

data class PostResult(
    var board_id: String
)