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
    val position = MutableLiveData(0)

    init {
        evaluations.value = listOf("a", "b", "c", "d", "e", "f")
    }

    fun btnClick(pos: Int) {
        evaluations.value = when (pos) {
            0 -> listOf("a", "b", "c")
            1 -> listOf("a", "b", "c", "d")
            2 -> listOf("a", "b", "c", "d", "e")
            else -> listOf()
        }

        this.position.value = pos
    }
}
