package com.example.hw2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hw2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        supportFragmentManager.beginTransaction().replace(R.id.root, BottomNavigationFragment.newInstance()).commit()
    }
}