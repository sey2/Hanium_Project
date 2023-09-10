package com.example.hanium

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface BoardService {

    @POST("/fishing/board")
    fun postContent(
        @Body jsonparams: PostingData
    )

    @GET("/fishing/board/all")
    fun getBoardData(
    ): Call<PostingData>

}