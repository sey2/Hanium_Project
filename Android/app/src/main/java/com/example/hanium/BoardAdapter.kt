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
    private val itemList: ArrayList<BoardItem>) :
    RecyclerView.Adapter<BoardAdapter.BoardViewHolder>(){

    inner class BoardViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView){
        private val context = itemView.context
        val boardBadge: TextView = itemView.findViewById(R.id.postListBadge)
        val boardTitle: TextView = itemView.findViewById(R.id.postListTitle)
        val boardContent: TextView = itemView.findViewById(R.id.postListPV)
        val boardUserName: TextView = itemView.findViewById(R.id.postListUsername)
        val boardDate: TextView = itemView.findViewById(R.id.postListDate)
        val boardHits: TextView = itemView.findViewById(R.id.postListHits)

        fun bind(item: BoardItem) {
            itemView.setOnClickListener {
                val intent = Intent(context, BoardDetailActivity::class.java)
                intent.putExtra("data", item)
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
        holder.boardContent.text = itemList[position].content
        holder.boardUserName.text = itemList[position].userName
        holder.boardDate.text = itemList[position].date.toString()
        holder.boardHits.text = "조회 " + itemList[position].hits.toString()
        holder.bind(boardItem)
    }

    override fun getItemCount(): Int {
        return itemList.count()
    }
}