package com.example.webtoonapp

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.provider.Settings.Global.putString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import com.example.webtoonapp.databinding.FragmentWebviewBinding
import java.net.URL

class WebViewFragment(private val position: Int, private val webViewUrl: String) : Fragment() {

    var listener: onTabLayoutNameChanged? = null

    private lateinit var binding: FragmentWebviewBinding
    companion object{
        const val SHARED_PREFERENCE = "WEB_HISTORY"
    }

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
            activity?.getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE)?.edit {
                putString("tab$position",url)
            }
        }
        binding.webView.settings.javaScriptEnabled = true

        binding.webView.loadUrl(webViewUrl)

        binding.backToLastButton.setOnClickListener {
            val sharedPreferences = activity?.getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE)
            val url = sharedPreferences?.getString("tab$position","")
            if(url.isNullOrBlank()){
                Toast.makeText(context, "마지막 저장 시점이 없습니다.",Toast.LENGTH_SHORT).show()
            }else{
                binding.webView.loadUrl(url)
            }

        }

        binding.changeTabNameButton.setOnClickListener {
            val dialog = AlertDialog.Builder(context)
            val editText = EditText(context)

            dialog.setView(editText)
            dialog.setPositiveButton("저장"){ _, _ ->
                activity?.getSharedPreferences(SHARED_PREFERENCE,Context.MODE_PRIVATE)?.edit {
                    putString("tab${position}_name",editText.text.toString())
                    listener?.nameChanged(position, editText.text.toString()) // 리스너에게 이름이 변경되었다고 알려줌
                }

            }
            dialog.setNegativeButton("취소"){ dialogInterface, _ ->
                dialogInterface.cancel()
            }
            dialog.show()
        }
     }

    fun canGoBack():Boolean{
        return binding.webView.canGoBack()
    }

    fun goBack() {
        binding.webView.goBack()
    }
}

interface onTabLayoutNameChanged {
    fun nameChanged(position: Int, name: String)
}