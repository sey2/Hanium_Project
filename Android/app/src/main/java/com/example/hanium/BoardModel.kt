package com.example.hanium

import android.os.Parcel
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
) : Parcelable {
    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(p0: Parcel, p1: Int) {
        TODO("Not yet implemented")
    }
}

data class PostResult(
    val result: String? = null
)