package com.leisurely.people.enjoyd.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.leisurely.people.enjoyd.R
import com.leisurely.people.enjoyd.data.local.prefs.TokenManager
import com.leisurely.people.enjoyd.databinding.ActivityLoginBinding
import com.leisurely.people.enjoyd.ui.base.BaseActivity
import com.leisurely.people.enjoyd.ui.login.sociallogin.KakaoLogin
import com.leisurely.people.enjoyd.ui.main.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

/**
 * 로그인 액티비티
 *
 * @author Wayne
 * @since v1.0.0 / 2020.07.06
 */

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>(R.layout.activity_login) {

    private val kakaoLogin by lazy {
        KakaoLogin(this, onLoginSuccess = {
            viewModel.requestLogin(it)
        }, onLoginFail = {
            viewModel.showToast(getString(R.string.connection_failed))
            reStartActivity()
        })
    }

    override val viewModel: LoginViewModel by viewModel {
        parametersOf(kakaoLogin)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.startMain.observe(this, Observer { userTokenResponse ->
            /** 사용자 토큰 SharedPreference 저장 후 메인화면으로 전환 */
            TokenManager.setUserToken(this, userTokenResponse)
            MainActivity.startActivity(this@LoginActivity)
            finish()
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        kakaoLogin.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onDestroy() {
        kakaoLogin.onDestroy() // 로그인 화면을 벗어나면 카카오 세션을 끝내기 위한 구문
        super.onDestroy()
    }

    /** 소셜 로그인 실패 시 해당 화면을 재시작 하기 위한 메소드 */
    private fun reStartActivity() {
        val intent = intent.apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        }
        finish()
        startActivity(intent)
    }

    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, LoginActivity::class.java))
        }
    }
}