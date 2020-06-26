package com.leisurely.people.enjoyd

import android.app.Application
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.core.app.ApplicationProvider
import com.leisurely.people.enjoyd.util.coroutine.appContext
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/**
 * Robolectric 을 사용하는 모든 테스트 클래스의 공통
 *
 * @author ricky
 * @since v1.0.0 / 2020.06.26
 */
@RunWith(AndroidJUnit4::class)
@Config(application = Application::class)
abstract class AndroidBaseTest : BaseTest() {
    @Before
    fun setupContext() {
        appContext = ApplicationProvider.getApplicationContext()
    }
}
