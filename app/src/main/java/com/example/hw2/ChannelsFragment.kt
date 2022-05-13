package com.example.hw2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hw2.databinding.FragmentChannelsBinding


class ChannelsFragment : Fragment() {

    private lateinit var binding: FragmentChannelsBinding
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var newArrayList: ArrayList<ChannelInfo>
    lateinit var channelText: ArrayList<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChannelsBinding.inflate(inflater)
        return binding.rootChannels
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        channelText = mutableListOf("#general", "#Development", "#Design", "#PR") as ArrayList<String>

        layoutManager = LinearLayoutManager(context)
        binding.channelsRecycler.layoutManager = layoutManager

        newArrayList = arrayListOf()
        getChannel()
    }

    companion object {
        @JvmStatic
        fun newInstance() = ChannelsFragment()
    }

    private fun getChannel(){
        for (i in channelText.indices){
            val channels = ChannelInfo(channelText[i])
            newArrayList.add(channels)
        }

        var adapter = ChannelAdapter(newArrayList)
        binding.channelsRecycler.adapter = adapter
    }
}