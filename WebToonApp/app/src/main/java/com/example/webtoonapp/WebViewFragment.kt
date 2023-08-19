package com.example.webtoonapp

import android.content.Context
import android.os.Bundle
import android.provider.Settings.Global.putString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import com.example.webtoonapp.databinding.FragmentWebviewBinding

class WebViewFragment(private val position: Int) : Fragment() {

    private lateinit var binding: FragmentWebviewBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWebviewBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.webView.webViewClient = WebtoonWebViewClient(binding.progressBar){ url ->
            activity?.getSharedPreferences("WEB_HISTORY", Context.MODE_PRIVATE)?.edit {
                putString("tab$position",url)
            }
        }
        binding.webView.settings.javaScriptEnabled = true

        binding.webView.loadUrl("https://webtoonscorp.com/")

        binding.backToLastButton.setOnClickListener {
            val sharedPreferences = activity?.getSharedPreferences("WEB_HISTORY", Context.MODE_PRIVATE)
            val url = sharedPreferences?.getString("tab$position","")
            if(url.isNullOrBlank()){
                Toast.makeText(context, "마지막 저장 시점이 없습니다.",Toast.LENGTH_SHORT).show()
            }else{
                binding.webView.loadUrl(url)
            }

        }
     }

    fun canGoBack():Boolean{
        return binding.webView.canGoBack()
    }

    fun goBack() {
        binding.webView.goBack()
    }
}