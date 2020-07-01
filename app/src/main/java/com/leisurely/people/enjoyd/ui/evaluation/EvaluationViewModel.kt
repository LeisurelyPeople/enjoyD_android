package com.leisurely.people.enjoyd.ui.evaluation

import androidx.lifecycle.MutableLiveData
import com.leisurely.people.enjoyd.ui.base.BaseViewModel

/**
 * 평가하기 액티비티 ViewModel
 *
 * @author ricky
 * @since v1.0.0 / 2020.06.30
 */
class EvaluationViewModel : BaseViewModel() {
    val evaluations = MutableLiveData(listOf<String>())

    fun firstBtnClick(){
        evaluations.value = listOf("a", "b", "c", "d", "e", "f")
    }
}
