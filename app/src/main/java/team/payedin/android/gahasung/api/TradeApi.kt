package team.payedin.android.gahasung.api

import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import team.payedin.android.gahasung.request.CreateTradeRequest
import team.payedin.android.gahasung.response.FetchTradeDetailResponse
import team.payedin.android.gahasung.response.FetchTradeRequestsResponse
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

    @POST("/trades/trade/{trade_id}")
    suspend fun createTradeRequest(
        @Path("trade_id") tradeId: String,
    )

    @GET("/trades/trade/req")
    suspend fun fetchTradeRequests(): FetchTradeRequestsResponse

    @DELETE("/trades/{trade_id}")
    suspend fun deleteTrade(
        @Path("trade_id") tradeId: String,
    )

    @POST("/trades/approve")
    suspend fun approveTrade(
        @Query("trade_request_id") id: String,
        @Query("approve") approve: Boolean,
    )

    @DELETE("/trades/req/{trade_req_id}")
    suspend fun deleteTradeReq(
        @Path("trade_req_id") id: String,
    )
}