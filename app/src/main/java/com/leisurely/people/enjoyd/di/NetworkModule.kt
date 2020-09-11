package com.leisurely.people.enjoyd.di

import com.leisurely.people.enjoyd.BuildConfig
import com.leisurely.people.enjoyd.data.remote.api.AuthService
import com.leisurely.people.enjoyd.data.remote.api.EnjoyDService
import com.leisurely.people.enjoyd.data.remote.interceptor.AuthInterceptor
import com.leisurely.people.enjoyd.model.enums.RetrofitQualifiers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Retrofit, OkHttp, DI(네트워크) 설정, 뷰에서 API 접근을 위한 파일
 *
 * @author Wayne
 * @since v1.0.0 / 2020.06.15
 */

/** 네트워크 모듈(DI) 설정 */
val networkModule = module {
    single { AuthInterceptor(androidContext()) }
    single { provideEnjoyDService(get(qualifier = RetrofitQualifiers.ENJOYD)) }
    single { provideAuthService(get(qualifier = RetrofitQualifiers.AUTH)) }
    single(RetrofitQualifiers.ENJOYD) { provideOkHttpClient(get()) }
    single(RetrofitQualifiers.AUTH) { provideDefaultOkHttpClient() }
}

/** Auth Api Service 생성 */
fun provideAuthService(okHttpClient: OkHttpClient): AuthService {
    return Retrofit.Builder().baseUrl("http://3.35.87.123/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(AuthService::class.java)
}

/** EnjoyD Api Service 생성 */
fun provideEnjoyDService(okHttpClient: OkHttpClient): EnjoyDService {
    return Retrofit.Builder().baseUrl("http://3.35.87.123/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(EnjoyDService::class.java)
}

/** 기본 OkHttpClient 생성 */
fun provideDefaultOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        })
        .build()
}

/** 토큰 인증/갱신 인터셉터를 가지고 있는 OkHttpClient 설정 */
fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        })
        .build()
}

/** Api 접근을 위한 메소드 */
fun provideApi(retrofit: Retrofit): APIService {
    return retrofit.create(APIService::class.java)
}