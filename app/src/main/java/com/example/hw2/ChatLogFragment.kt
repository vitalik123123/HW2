package com.example.hw2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hw2.databinding.FragmentChatLogBinding



class ChatLogFragment : Fragment() {

    private lateinit var binding: FragmentChatLogBinding
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var newArrayList: ArrayList<MessageInfo>
    lateinit var messageText: ArrayList<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChatLogBinding.inflate(inflater)
        return binding.rootChatLog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        messageText =
            mutableListOf("This test message 1", "This test message 2", "This test message 3", "This test message 4") as ArrayList<String>


        layoutManager = LinearLayoutManager(context)
        (layoutManager as LinearLayoutManager).stackFromEnd = true

        binding.rvMessages.layoutManager = layoutManager

        newArrayList = arrayListOf<MessageInfo>()
        getUserdata()

        binding.btnSendMessage.setOnClickListener {
            messageText.add(binding.etSendMessage.text.toString())
            refreshUserdata()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ChatLogFragment()

    }

    private fun getUserdata() {
        for (i in messageText.indices){
            val messages = MessageInfo(messageText[i])
            newArrayList.add(messages)
        }

        binding.rvMessages.adapter = MessagesAdapter(newArrayList)
    }

    private fun refreshUserdata(){

        newArrayList.clear()
        binding.rvMessages.adapter?.notifyDataSetChanged()

        for (i in messageText.indices){
            val messages = MessageInfo(messageText[i])
            newArrayList.add(messages)
        }

        binding.rvMessages.adapter = MessagesAdapter(newArrayList)
    }
}