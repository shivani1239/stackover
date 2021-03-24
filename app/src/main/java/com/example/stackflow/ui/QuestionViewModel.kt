package com.example.stackflow.ui

import androidx.lifecycle.MutableLiveData
import com.example.stackflow.base.BaseViewModel
import com.example.stackflow.model.QuestionItemList

class QuestionViewModel : BaseViewModel() {
    private val question = MutableLiveData<String>();
    private val imageUrl = MutableLiveData<String>();
    var questionList: MutableList<QuestionItemList> = mutableListOf()

    fun bind(questionItemList: QuestionItemList) {
        question.value = questionItemList.title
        imageUrl.value = questionItemList.owner.profileImage
        questionList.add(questionItemList)
    }

    fun getQuestion(): MutableLiveData<String> {
        return question
    }

    fun getImageUrl(): MutableLiveData<String> {
        return imageUrl
    }

}