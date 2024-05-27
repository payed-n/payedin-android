package team.payedin.android.gahasung.request

import com.google.gson.annotations.SerializedName

data class CreateTradeRequest(
    @SerializedName("imageUrl") val imageUrl: String,
    @SerializedName("title") val title: String,
    @SerializedName("content") val content: String,
    @SerializedName("price") val price: Int,
)