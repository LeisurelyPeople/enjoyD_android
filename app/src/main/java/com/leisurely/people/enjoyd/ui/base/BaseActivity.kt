package com.leisurely.people.enjoyd.ui.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import com.leisurely.people.enjoyd.BR
import com.leisurely.people.enjoyd.ui.common.fragment.ProgressDialogFragment
import com.leisurely.people.enjoyd.util.Constant
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

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

    /**
     * [Dispatchers.Main]을 기본으로 사용하고
     * [onDestroy]에서 [cancel][CoroutineScope.cancel] 되는 코루틴 스코프
     */
    val uiScope: CoroutineScope = MainScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, layoutRes)
        binding.run {
            setVariable(BR.vm, viewModel) // 데이터 바인딩 <-> 뷰모델 자동 설정
            lifecycleOwner = this@BaseActivity // liveData 사용을 위한 lifecycleOwner 설정
        }

        viewModel.startLogout.observe(this, Observer {
            EnjoyDApplication.instance.logout()
        })

        viewModel.liveLoading.observe(this, Observer { isLoading ->
            val progressDialog = supportFragmentManager
                .findFragmentByTag(Constant.FRAGMENT_TAG_PROGRESS_BAR_DIALOG) as? DialogFragment

            if (isLoading) {
                if (progressDialog == null) {
                    ProgressDialogFragment.newInstance()
                        .show(supportFragmentManager, Constant.FRAGMENT_TAG_PROGRESS_BAR_DIALOG)
                }
            } else {
                progressDialog?.dismissAllowingStateLoss()
            }
        })

        viewModel.liveToastMessage.observe(this, Observer { message ->
            Toast.makeText(baseContext, message, Toast.LENGTH_SHORT).show()
        })
    }

    override fun onDestroy() {
        uiScope.cancel()
        super.onDestroy()
    }

    companion object {
        private val TAG = BaseActivity::class.java.canonicalName
    }
}
