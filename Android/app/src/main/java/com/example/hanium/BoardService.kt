package com.example.hanium

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface BoardService {

    @Multipart
    @POST("/fishing/board")
    fun postContent(
        @Part title: String,
        @Part content: String
    )

    @GET("/fishing/board/all")
    fun getBoardData(
    ): Call<PostingData>

}