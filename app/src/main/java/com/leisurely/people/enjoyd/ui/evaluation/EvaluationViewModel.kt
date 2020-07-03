package com.leisurely.people.enjoyd.ui.evaluation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.leisurely.people.enjoyd.ui.base.BaseViewModel

/**
 * 평가하기 액티비티 ViewModel
 *
 * @author ricky
 * @since v1.0.0 / 2020.06.30
 */
class EvaluationViewModel : BaseViewModel() {
    private val _evaluations = MutableLiveData(listOf<String>())
    val evaluations: LiveData<List<String>>
        get() = _evaluations

    private val _position = MutableLiveData(0)
    val position: LiveData<Int>
        get() = _position

    init {
        _evaluations.value = listOf("a", "b", "c", "d", "e", "f")
    }

    fun btnClick(pos: Int) {
        _evaluations.value = when (pos) {
            0 -> listOf("a", "b", "c")
            1 -> listOf("a", "b", "c", "d")
            2 -> listOf("a", "b", "c", "d", "e")
            else -> listOf()
        }

        this._position.value = pos
    }
}
