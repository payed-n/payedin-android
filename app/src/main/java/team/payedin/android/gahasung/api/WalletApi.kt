package team.payedin.android.gahasung.api

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import team.payedin.android.gahasung.request.TransferRequest
import team.payedin.android.gahasung.response.FetchWalletHistoryResponse
import team.payedin.android.gahasung.response.FetchWalletResponse

interface WalletApi {
    @GET("/wallets/my")
    suspend fun fetchWallet(): FetchWalletResponse

    @GET("/wallets/my/history")
    suspend fun fetchWalletHistory(): FetchWalletHistoryResponse

    @POST("/wallets/transfer")
    suspend fun transfer(@Body req: TransferRequest)
}
