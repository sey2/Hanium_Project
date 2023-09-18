package com.example.hanium

import android.content.ContentValues.TAG
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.hanium.databinding.BoardDetailActivityBinding
import com.example.hanium.databinding.FragmentBoardBinding

class BoardAdapter(
    private val itemList: MutableList<BoardItem>) :
    RecyclerView.Adapter<BoardAdapter.BoardViewHolder>(){

    inner class BoardViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView){
        private val context = itemView.context
        val boardBadge: TextView = itemView.findViewById(R.id.postListBadge)
        val boardTitle: TextView = itemView.findViewById(R.id.postListTitle)
        val boardContent: TextView = itemView.findViewById(R.id.postListPV)
        val boardUserName: TextView = itemView.findViewById(R.id.postListUsername)
        val boardDate: TextView = itemView.findViewById(R.id.postListDate)

        fun bind(item: BoardItem) {
            itemView.setOnClickListener {
                val intent = Intent(context, BoardDetailActivity::class.java)
                intent.putExtra("badge", item.badge)
                intent.putExtra("title", item.title)
                intent.putExtra("content", item.content)
                intent.putExtra("userNm", item.userName)
                intent.putExtra("date", item.date)
                intent.run { context.startActivity(this) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.posting_list_view, parent, false)
        return BoardViewHolder(view)
    }

    override fun onBindViewHolder(holder: BoardViewHolder, position: Int) {
        val boardItem = itemList[position]
        holder.boardBadge.text = itemList[position].badge
        holder.boardTitle.text = itemList[position].title
        holder.boardContent.text = itemList[position].content.substring(0 until 25) +"..."
        holder.boardUserName.text = itemList[position].userName
        holder.boardDate.text = itemList[position].date
        holder.bind(boardItem)
    }

    override fun getItemCount(): Int {
        return itemList.count()
    }
}