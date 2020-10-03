package com.leisurely.people.enjoyd.util.coroutine

import android.util.Log
import androidx.annotation.VisibleForTesting
import com.leisurely.people.enjoyd.ui.base.EnjoyDApplication
import com.leisurely.people.enjoyd.util.Constant
import com.leisurely.people.enjoyd.util.ext.isNetworkConnected
import kotlinx.coroutines.*
import java.net.ConnectException
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

/**
 * 코루틴을 유용하게 사용할 수 있는 함수 및 콜스택 유실을 대비하기 위한 object 를 관리하는 유틸 클래스
 *
 * @author ricky
 * @since v1.0.0 / 2020.06.26
 */

/** 모든 값을 비우고 싶을 때 사용하는 내부 변수 */
private const val UNKNOWN_LOGIC = ""

/** 기본 예외 처리기 */
private fun defaultExceptionHandler(logicName: String) = CoroutineExceptionHandler { _, throwable ->
    throwable.printStackTrace()

//    if (!isTestable && logicName != UNKNOWN_LOGIC)
//        FCrashlytics.logException(CoroutineUtil.generateThrowable(logicName, throwable))
//    else
//        FCrashlytics.logException(throwable)
    CoroutineUtil.clear(logicName)
}

/** API 통신 시 성공/실패 여부를 구분해주는 메소드 */
suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> T
): ResultWrapper<T> {
    return if (EnjoyDApplication.instance.isNetworkConnected().not()) {
        ResultWrapper.Error(ConnectException(Constant.ERROR_NETWORK_CONNECTION_FAIL))
    } else { // 네트워크 연결이 되어있을 경우에만 API call 처리
        withContext(dispatcher) {
            try {
                ResultWrapper.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                ResultWrapper.Error(throwable)
            }
        }
    }
}

/** 예외가 발생해도 크래시를 일으키지 않는 최상위 [코루틴 스코프][CoroutineScope]를 생성한다. */
@Suppress("FunctionName")
fun SafeScope(
    context: CoroutineContext = EmptyCoroutineContext,
    logicName: String = ""
): CoroutineScope =
    CoroutineScope(context + SupervisorJob() + defaultExceptionHandler(logicName))

/**
 * defaultHandler 로 에러 핸들링을 하지 않는 [코루틴 스코프][CoroutineScope]를 생성한다.
 * 이 스코프를 사용할 시 exceptionHandler 를 반드시 추가 선언해주어야 한다.
 */
@Suppress("FunctionName")
fun UnSafeScope(context: CoroutineContext = EmptyCoroutineContext): CoroutineScope =
    CoroutineScope(context + SupervisorJob())

/** 예외가 발생해도 크래시를 일으키지 않는 리시버의 child [코루틴 스코프][CoroutineScope]를 생성한다. */
fun CoroutineScope.newSafeScope(logicName: String = ""): CoroutineScope =
    CoroutineScope(this.coroutineContext + SupervisorJob() + defaultExceptionHandler(logicName))

/**
 * 코루틴 관련 유틸 object
 */
object CoroutineUtil {
    val tag = javaClass.canonicalName

    /**
     * 콜스택에 들어갈 throwable 들을 관리하는 stack
     * [scopeChanged], [scopeCleared], [clear] 함수만 이 변수 안의 내용을 바꿀 수 있다.
     *
     * 이 변수는 외부로 보이면 안되므로 테스트 함수들을 여기에 작성한다.
     */
    // private var throwables: List<Throwable> = listOf()
    private var throwables: MutableMap<String, List<Throwable>> = mutableMapOf()

    /** [logicName] 의 이름을 가진 throwable 들을 지운다. */
    fun clear(logicName: String) {
        throwables[logicName] = listOf()
        // print("$logicName is clear")
    }

    /** [throwable] 의 message 이름을 가진 throwable 들을 지운다. */
    fun clear(throwable: Throwable?) {
        val logicName = throwable?.message ?: ""
        throwables[logicName] = listOf()
        // print("$logicName is clear")
    }

    /**
     * 에러가 발생할 경우 FCrashlytics 에 보낼 causeThrowable 를 만든다.
     * throwables 는 가공하지 않는다.
     *
     * @param logicName 추출할 로직 이름
     *                  custom Exception Handler 에서 에러를 캐치한 경우
     *                  가장 최근에 찍힌 throwable message 값을 가져온다.
     * @param throwable 발생한 throwable
     *
     * @return logicName 의 이름을 가진 throwable 의 스택트레이스를 더해준 throwable
     *         만약 logicName 이 없거나, 트레킹된 throwable 가 없는 경우 기존 throwable 를 반환한다.
     */
    fun generateThrowable(logicName: String, throwable: Throwable): Throwable {
        return try {
            val currentStackTraces =
                getStackTraceElements(
                    logicName
                )
            currentStackTraces.addAll(0, throwable.stackTrace.toList())
            throwable.stackTrace = currentStackTraces.toTypedArray()

            throwable
        }
        // 알수 없는 에러 발생 시 기존 throwable 리턴
        catch (e: Throwable) {
            // FCrashlytics.log("$tag 콜스택 throwable 생성 중 에러가 발생하여 본래값을 리턴함")
            throwable
        }
    }

    /** 해당 코루틴 로직의 스택트레이스를 가져온다 (MutableList 형식으로 가져온다) */
    private fun getStackTraceElements(
        logicName: String = UNKNOWN_LOGIC
    ): MutableList<StackTraceElement> {
        val stackTraceElements = mutableListOf<StackTraceElement>()

        // 그 이름을 message 로 가지고 있는 throwables 를 반환한다.
        val logicThrowables = throwables[logicName]?.filter { it.message == logicName } ?: listOf()
        logicThrowables.forEach { throwable -> stackTraceElements.addAll(throwable.stackTrace) }

        return stackTraceElements
    }

    /** throwable의 크기를 반환한다. */
    fun getThrowablesSize(logicName: String): Int = throwables[logicName]?.size ?: 0

    /** 해당 throwable 가 [throwables]에 있는지 확인한다. */
    @VisibleForTesting
    fun isThrowableFinished(logicName: String, throwable: Throwable?): Boolean =
        throwables[logicName]?.contains(throwable) == false

    /** 해당 코루틴 작업이 모두 종료되었는지 확인한다. */
    @VisibleForTesting
    fun isWorkFinished(logicName: String): Boolean = throwables[logicName].isNullOrEmpty()

    /**
     * scope 가 바뀔 경우 [throwables] 특정 로직 내에 [throwable] 을 추가한다.
     *
     * throwable 가 null 인 경우, throwable.message 를 찾지 않고
     * throwable 가 null 이 아닌 경우, [throwables] 에는 가공된 message 를 가지는 throwable 만 들어가므로
     * logicName 은 throwable.message 를 활용한다.
     */
    fun scopeChanged(throwable: Throwable?) {
        throwable?.let {
            val logicName = throwable.message!!
            val prevThrowables = mutableListOf<Throwable>().apply {
                add(it)
                addAll(throwables[logicName] ?: listOf())
            }

            throwables[logicName] = prevThrowables

            // print("${throwable.message} is scopeChanged")
        }
    }

    /**
     * [throwables] 특정 로직 내에서 해당 [throwable] 을 제거한다.
     *
     * throwable 가 null 인 경우, throwable.message 를 찾지 않고
     * throwable 가 null 이 아닌 경우, [throwables] 에는 가공된 message 를 가지는 throwable 만 들어가므로
     * logicName 은 throwable.message 를 활용한다.
     */
    fun scopeCleared(throwable: Throwable?) {
        // null 이 아닌 경우 logicName 에 맞는 이름을 map 에서 찾고, 특정 throwable 을 제거한다.
        throwable?.let {
            val logicName = it.message!!
            Log.i(tag, logicName)

            throwables[logicName] = throwables[logicName]?.filter { t -> t != it } ?: listOf()

            // print("${throwable.message} is scopeCleared")
        }
    }

    /** 현재 [throwable] 의 상태를 보여준다. (콜스택 내역을 확인하고 싶을 시 위의 print 주석 코드 활성화) */
    private fun print(currentAction: String) {
        Log.i(tag, currentAction)
        throwables.forEach { throwables ->
            Log.i(tag, " - ${throwables.key}'s size : ${throwables.value.size}")
        }
        Log.i(tag, "\n")
    }
}


