package com.leisurely.people.enjoyd.di

import com.leisurely.people.enjoyd.BuildConfig
import com.leisurely.people.enjoyd.data.remote.api.APIService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
    factory { provideOkHttpClient() }
    factory { provideApi(get()) }
    single { provideRetrofit(get()) }
}

/** Retrofit 설정 */
fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl("http://3.34.113.117/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
}

/** OkHttp 설정 */
fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor {
            val request = it.request()
                .newBuilder()
                .build()
            it.proceed(request)
        }
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