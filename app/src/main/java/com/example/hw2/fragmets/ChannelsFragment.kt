package com.example.hw2.fragmets

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hw2.R
import com.example.hw2.adapters.ChannelAdapter
import com.example.hw2.databinding.FragmentChannelsBinding
import com.example.hw2.entities.ChannelInfo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList


class ChannelsFragment : Fragment() {

    private lateinit var binding: FragmentChannelsBinding
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var adapterArrayList: ArrayList<ChannelInfo>
    var channelAdapter: ChannelAdapter = ChannelAdapter()
    lateinit var channelText: ArrayList<String>
    private val disposeBagChannels = CompositeDisposable()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChannelsBinding.inflate(inflater)
        return binding.rootChannels
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        channelText =
            arrayListOf("#general", "#Development", "#Design", "#PR")

        layoutManager = LinearLayoutManager(context)
        binding.channelsRecycler.layoutManager = layoutManager

        channelAdapter.setOnClickListener(onClick)

        adapterArrayList = arrayListOf()

        val disposeViewChanel = Observable.fromIterable(channelText)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                adapterArrayList.add(ChannelInfo(it))
            }
            .subscribe({
                channelAdapter.setData(adapterArrayList)
                binding.channelsRecycler.adapter = channelAdapter
            }, {

            })


        disposeBagChannels.add(disposeViewChanel)

        val disposeChannelSearch = Observable.create(ObservableOnSubscribe<String> { subsctiber ->
            binding.channelsSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    subsctiber.onNext(query!!)
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    subsctiber.onNext(newText!!)
                    return false
                }
            })
        })
            .map { text -> text.lowercase().trim() }
            .debounce(1000, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({search ->
                val curArr = ArrayList<ChannelInfo>()

                Log.e("TEST", "newText $search")
                adapterArrayList.map {
                    if (it.channel!!.lowercase(Locale.getDefault()).contains(search.toString())){
                        curArr.add(it)
                    }
                }

                binding.channelsRecycler.adapter?.notifyDataSetChanged()
                channelAdapter.setData(curArr)
                binding.channelsRecycler.adapter = channelAdapter
            },{

            })

        disposeBagChannels.add(disposeChannelSearch)
    }

    private val onClick = object :ChannelAdapter.OnItemClickListener{
        override fun onClick(clickModel: ChannelInfo) {
            Toast.makeText(this@ChannelsFragment.context, "${clickModel.channel}", Toast.LENGTH_LONG).show()

            var fragment: Fragment
            var bundle = Bundle()
            bundle.putSerializable("model", clickModel)
            fragment = ChatLogFragment.newInstance()
            fragment.arguments = bundle

            activity!!.supportFragmentManager.beginTransaction()
                .replace(R.id.root, fragment).addToBackStack(fragment.javaClass.simpleName).commit()
        }

    }


    override fun onDestroyView() {
        disposeBagChannels.clear()
        super.onDestroyView()
    }

    companion object {
        @JvmStatic
        fun newInstance() = ChannelsFragment().apply {
            arguments = Bundle().apply {

            }
        }
    }
}