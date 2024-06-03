package team.payedin.android.gahasung.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import team.payedin.android.LoginActivity.Companion.token

object ApiProvider {
    private var BASE_URL = "http://43.201.226.60:8080"

    private fun getLoggingInterceptor() =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(getLoggingInterceptor())
                    .addInterceptor(getTokenInterceptor())
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getTokenInterceptor(): Interceptor {
        return Interceptor { chain ->
            val token = token.ifBlank {
                "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJhYzFmMTI3Yi04ZmEzLTEyNDYtODE4Zi1hMzcyODQ2OTAwMDAiLCJpYXQiOjE3MTY0Njc0NzAsImV4cCI6MTcxNzQ2NzQ2OX0.d6l-DNgkgDigSGnvwcGi4O1M008JOtDYollZIl0ME7U"
            }
            val request = chain.request().newBuilder()
                .addHeader(
                    "Authorization",
                    "Bearer $token"
                )
                .build()
            chain.proceed(request)
        }
    }

    private fun getLogin(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(getLoggingInterceptor())
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun tradeApi(): TradeApi = getRetrofit().create(TradeApi::class.java)

    fun walletApi(): WalletApi = getRetrofit().create(WalletApi::class.java)
    fun userApi(): UserApi = getRetrofit().create(UserApi::class.java)

    fun login(): UserApi = getLogin().create(UserApi::class.java)
}