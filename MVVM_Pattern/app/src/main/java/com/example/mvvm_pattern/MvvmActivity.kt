package com.example.mvvm_pattern

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.mvvm_pattern.databinding.ActivityMvvmBinding
import com.example.mvvm_pattern.repository.ImageRespositoryImpl

class MvvmActivity : AppCompatActivity() {

    private val viewModel: MvvmViewModel by viewModels{
        MvvmViewModel.MvvmViewModelFactroy(ImageRespositoryImpl())
    }

    private lateinit var binding: ActivityMvvmBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMvvmBinding.inflate(layoutInflater).also {
            setContentView(it.root)
            it.lifecycleOwner =this
            it.view = this
            it.viewModel = viewModel
        }
    }

    fun loadImage(){
        viewModel.loadRandomImage()
    }
}