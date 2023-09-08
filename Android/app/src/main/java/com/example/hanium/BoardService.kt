package com.example.hanium

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface BoardService {

    @GET("/fishing/board")
    fun getBoardData(): Call<PostingData>

}