package team.payedin.android.gahasung.response

import com.google.gson.annotations.SerializedName

data class FetchTradeRequestsResponse(
    @SerializedName("requests") val requests: List<TradeRequest>
)

data class TradeRequest(
    @SerializedName("tradeRequestId") val tradeRequestId: String,
    @SerializedName("tradeId") val tradeId: String,
    @SerializedName("imageUrl") val imageUrl: String,
    @SerializedName("title") val title: String,
    @SerializedName("userId") val userId: String,
    @SerializedName("nickname") val nickname: String
)