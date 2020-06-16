package com.leisurely.people.enjoyd.ui.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.leisurely.people.enjoyd.BR

/**
 * Fragment Base 클래스
 * DataBinding 설정, ViewModel 설정, lifecycleOwner 설정 등의 역할을 하는 클래스
 *
 * @author Wayne
 * @since v1.0.0 / 2020.06.15
 */

abstract class BaseFragment<B : ViewDataBinding, VM : BaseViewModel>(@LayoutRes private val layoutRes: Int) :
    Fragment(layoutRes) {

    protected val binding by lazy {
        DataBindingUtil.bind<B>(view!!)!!
    }

    protected abstract val viewModel: VM

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.run {
            setVariable(BR.vm, viewModel)
            lifecycleOwner = viewLifecycleOwner
        }

        viewModel.liveToastMessage.observe(viewLifecycleOwner, Observer { message ->
            context?.let { context ->
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}