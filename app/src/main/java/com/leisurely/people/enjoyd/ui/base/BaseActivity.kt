package com.leisurely.people.enjoyd.ui.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.leisurely.people.enjoyd.BR

/**
 * Activity Base 클래스
 * DataBinding 설정, ViewModel 설정, lifecycleOwner 설정 등의 역할을 하는 클래스
 *
 * @author Wayne
 * @since v1.0.0 / 2020.06.15
 */

abstract class BaseActivity<B : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes private val layoutRes: Int
) : AppCompatActivity() {

    protected lateinit var binding: B

    protected abstract val viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, layoutRes)
        binding.run {
            setVariable(BR.vm, viewModel) // 데이터 바인딩 <-> 뷰모델 자동 설정
            lifecycleOwner = this@BaseActivity // liveData 사용을 위한 lifecycleOwner 설정
        }

        viewModel.liveToastMessage.observe(this, Observer { message ->
            Toast.makeText(baseContext, message, Toast.LENGTH_SHORT).show()
        })
    }
}