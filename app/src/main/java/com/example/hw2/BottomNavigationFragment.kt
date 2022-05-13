package com.example.hw2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.example.hw2.databinding.FragmentBottomNavigationBinding


class BottomNavigationFragment : Fragment() {

    private lateinit var binding: FragmentBottomNavigationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBottomNavigationBinding.inflate(inflater)
        return binding.rootBottomNavigation
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bottomNavigationView.selectedItemId = R.id.bnMenuChannels
        childFragmentManager.beginTransaction().replace(R.id.frameOnBottomNavigation, ChannelsFragment.newInstance()).commit()
        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.bnMenuChannels -> {
                    childFragmentManager.beginTransaction().replace(R.id.frameOnBottomNavigation, ChannelsFragment.newInstance()).commit()
                }
                R.id.bnMenuPeople -> {
                    childFragmentManager.beginTransaction().replace(R.id.frameOnBottomNavigation, PeopleFragment.newInstance()).commit()
                }
                R.id.bnMenuProfile -> {
                    childFragmentManager.beginTransaction().replace(R.id.frameOnBottomNavigation, ProfileFragment.newInstance()).commit()
                }
            }
            true
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = BottomNavigationFragment()
    }
}