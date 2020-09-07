package com.leisurely.people.enjoyd.ui.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.leisurely.people.enjoyd.data.remote.data.request.SignUpRequest
import com.leisurely.people.enjoyd.data.repository.AccountRepository
import com.leisurely.people.enjoyd.model.enums.Gender
import com.leisurely.people.enjoyd.ui.base.BaseViewModel
import com.leisurely.people.enjoyd.model.login.SocialLoginModel
import com.leisurely.people.enjoyd.util.ext.applySchedulers
import com.leisurely.people.enjoyd.util.ext.convertToServerDate
import com.leisurely.people.enjoyd.util.lifecycle.LiveEvent
import com.leisurely.people.enjoyd.util.observer.DisposableCompletableObserver

/**
 * UserInfoInputActivity ViewModel
 *
 * @author Wayne
 * @since v1.0.0 / 2020.07.20
 */

class UserInfoInputViewModel(
    socialLoginModel: SocialLoginModel,
    private val accountRepository: AccountRepository
) : BaseViewModel() {

    /** 유저 소셜로그인 고유값 LiveData */
    private val _userSocialId: MutableLiveData<Long> = MutableLiveData(socialLoginModel.socialId ?: -1)
    val userSocialId: LiveData<Long> = _userSocialId

    /** 유저 이름 LiveData */
    private val _username: MutableLiveData<String> = MutableLiveData(socialLoginModel.name ?: "")
    val username: MutableLiveData<String> = _username

    /** 유저 이름 경고 문구 LiveData */
    private val _showsUsernameWarningMsg: MutableLiveData<Boolean> =
        MutableLiveData(_username.value.isNullOrEmpty())
    val showsUsernameWarningMsg: LiveData<Boolean> = _showsUsernameWarningMsg

    /** 유저 생일 입력 data picker 를 시작하기 위한 LiveData */
    private val _startBirthDayPicker: LiveEvent<Unit> = LiveEvent()
    val startBirthDayPicker: LiveEvent<Unit> = _startBirthDayPicker

    /** 유저 생일 LiveData */
    private val _userBirthDay: MutableLiveData<String> = MutableLiveData()
    val userBirthDay: LiveData<String> = _userBirthDay

    /** 유저 생일 경고 문구 LiveData */
    private val _showsUserBirthDayWarningMsg: MutableLiveData<Boolean> = MutableLiveData(true)
    val showsUserBirthDayWarningMsg: LiveData<Boolean> = _showsUserBirthDayWarningMsg

    /** 유저 성별 LiveData */
    private val _userGender: MutableLiveData<Int> = MutableLiveData<Int>().apply {
        /** Int 값인 경우 null 이닌 -1 타입을 디폴트로 작성 */
        value = socialLoginModel.gender ?: -1
    }
    val userGender: LiveData<Int> = _userGender

    /** 유저 성별 경고 문구 LiveData */
    private val _showsUserGenderWarningMsg: MutableLiveData<Boolean> =
        MutableLiveData(_userGender.value == -1)
    val showsUserGenderWarningMsg: MutableLiveData<Boolean> = _showsUserGenderWarningMsg

    /** 유저 직업 LiveData */
    private val _userJob: MutableLiveData<String> = MutableLiveData()
    val userJob: LiveData<String> = _userJob

    /** 유저 직업 경고 문구 LiveData */
    private val _showsUserJobWarningMsg: MutableLiveData<Boolean> = MutableLiveData(true)
    val showsUserJobWarningMsg: LiveData<Boolean> = _showsUserJobWarningMsg

    /** 유저 상세 직업 LiveData */
    private val _userDetailJob: MutableLiveData<String> = MutableLiveData()
    val userDetailJob: MutableLiveData<String> = _userDetailJob

    /** 유저 상세 직업 경고 문구 LiveData */
    private val _showsUserDetailJobWarningMsg: MutableLiveData<Boolean> = MutableLiveData(true)
    val showsUserDetailJobWarningMsg: LiveData<Boolean> = _showsUserDetailJobWarningMsg

    /** 뒤로가기 버튼을 동작시키기 위한 LiveData */
    private val _startBackScreen: LiveEvent<Unit> = LiveEvent()
    val startBackScreen: LiveEvent<Unit> = _startBackScreen

    /** 메인 화면으로 이동 시키기 위한 LiveData */
    private val _startMain: LiveEvent<Unit> = LiveEvent()
    val startMain: LiveEvent<Unit> = _startMain


    /** 유저 이름 텍스트 변화 감지 메소드 */
    fun onUsernameChanged(charSequence: CharSequence) {
        _showsUsernameWarningMsg.value = charSequence.toString().isEmpty()
    }

    /** 액티비티 코드에 있는 데이터피커를 열기 위한 메소드  */
    fun onClickUserBirthDay() {
        _startBirthDayPicker.value = null
    }

    /** 액티비티 -> 뷰모델 값 셋팅을 위한 메소드 (유저 생일) */
    fun setUserBirthDayValue(value: String?) {
        value?.let {
            _userBirthDay.value = value
            _showsUserBirthDayWarningMsg.value = false
        }
    }

    /** 유저 성별 RadioButton 클릭메소드 */
    fun onUserGenderChanged(gender: Gender) {
        _userGender.value = gender.value
        _showsUserGenderWarningMsg.value = false // 유저 성별을 선택한 상태이므로 경고 메시지는 안보이도록 설정
    }

    /** 유저 직업 RadioButton 클릭메소드 */
    fun onUserJobChanged(value: String, needsDetailJob: Boolean) {
        _userJob.value = value
        _showsUserJobWarningMsg.value = false
        /** 사용자 직업이 "기타" 이면서 상세 직업칸이 비어있지 않을 경우 상세 직업 경고문 삭제 */
        if (needsDetailJob) {
            _showsUserDetailJobWarningMsg.value = _userDetailJob.value.isNullOrEmpty()
        } else {
            _showsUserDetailJobWarningMsg.value = false
        }
    }

    /** 유저 상세 직업 텍스트 변화 감지 메소드 */
    fun onUserDetailJobChanged(charSequence: CharSequence) {
        _showsUserDetailJobWarningMsg.value = charSequence.toString().isEmpty()
    }

    /** 뒤로가기 버튼을 동작시키기 위한 메소드 */
    fun onClickBackScreen() {
        _startBackScreen.value = null
    }

    /** 회원가입 작업 메소드 */
    fun onClickSignUp() {
        /** social id 값이 null or -1 인 경우 로그인 화면으로 전환 */
        val userSocialId = _userSocialId.value?.let {
            if (it == -1L) {
                showToast("잠시 후 다시 시도해주세요.")
                _startBackScreen.value = null
                return
            } else {
                it
            }
        } ?: kotlin.run {
            showToast("잠시 후 다시 시도해주세요.")
            _startBackScreen.value = null
            return
        }
        val username = _username.value!!

        val userJob = _userJob.value?.let {
            /** 기타 직업 군이면 상세 직업 정보를 입력 */
            if (it == "기타") {
                _userDetailJob.value ?: kotlin.run {
                    showToast("상세 직업을 입력해주세요.")
                    return
                }
            } else {
                it
            }
        } ?: kotlin.run {
            showToast("직업을 선택해주세요.")
            return
        }
        val userBirthday = _userBirthDay.value?.convertToServerDate() ?: kotlin.run {
            showToast("생일을 입력해주세요.")
            return
        }

        val userGender = _userGender.value?.let {
            if (it == -1) {
                showToast("성별을 선택해주세요.")
                return
            }
            it
        } ?: kotlin.run {
            showToast("성별을 선택해주세요.")
            return
        }

        val signUpRequest = SignUpRequest(userSocialId, username, userJob, userBirthday, userGender)

        accountRepository.requestSignUp(signUpRequest)
            .applySchedulers()
            .subscribeWith(object : DisposableCompletableObserver() {
                override fun onComplete() {
                    _startMain.value = null
                }

                override fun onError(e: Throwable) {
                    super.onError(e)
                    showToast("잠시 후 다시 시도해주세요.")
                }
            }).addDisposable()
    }

}