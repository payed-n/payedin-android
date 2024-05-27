package team.payedin.android.gahasung.response

import com.google.gson.annotations.SerializedName

data class FetchWalletResponse(
    @SerializedName("balance") val balance: Long,
    @SerializedName("accountNumber") val accountNumber: String,
    @SerializedName("name") val name: String,
)
