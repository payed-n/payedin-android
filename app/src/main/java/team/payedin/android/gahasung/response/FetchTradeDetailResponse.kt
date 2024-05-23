package team.payedin.android.gahasung.response

import com.google.gson.annotations.SerializedName
import team.payedin.android.gahasung.Status

data class FetchTradeDetailResponse(
    @SerializedName("trade") val trade: TradeDetail,
    @SerializedName("user") val user: User,
    @SerializedName("status") val status: Status?,
)

data class TradeDetail(
    @SerializedName("tradeId") val tradeId: String,
    @SerializedName("nickname") val nickname: String,
    @SerializedName("title") val title: String,
    @SerializedName("content") val content: String,
    @SerializedName("price") val price: Int,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("imageUrl") val imageUrl: String
)

data class User(
    @SerializedName("userId") val userId: String,
    @SerializedName("nickname") val nickname: String
)

