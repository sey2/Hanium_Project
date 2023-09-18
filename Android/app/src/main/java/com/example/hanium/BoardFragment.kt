package com.example.hanium

import android.app.Activity.RESULT_OK
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hanium.databinding.FragmentBoardBinding
import com.example.hanium.databinding.PostingListViewBinding
import org.w3c.dom.Comment
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST

class BoardFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentBoardBinding
    private lateinit var recyclerBinding: PostingListViewBinding

    private var itemList: MutableList<BoardItem> = mutableListOf(
        BoardItem("질문","수산물 어디가 제일 싼가요?","수산시장 가서 횟감 좀 사려는데 어디서 사는 게 제일 저렴할지 추천해주세요", "수산 병아리", "2023.08.28 16:30:07", "9"),
        BoardItem("수다","싸고 좋네요","오랜만에 수산시장에 들렀는데 물건도 저렴하고 좋아요", "서울 토박이", "2023.08.28 15:24:12", "8"),
        BoardItem("수다","놀러왔습니다","식당 가서 식사하고 나오는 길인데 여기 수산시장 괜찮네요", "수산 마니아", "2023.08.28 10:11:12", "7"),
        BoardItem("질문","수산물 어디가 제일 싼가요?","수산시장 가서 횟감 좀 사려는데 어디서 사는 게 제일 저렴할지 추천해주세요", "수산 병아리", "2023.08.27 16:25:42", "6"),
        BoardItem("수다","싸고 좋네요","오랜만에 수산시장에 들렀는데 물건도 저렴하고 좋아요", "서울 토박이", "2023.08.27 12:16:24", "5"),
        BoardItem("수다","놀러왔습니다","식당 가서 식사하고 나오는 길인데 여기 수산시장 괜찮네요", "수산 마니아", "2023.08.26 19:24:12", "4"),
        BoardItem("질문","수산물 어디가 제일 싼가요?","수산시장 가서 횟감 좀 사려는데 어디서 사는 게 제일 저렴할지 추천해주세요", "수산 병아리", "2023.08.25 20:24:51", "3"),
        BoardItem("수다","싸고 좋네요","오랜만에 수산시장에 들렀는데 물건도 저렴하고 좋아요", "서울 토박이", "2023.08.25 19:23:15", "2"),
        BoardItem("수다","놀러왔습니다","식당 가서 식사하고 나오는 길인데 여기 수산시장 괜찮네요", "수산 마니아", "2023.08.25 07:15:13", "1")
    )

    private var cmtList: MutableList<CommentItem> = mutableListOf(
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBoardBinding.inflate(inflater, container, false)
        recyclerBinding = PostingListViewBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnWrite.setOnClickListener(this)

        binding.postingList.adapter = BoardAdapter(itemList)
        binding.postingList.layoutManager = LinearLayoutManager(
            context, LinearLayoutManager.VERTICAL, false)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)

        if (resultCode == RESULT_OK) {
            itemList.add(BoardItem(
                intent?.getStringExtra("badge")!!,
                intent.getStringExtra("title")!!,
                intent.getStringExtra("content")!!,
                intent.getStringExtra("date")!!,
                intent.getStringExtra("pic")!!,
                intent.getStringExtra("id")!!
            ))
        }
    }

    private fun boardListRequest(){

        // retrofit 객체
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("") // 서버 URL 입력
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Service 객체
        val boardService: BoardService = retrofit.create(BoardService::class.java)

        // Call 객체
        val boardCall = boardService.getBoardData()

        // 네트워크 통신
        boardCall.enqueue(object: Callback<PostingData>{
            override fun onResponse(call: Call<PostingData>, response: Response<PostingData>) {
                // 호출 데이터
                val boardInfo = response.body()

                if (boardInfo != null) {
                    recyclerBinding.postListTitle.text = boardInfo.title
                    recyclerBinding.postListPV.text = boardInfo.content.substring(0 until 5)
                }

            }

            override fun onFailure(call: Call<PostingData>, t: Throwable) {
                call.cancel()
            }

        })
    }

    fun addPost(intent: Intent){
        itemList.add(BoardItem(
            intent.getStringExtra("badge")!!,
            intent.getStringExtra("title")!!,
            intent.getStringExtra("content")!!,
            intent.getStringExtra("date")!!,
            intent.getStringExtra("pic")!!,
            intent.getStringExtra("id")!!
        ))
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_write -> {
                val intent = Intent(requireContext(), BoardWriteActivity::class.java)
//                startActivityForResult(intent, itemList.size)
                registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                    when (result.resultCode) {
                        RESULT_OK -> {
                            addPost(intent)
                        }
                    }
                }
            }
        }
    }

}