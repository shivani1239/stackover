package com.example.stackflow.model

import com.google.gson.annotations.SerializedName

data class QuestionItemList(
    @SerializedName("tags")
    val tags: MutableList<String>,

    @SerializedName("owner")
    val owner: Owner,

    @SerializedName("is_answered")
    val isAnswered: Boolean,

    @SerializedName("view_count")
    val viewCount: Int,

    @SerializedName("answer_count")
    val answerCount: Int,

    @SerializedName("score")
    val score: Int,

    @SerializedName("last_activity_date")
    val lastActivityDate: Int,

    @SerializedName("creation_date")
    val creationDate: Int,

    @SerializedName("last_edit_date")
    val lastEditDate: Int,

    @SerializedName("question_id")
    val questionId: Int,

    @SerializedName("content_license")
    val contentLicense: String,

    @SerializedName("link")
    val link: String,

    @SerializedName("title")
    val title: String
)
