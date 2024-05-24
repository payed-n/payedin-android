package team.payedin.android.gahasung.request

import com.google.gson.annotations.SerializedName

data class TransferRequest(
    @SerializedName("targetAccountNumber") val targetAccountNumber: String,
    @SerializedName("amount") val amount: Long,
)