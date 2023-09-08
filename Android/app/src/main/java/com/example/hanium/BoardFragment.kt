package com.example.hanium

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hanium.databinding.FragmentBoardBinding
import com.example.hanium.databinding.PostingListViewBinding
import kotlinx.android.synthetic.clearFindViewByIdCache
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
    private val itemList = ArrayList<BoardItem>()

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

        /**
         * 서버 URL 추가*/
//        boardListRequest()
        addPosting()
        binding.postingList.adapter = BoardAdapter(itemList)
        binding.postingList.layoutManager = LinearLayoutManager(
            context, LinearLayoutManager.VERTICAL, false)
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
                    recyclerBinding.postListPV.text = boardInfo.content.substring(0 until 15)
                }

            }

            override fun onFailure(call: Call<PostingData>, t: Throwable) {
                call.cancel()
            }

        })
    }

    private fun addPosting(){ //임시 테스트 데이터
        itemList.add(BoardItem("질문","수산시장 질문","궁금한 게 있습니다", "익명1", 20230828163000, 13))
        itemList.add(BoardItem("수다","싸네요","우와 진짜 싸요", "익명2", 20230828152412, 17))
        itemList.add(BoardItem("수다","놀러왔습니다","넓고 좋네요", "익명3", 20230828101112, 24))
        itemList.add(BoardItem("질문","수산시장 질문","궁금한 게 있습니다", "익명1", 20230827162542, 13))
        itemList.add(BoardItem("수다","싸네요","우와 진짜 싸요", "익명2", 20230827121624, 17))
        itemList.add(BoardItem("수다","놀러왔습니다","넓고 좋네요", "익명3", 20230826192412, 24))
        itemList.add(BoardItem("질문","수산시장 질문","궁금한 게 있습니다", "익명1", 20230825202451, 13))
        itemList.add(BoardItem("수다","싸네요","우와 진짜 싸요", "익명2", 20230825192315, 17))
        itemList.add(BoardItem("수다","놀러왔습니다","넓고 좋네요", "익명3", 20230825071513, 24))
        Log.d(tag, "$itemList")
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_write -> {
                Log.d(TAG, "click the write btn")
                val intent = Intent(requireContext(), BoardWriteActivity::class.java)
                startActivity(intent)
            }
        }
    }

}