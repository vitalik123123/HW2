package com.example.hw2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MessagesAdapter(private val messagesList: ArrayList<MessageInfo>): RecyclerView.Adapter<MessagesAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessagesAdapter.ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.sample_message, parent, false)
        return ViewHolder(view)
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