package team.payedin.android.gahasung.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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
            val token =
                "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJhYzFmMTI3Yi04ZmEzLTEyNDYtODE4Zi1hMzcyODQ2OTAwMDAiLCJpYXQiOjE3MTY0Njc0NzAsImV4cCI6MTcxNzQ2NzQ2OX0.d6l-DNgkgDigSGnvwcGi4O1M008JOtDYollZIl0ME7U"
            val request = chain.request().newBuilder()
                .addHeader(
                    "Authorization",
                    "Bearer $token"
                )
                .build()
            chain.proceed(request)
        }
    }

    fun tradeApi(): TradeApi = getRetrofit().create(TradeApi::class.java)

    fun walletApi(): WalletApi = getRetrofit().create(WalletApi::class.java)
}