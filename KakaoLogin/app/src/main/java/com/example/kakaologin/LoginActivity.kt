package com.example.kakaologin

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.kakaologin.databinding.ActivityLoginBinding
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.common.model.KakaoSdkError
import com.kakao.sdk.user.UserApiClient

class LoginActivity :AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    private val callback:(OAuthToken?, Throwable?) -> Unit = { token, error ->
        if(error != null){
            //로그인 실패
        }else if(token != null){
            //로그인 성공
            Log.e("loginActivity", "login in with kakao account token == $token")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Kakao SDK 초기화
        KakaoSdk.init(this, "6e217e8b202e7831926d2e71aeee238c")

        binding.kakaoTalkLoginButton.setOnClickListener {
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)){
                //카카오톡 로그인
                UserApiClient.instance.loginWithKakaoTalk(this){ token, error ->
                    if(error != null) {
                        // 카카오톡 로그인 실패
                        // 유저가 로그인 닫기
                        if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                            return@loginWithKakaoTalk
                        }
                        UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
                    }else if(token != null){
                        // 로그인 성공
                        Log.e("loginActivity", "token == $token")
                    }
                }
            }else{
                //카카오계정 로그인

                UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
            }
        }


    }
}