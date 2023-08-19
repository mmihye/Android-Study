package com.example.webtoonapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import com.example.webtoonapp.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity(), onTabLayoutNameChanged{

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreference = getSharedPreferences(WebViewFragment.Companion.SHARED_PREFERENCE,
            Context.MODE_PRIVATE)
        val tab0 = sharedPreference?.getString("tab0_name","월요웹툰")
        val tab1 = sharedPreference?.getString("tab0_name","화요웹툰")
        val tab2 = sharedPreference?.getString("tab0_name","수요웹툰")

        binding.viewPager.adapter = ViewPagerAdapter(this)

        TabLayoutMediator(binding.tabLayout, binding.viewPager){ tab, position->
            run{
//                val textView = TextView(this@MainActivity)
//                textView.text ="position $position"
//                textView.gravity = Gravity.CENTER
//
//                tab.customView = textView
                tab.text = when(position){
                    0->tab0
                    1->tab1
                    else->tab2
                }
            }
        }.attach()

//        binding.button1.setOnClickListener {
//            // transaction : 작업의 단위
//            // beginTransaction : 작업을 시작, commitTransaction: 작업을 끝
//            supportFragmentManager.beginTransaction().apply {
//                replace(R.id.fragmentContainer, WebViewFragment())
//                commit()
//            }
//        }
//
//        binding.button2.setOnClickListener {
//            // transaction : 작업의 단위
//            // beginTransaction : 작업을 시작, commitTransaction: 작업을 끝
//            supportFragmentManager.beginTransaction().apply {
//                replace(R.id.fragmentContainer, BFragment())
//                commit()
//            }
//        }
    }

        override fun onBackPressed() {
            val currentFragment = supportFragmentManager.fragments[binding.viewPager.currentItem]
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

    override fun nameChanged(position: Int, name: String) {
        val tab = binding.tabLayout.getTabAt(position)
        tab?.text = name
    }

}