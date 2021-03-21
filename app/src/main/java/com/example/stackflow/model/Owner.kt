package com.example.stackflow.model

import com.google.gson.annotations.SerializedName

data class Owner(
    @SerializedName("reputation")
    val reputation: Int,

    @SerializedName("user_id")
    val userId: Int,

    @SerializedName("user_type")
    val userTypeval: String,

    @SerializedName("accept_rate")
    val acceptRate: Int,

    @SerializedName("profile_image")
    val profileImage: String,

    @SerializedName("display_name")
    val displayName: String,

    @SerializedName("link")
    val link: String
)