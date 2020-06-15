package com.leisurely.people.enjoyd.ui.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.leisurely.people.enjoyd.BR

abstract class BaseActivity<B : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes private val layoutRes: Int
) : AppCompatActivity() {

    protected lateinit var binding: B

    protected abstract val viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, layoutRes)
        binding.run {
            setVariable(BR.vm, viewModel)
            lifecycleOwner = this@BaseActivity
        }

        viewModel.liveToastMessage.observe(this, Observer { message ->
            Toast.makeText(baseContext, message, Toast.LENGTH_SHORT).show()
        })
    }
}