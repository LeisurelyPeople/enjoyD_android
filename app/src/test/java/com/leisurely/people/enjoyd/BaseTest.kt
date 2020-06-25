package com.leisurely.people.enjoyd

import com.leisurely.people.enjoyd.util.coroutine.isUnitTest
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.BeforeClass

/**
 * 모든 테스트 클래스의 공통
 * @author ricky
 * @since v1.0.0 / 2020.06.26
 */
abstract class BaseTest {
    companion object {
        /** 기본 테스트 설정 */
        @BeforeClass
        @JvmStatic
        fun setupClass() {
            // AndroidScheduler 를 JVM 에서 수행할 수 있게 설정
            RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }

            // 테스트를 위해 필수 Var 값 초기화
            isUnitTest = true
        }
    }
}
