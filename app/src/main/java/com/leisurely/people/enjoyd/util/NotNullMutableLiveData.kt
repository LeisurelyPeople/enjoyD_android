package com.leisurely.people.enjoyd.util

import androidx.lifecycle.MutableLiveData

/**
 * MutableLiveData 클래스에 담기는 변수에 Null 허용하지 않게 하는 클래스
 * setValue 작업 시 반드시 not null 인 값을 넣어줘야 한다.
 *
 * @author Wayne
 * @since v1.0.0 / 2020.06.15
 */

class NotNullMutableLiveData<E : Any>(defaultValue: E) : MutableLiveData<E>(defaultValue) {

    override fun getValue(): E? {
        return super.getValue()!!
    }
}