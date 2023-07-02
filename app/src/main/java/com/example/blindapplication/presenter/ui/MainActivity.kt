package com.example.blindapplication.presenter.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.blindapplication.R
import com.example.blindapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
            view = this@MainActivity
        }
    }

    fun onClickAdd() {

    }
}