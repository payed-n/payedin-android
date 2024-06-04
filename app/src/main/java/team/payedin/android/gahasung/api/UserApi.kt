package team.payedin.android.gahasung.api

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import team.payedin.android.gahasung.request.LoginRequest
import team.payedin.android.gahasung.response.FetchPointsResponse
import team.payedin.android.gahasung.response.LoginResponse

interface UserApi {

    @GET("/users/point")
    suspend fun fetchPoints() : FetchPointsResponse

    @POST("/users/sign-in")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): LoginResponse
}
