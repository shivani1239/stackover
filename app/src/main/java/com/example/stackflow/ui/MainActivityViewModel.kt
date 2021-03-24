package com.example.stackflow.ui

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.example.stackflow.R
import com.example.stackflow.base.BaseViewModel
import com.example.stackflow.core.ApiInterface
import com.example.stackflow.model.QuestionItemList
import com.example.stackflow.model.QuestionResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainActivityViewModel : BaseViewModel() {

    @Inject
    lateinit var apiInterface: ApiInterface

    private lateinit var subscription: Disposable
    var order: String = "desc"
    var sort: String = "activity"
    var site: String = "stackoverflow"

    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    var questionList1: MutableList<QuestionItemList> = mutableListOf()
    var questionList: MutableLiveData<MutableList<QuestionItemList>> =
        MutableLiveData(mutableListOf())
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()

    init {
        getData()
    }

    fun getData() {
        subscription = apiInterface.getQuestionList(order, sort, site)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                onPreRetrieveData()
            }
            .subscribe({ response -> onRetrieveData(response) },
                { throwable -> onDataError(throwable) })

    }

    private fun onPreRetrieveData() {
        errorMessage.value = null
        loadingVisibility.value = View.VISIBLE
    }

    private fun onDataError(throwable: Throwable?) {
        loadingVisibility.value = View.GONE
        errorMessage.value = R.string.error_msg
    }

    private fun onRetrieveData(response: QuestionResponse) {
        loadingVisibility.value = View.GONE
        questionList1 = response.questionItemLists
        questionList.value = response.questionItemLists
        Log.d("list", "" + questionList)
    }


    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }


}