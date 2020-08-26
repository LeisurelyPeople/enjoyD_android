package com.leisurely.people.enjoyd.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.leisurely.people.enjoyd.R
import com.leisurely.people.enjoyd.databinding.ActivityLoginBinding
import com.leisurely.people.enjoyd.ui.base.BaseActivity
import com.leisurely.people.enjoyd.ui.login.sociallogin.KakaoLogin
import com.leisurely.people.enjoyd.ui.main.MainActivity
import com.leisurely.people.enjoyd.ui.onboarding.UserInfoInputActivity
import com.leisurely.people.enjoyd.util.applyClickShrink
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
            reStartActivity()
        })
    }

    override val viewModel: LoginViewModel by viewModel {
        parametersOf(kakaoLogin)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLoginBtnAnimation()

        viewModel.startMain.observe(this, Observer {
            startActivity(MainActivity.getIntent(this))
        })

        viewModel.startOnBoarding.observe(this, Observer { socialLogin ->
            UserInfoInputActivity.startActivity(this@LoginActivity, socialLogin)
        })

        viewModel.reStartLogin.observe(this, Observer {
            /**
             *  로그인 실패 시 로그인 액티비티 재 생성 되도록 설정
             *  재실행 하지 않는 경우 카카오 로그인 클래스 내의 onSessionOpened() 콜백 메소드가 계속되기 때문에 onDestroy() 메소드를 타게 해서
             *  콜백을 한번씩 제거 해줘야함.
             */
            reStartActivity()
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

    private fun setLoginBtnAnimation() {
        binding.clKakaoLoginLayout.applyClickShrink()
    }

    /** 소셜 로그인 실패 시 해당 화면을 재시작 하기 위한 메소드 */
    private fun reStartActivity() {
        viewModel.showToast(getString(R.string.connection_failed))
        val intent = intent.apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        }
        finish()
        startActivity(intent)
    }

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }
}