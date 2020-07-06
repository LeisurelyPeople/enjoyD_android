package com.leisurely.people.enjoyd.ui.main

import android.os.Bundle
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.leisurely.people.enjoyd.R
import com.leisurely.people.enjoyd.databinding.ActivityMainBinding
import com.leisurely.people.enjoyd.ui.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {
    override val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
