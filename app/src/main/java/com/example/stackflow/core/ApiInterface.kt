package com.example.stackflow.core

import com.example.stackflow.model.QuestionResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("no-answers?")
    fun getQuestionList(
        @Query("order") order: String,
        @Query("sort") sort: String,
        @Query("site") site: String): Observable<QuestionResponse>
}