package com.example.stackflow.model

import com.google.gson.annotations.SerializedName

data class QuestionResponse(
    @SerializedName("items")
    val questionItemLists: MutableList<QuestionItemList>,

    @SerializedName("has_more")
    val hasMore: Boolean,

    @SerializedName("quota_max")
    val quotaMax: Int,

    @SerializedName("quota_remaining")
    val quotaRemaining: Int
)