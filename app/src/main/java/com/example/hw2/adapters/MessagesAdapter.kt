package com.example.hw2.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hw2.entities.MessageInfo
import com.example.hw2.R
import kotlin.collections.ArrayList

class MessagesAdapter(): RecyclerView.Adapter<MessagesAdapter.ViewHolder>() {

    var messagesArrayList = ArrayList<MessageInfo>()

    fun setData(arrMessagesList: List<MessageInfo>){
        messagesArrayList = arrMessagesList as ArrayList<MessageInfo>
    }

    fun addMessage(message: MessageInfo, position: Int){
        messagesArrayList.add(message)
        notifyItemInserted(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(itemView = LayoutInflater.from(parent.context).inflate(R.layout.sample_message, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(model = messagesArrayList[position])
    }

    override fun getItemCount(): Int = messagesArrayList.count()

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        var itemMessage: TextView = itemView.findViewById(R.id.twUserMessage)

        fun bind(model: MessageInfo){
            itemMessage.text = model.message
        }

    }
}