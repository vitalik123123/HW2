package com.example.hw2.fragmets

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hw2.databinding.FragmentPeopleBinding

class PeopleFragment : Fragment() {

    private lateinit var binding: FragmentPeopleBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPeopleBinding.inflate(inflater)
        return binding.rootPeople
    }

    companion object {
        @JvmStatic
        fun newInstance() = PeopleFragment()
    }
}