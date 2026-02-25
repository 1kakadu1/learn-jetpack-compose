package com.example.entertainhub.data.remote

import com.example.entertainhub.BuildConfig
import com.example.entertainhub.data.remote.api.TmdbApi
import okhttp3.Dns
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.InetSocketAddress
import java.net.Proxy
import java.util.concurrent.TimeUnit

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader(
                "Authorization",
                "Bearer ${BuildConfig.TMDB_API_KEY}"
            )
            .build()

        return chain.proceed(request)
    }
}

object RetrofitInstance {
    private const val BASE_URL = "https://api.themoviedb.org/3/"

    private val okHttpClient = OkHttpClient.Builder()
        .apply {
            if (BuildConfig.HTTP_PROXY === "true") {
                val proxy = Proxy(
                    Proxy.Type.HTTP,  // HTTP прокси
                    InetSocketAddress(
                        "10.0.2.2",
                        2080
                    )
                )
                proxy(proxy)
                dns(Dns.SYSTEM)
            }

            if (BuildConfig.DEBUG) {
                val loggingInterceptor = HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
                addInterceptor(loggingInterceptor)
            }
        }
        .addInterceptor(AuthInterceptor())
        // Таймауты
        .connectTimeout(30, TimeUnit.SECONDS)  // Подключение
        .readTimeout(30, TimeUnit.SECONDS)     // Чтение
        .writeTimeout(30, TimeUnit.SECONDS)    // Запись
        .build()

    /**
     * Retrofit экземпляр
     * lazy = создаётся только при первом обращении
     */
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)  // Базовый URL
            .client(okHttpClient)  // HTTP клиент
            .addConverterFactory(GsonConverterFactory.create())  // JSON → Kotlin
            .build()
    }

    /**
     * API экземпляр
     * lazy = создаётся один раз
     * retrofit.create() - Retrofit генерирует реализацию интерфейса
     */
    val api: TmdbApi by lazy {
        retrofit.create(TmdbApi::class.java)
    }
}