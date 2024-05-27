package team.payedin.android.gahasung.response

import com.google.gson.annotations.SerializedName

data class FetchTradesResponse(
    @SerializedName("trade") val trade: List<Trade>
)
data class Trade(
    @SerializedName("tradeId") val tradeId: String,
    @SerializedName("isMine") val isMine: Boolean,
    @SerializedName("nickname") val nickname: String,
    @SerializedName("title") val title: String,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("imageUrl") val imageUrl: String
)
