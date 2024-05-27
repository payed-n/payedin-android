package team.payedin.android.gahasung.response

import com.google.gson.annotations.SerializedName

data class FetchPointsResponse(
    @SerializedName("plusPoint") val plusPoint: Int,
    @SerializedName("minusPoint") val minusPoint: Int,
)
