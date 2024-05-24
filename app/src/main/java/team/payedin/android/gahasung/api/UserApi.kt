package team.payedin.android.gahasung.api

import retrofit2.http.GET
import team.payedin.android.gahasung.response.FetchPointsResponse

interface UserApi {

    @GET("/users/point")
    suspend fun fetchPoints() : FetchPointsResponse
}
