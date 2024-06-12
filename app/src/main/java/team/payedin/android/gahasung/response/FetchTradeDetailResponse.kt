package team.payedin.android.gahasung.response

import com.google.gson.annotations.SerializedName
import team.payedin.android.gahasung.Status

data class FetchTradeDetailResponse(
    @SerializedName("trade") val trade: TradeDetail,
    @SerializedName("user") val user: User,
    @SerializedName("status") val status: Status?,
    @SerializedName("tradeRequestId") val tradeRequestId: String?,
)

data class TradeDetail(
    @SerializedName("id") val tradeId: String,
    @SerializedName("title") val title: String,
    @SerializedName("content") val content: String,
    @SerializedName("price") val price: Int,
    @SerializedName("imageUrl") val imageUrl: String,
    @SerializedName("userId") val userId: String,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("isNew") val isNew: Boolean,
    @SerializedName("identifier") val identifier: String
)

data class User(
    @SerializedName("id") val userId: String,
    @SerializedName("name") val name: String,
    @SerializedName("gcn") val gcn: String,
    @SerializedName("nickname") val nickname: String,
    @SerializedName("balance") val balance: Int,
    @SerializedName("accountNumber") val accountNumber: String,
    @SerializedName("bonusTotal") val bonusTotal: Int,
    @SerializedName("minusTotal") val minusTotal: Int,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("isNew") val isNew: Boolean,
    @SerializedName("identifier") val identifier: String
)
