package com.example.hw2.fragmets

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hw2.adapters.MessagesAdapter
import com.example.hw2.databinding.FragmentChatLogBinding
import com.example.hw2.entities.ChannelInfo
import com.example.hw2.entities.MessageInfo
import com.google.android.material.snackbar.Snackbar
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers


class ChatLogFragment : Fragment() {

    private lateinit var binding: FragmentChatLogBinding
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var adapterArrayList: ArrayList<MessageInfo>
    var messageAdapter: MessagesAdapter = MessagesAdapter()
    lateinit var messageText: ArrayList<String>
    private val disposeBagChatLog = CompositeDisposable()
    private var modelChannel: ChannelInfo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        modelChannel = requireArguments().getSerializable("model") as ChannelInfo?
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChatLogBinding.inflate(inflater)
        return binding.rootChatLog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvChatLog.text = modelChannel!!.channel

        messageText =
            mutableListOf("This test message 1", "This test message 2", "This test message 3", "This test message 4") as ArrayList<String>

        layoutManager = LinearLayoutManager(context)
        (layoutManager as LinearLayoutManager).stackFromEnd = true

        binding.rvMessages.layoutManager = layoutManager

        adapterArrayList = arrayListOf()

        val countErr = (2..5).shuffled().first()
        println("Count : $countErr")
        var currSend = 0

        binding.btnSendMessage.setOnClickListener {
            currSend++
            Log.e("SNAC", " countErr : $countErr  currSend : $currSend")
            val disposeClickSendMessage = Observable.fromArray(messageText)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (countErr != currSend) {
                        messageAdapter.addMessage(MessageInfo(binding.etSendMessage.text.toString()), adapterArrayList.size - 1)
                        binding.rvMessages.adapter = messageAdapter
                    } else {
                        Snackbar.make(
                            binding.rootChatLog,
                            "Send message error",
                            Snackbar.LENGTH_LONG
                        )
                            .show()

                        currSend = 0
                    }
                },{

                })
            disposeBagChatLog.add(disposeClickSendMessage)
        }

        val disposeViewMessage = Observable.fromIterable(messageText)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                adapterArrayList.add(MessageInfo(it))
            }
            .subscribe({
                messageAdapter.setData(adapterArrayList)
                binding.rvMessages.adapter = messageAdapter
            },{

            })

        disposeBagChatLog.add(disposeViewMessage)

        binding.backChat.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    override fun onDestroyView() {
        disposeBagChatLog.clear()
        super.onDestroyView()
    }

    companion object {
        @JvmStatic
        fun newInstance() = ChatLogFragment().apply {
            arguments = Bundle().apply {

            }
        }

    }
}