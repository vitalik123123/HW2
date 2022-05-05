package com.example.hw2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hw2.databinding.ActivityMainBinding
import com.example.hw2.databinding.FragmentChatLogBinding
import com.example.hw2.databinding.MessageBinding
import com.example.hw2.databinding.SampleReactionBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        supportFragmentManager.beginTransaction().replace(R.id.root, ChatLogFragment.newInstance()).commit()
    }
}