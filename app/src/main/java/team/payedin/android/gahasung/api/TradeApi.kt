package team.payedin.android.gahasung.api

import retrofit2.http.GET
import retrofit2.http.Query
import team.payedin.android.gahasung.response.FetchTradeDetailResponse
import team.payedin.android.gahasung.response.FetchTradesResponse

interface TradeApi {

    @GET("/trades")
    suspend fun fetchTrades(): FetchTradesResponse

    @GET("/trades")
    suspend fun fetchTradesDetail(
        @Query(":trade_id") tradeId: Int,
    ): FetchTradeDetailResponse
}