package team.payedin.android.gahasung.response

import com.google.gson.annotations.SerializedName

data class FetchWalletHistoryResponse(
    @SerializedName("histories") val histories: List<History>,
) {
    data class History(
        @SerializedName("name") val name: String,
        @SerializedName("balance") val balance: Long,
        @SerializedName("difference") val difference: Long,
        @SerializedName("createdAt") val createdAt: String,
    )
}
