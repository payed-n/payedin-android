package team.payedin.android.gahasung.response

import com.google.gson.annotations.SerializedName

data class FetchWalletHistoryResponse(
    @SerializedName("histories") val histories: List<History>,
) {
    data class History(
        @SerializedName("balance") val balance: Long,
        @SerializedName("difference") val difference: Long,
        @SerializedName("name") val name: String,
        @SerializedName("userId") val userId: String,
        @SerializedName("id") val id: String,
        @SerializedName("createdAt") val createdAt: String,
        @SerializedName("isNew") val isNew: Boolean,
        @SerializedName("identifier") val identifier: String,
    )
}
