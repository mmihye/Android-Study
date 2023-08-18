package com.example.webtoonapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.webtoonapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button1.setOnClickListener {
            // transaction : 작업의 단위
            // beginTransaction : 작업을 시작, commitTransaction: 작업을 끝
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainer, WebViewFragment())
                commit()
            }
        }

        binding.button2.setOnClickListener {
            // transaction : 작업의 단위
            // beginTransaction : 작업을 시작, commitTransaction: 작업을 끝
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainer, BFragment())
                commit()
            }
        }
    }

        override fun onBackPressed() {
            val currentFragment = supportFragmentManager.fragments.first()
            // webView 안에서 뒤로가기시 여러번클릭으로 webView 내 뒤로갈곳이 있다면
            // webView뒤로가기 없다면 activity backPressed로 앱이 종료
            if(currentFragment is WebViewFragment){
                if(currentFragment.canGoBack()){
                    currentFragment.goBack()
                }else{
                    super.onBackPressed()
                }}
            else{
                    super.onBackPressed()
                }
            }

//            super.onBackPressed()


}