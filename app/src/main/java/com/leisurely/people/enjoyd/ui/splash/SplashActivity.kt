package com.leisurely.people.enjoyd.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.lifecycle.Observer
import com.leisurely.people.enjoyd.R
import com.leisurely.people.enjoyd.databinding.ActivitySplashBinding
import com.leisurely.people.enjoyd.ui.base.BaseActivity
import com.leisurely.people.enjoyd.ui.login.LoginActivity
import com.leisurely.people.enjoyd.ui.main.MainActivity
import com.leisurely.people.enjoyd.util.Constant.Companion.EXTRA_KAKAO_LINK_VIDEO_ID
import com.leisurely.people.enjoyd.util.Constant.Companion.KAKAO_LINK_SLUG
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * 스플래쉬 액티비티
 *
 * @author Wayne
 * @since v1.0.0 / 2020.08.26
 */
class SplashActivity :
    BaseActivity<ActivitySplashBinding, SplashViewModel>(R.layout.activity_splash) {

    override val viewModel: SplashViewModel by viewModel()

    private val handler = Handler()

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeViewModel()
        viewModel.checkUserToken()
    }

    private fun observeViewModel() {
        with(viewModel) {
            startLogin.observe(this@SplashActivity, Observer {
                setIntentHandler(LoginActivity.getIntent(this@SplashActivity))
            })

            startMain.observe(this@SplashActivity, Observer {
                setIntentHandler(MainActivity.getIntent(this@SplashActivity).apply {
                    if (intent.action == Intent.ACTION_VIEW) {
                        intent.data?.getQueryParameter(KAKAO_LINK_SLUG)?.let {
                            putExtra(EXTRA_KAKAO_LINK_VIDEO_ID, it)
                        }
                    }
                })
            })
        }
    }


    private fun setIntentHandler(intent: Intent) {
        handler.postDelayed({
            startActivity(intent)
            finish()
        }, 500)
    }
}
