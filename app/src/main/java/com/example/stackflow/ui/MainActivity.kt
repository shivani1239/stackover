package com.example.stackflow.model

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import butterknife.BindView
import butterknife.ButterKnife
import com.example.stackflow.R
import com.example.stackflow.core.KEY_stack__URL
import com.example.stackflow.core.NetworkProvider
import com.example.stackflow.ui.QuestionListAdapter
import com.example.stackflow.ui.WebViewActivity
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MainActivity : AppCompatActivity(), QuestionListAdapter.QuestionClickListener {

    @BindView(R.id.parentLayout)
    lateinit var root: RelativeLayout

    @BindView(R.id.recycler_view_list)
    lateinit var recyclerViewQuestionList: RecyclerView

    @BindView(R.id.swipe_refresh_layout)
    lateinit var listRefreshLayout: SwipeRefreshLayout

    lateinit var questionAdapter: QuestionListAdapter
    lateinit var progressBar: ProgressDialog
    var errorSnackbar: Snackbar? = null

    var questionList = ArrayList<QuestionItemList>()
    lateinit var stackOverFlowUrl: String

    var order: String = "desc"
    var sort: String = "activity"
    var site: String = "stackoverflow"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)

        recyclerViewQuestionList.adapter = QuestionListAdapter(questionList, this, this)
        recyclerViewQuestionList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        progressBar = ProgressDialog(this)
        progressBar.setTitle(getString(R.string.loading))
        progressBar.show()

        getData()

        setPullToRefreshLayout()
    }

    private fun setPullToRefreshLayout() {
        listRefreshLayout.setColorSchemeColors(ContextCompat.getColor(this, R.color.colorAccent))

        listRefreshLayout.setOnRefreshListener() {
            getData()
            listRefreshLayout.isRefreshing = false;
        }
    }

    private fun getData() {
        val call: Call<QuestionResponse> =
            NetworkProvider.getClient.getQuestionList(order, sort, site)
        call.enqueue(object : Callback<QuestionResponse> {

            override fun onResponse(
                call: Call<QuestionResponse>?,
                response: Response<QuestionResponse>?
            ) {
                progressBar.hide()
                questionList.clear()
                questionList.addAll(response!!.body()!!.questionItemLists)
                recyclerViewQuestionList.adapter?.notifyDataSetChanged()
                hideError()
            }

            override fun onFailure(call: Call<QuestionResponse>?, t: Throwable?) {
                progressBar.hide()
                showError(getString(R.string.error_msg))
            }

        })
    }

    override fun onQuestionClicked(position: Int) {
        val intent = Intent(this, WebViewActivity::class.java)
        intent.putExtra(KEY_stack__URL, questionList.get(position).link)
        startActivity(intent)
    }

    private fun showError(errorMessage: String) {
        errorSnackbar = Snackbar.make(root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry) { getData() }
        errorSnackbar?.show()
    }

    private fun hideError() {
        errorSnackbar?.dismiss()
    }
}