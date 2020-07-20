package com.leisurely.people.enjoyd.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.leisurely.people.enjoyd.R
import com.leisurely.people.enjoyd.databinding.ActivityMainBinding
import com.leisurely.people.enjoyd.ui.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {

    override val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }
}
