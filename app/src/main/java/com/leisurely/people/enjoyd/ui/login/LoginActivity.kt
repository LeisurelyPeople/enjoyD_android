package com.leisurely.people.enjoyd.ui.login

import android.os.Bundle
import com.leisurely.people.enjoyd.R
import com.leisurely.people.enjoyd.databinding.ActivityLoginBinding
import com.leisurely.people.enjoyd.ui.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * 로그인 액티비티
 *
 * @author Wayne
 * @since v1.0.0 / 2020.07.06
 */

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>(R.layout.activity_login) {

    override val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
