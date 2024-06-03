package team.payedin.android.gahasung.api

import retrofit2.http.GET
import retrofit2.http.Query
import team.payedin.android.gahasung.response.FetchPointsResponse
import team.payedin.android.gahasung.response.LoginResponse

interface UserApi {

    @GET("/users/point")
    suspend fun fetchPoints() : FetchPointsResponse

    @GET("users/sign-in")
    suspend fun login(
        @Query("gcn") gnc: String,
    ): LoginResponse
}
