package com.example.hw2.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.hw2.entities.ChannelInfo
import com.example.hw2.fragmets.ChatLogFragment
import com.example.hw2.R
import com.example.hw2.entities.MessageInfo
import com.google.android.material.snackbar.Snackbar

class ChannelAdapter(): RecyclerView.Adapter<ChannelAdapter.ViewHolder>() {

    var channelsList = ArrayList<ChannelInfo>()
    var listener: OnItemClickListener? = null

    fun setData(arrChannelsList: List<ChannelInfo>){
        channelsList = arrChannelsList as ArrayList<ChannelInfo>
    }

    fun setOnClickListener(listener1: OnItemClickListener){
        listener = listener1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(itemView = LayoutInflater.from(parent.context).inflate(R.layout.sample_channel, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(model = channelsList[position])
    }

    override fun getItemCount(): Int = channelsList.size

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var itemChannel: TextView = itemView.findViewById(R.id.sampleChannelName)

        fun bind(model: ChannelInfo){
            itemChannel.text = model.channel
            itemView.setOnClickListener {
                listener?.onClick(model)
            }
        }
    }

    interface OnItemClickListener{
        fun onClick(clickModel: ChannelInfo)
    }
}