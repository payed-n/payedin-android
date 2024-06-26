package team.payedin.android.gahasung.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import team.payedin.android.LoginActivity.Companion.token
import team.payedin.android.gahasung.response.User

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

    private fun getTokenInterceptor(): Interceptor {
        return Interceptor { chain ->
            val token = token
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
    fun userApi(): UserApi = getRetrofit().create(UserApi::class.java)

    fun login(): UserApi = getLogin().create(UserApi::class.java)
}