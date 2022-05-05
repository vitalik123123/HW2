package com.example.hw2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hw2.databinding.MessageBinding

class MessagesAdapter(private val messagesList: ArrayList<MessageInfo>): RecyclerView.Adapter<MessagesAdapter.ViewHolder>() {

   private lateinit var binding: MessageBinding



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessagesAdapter.ViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.message, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: MessagesAdapter.ViewHolder, position: Int) {

        val currentItem = messagesList[position]
        holder.itemMessage.text = currentItem.message
    }

    override fun getItemCount(): Int = messagesList.size

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        var itemMessage: TextView = itemView.findViewById(R.id.twUserMessage)

    }

}