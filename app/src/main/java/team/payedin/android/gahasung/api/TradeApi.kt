package team.payedin.android.gahasung.api

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import team.payedin.android.gahasung.request.CreateTradeRequest
import team.payedin.android.gahasung.response.FetchTradeDetailResponse
import team.payedin.android.gahasung.response.FetchTradesResponse

interface TradeApi {

    @GET("/trades")
    suspend fun fetchTrades(): FetchTradesResponse

    @GET("/trades/{trade_id}")
    suspend fun fetchTradesDetail(
        @Path("trade_id") tradeId: String,
    ): FetchTradeDetailResponse

    @POST("/trades")
    suspend fun createTrades(
        @Body createTradeRequest: CreateTradeRequest
    )
}