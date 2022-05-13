package com.example.hw2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView

class ChannelAdapter(private val channelsList: ArrayList<ChannelInfo>): RecyclerView.Adapter<ChannelAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChannelAdapter.ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.sample_channel, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChannelAdapter.ViewHolder, position: Int) {

        val currentItem = channelsList[position]
        holder.itemChannel.text = currentItem.channel
        holder.itemView.setOnClickListener { p0 ->
            val fragment = p0!!.context as FragmentActivity
            fragment.supportFragmentManager.beginTransaction()
                .replace(R.id.root, ChatLogFragment.newInstance()).commit()
        }
    }

    override fun getItemCount(): Int = channelsList.size

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var itemChannel: TextView = itemView.findViewById(R.id.sampleChannelName)

    }
}