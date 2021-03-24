package com.example.stackflow.model

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stackflow.R
import com.example.stackflow.core.KEY_stack__URL
import com.example.stackflow.databinding.ActivityMainBinding
import com.example.stackflow.ui.MainActivityViewModel
import com.example.stackflow.ui.QuestionListAdapter
import com.example.stackflow.ui.WebViewActivity
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), QuestionListAdapter.QuestionClickListener {

    private lateinit var questionAdapter: QuestionListAdapter
    private var errorSnackbar: Snackbar? = null
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.recyclerViewList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        binding.viewModel = viewModel

        questionAdapter = QuestionListAdapter(viewModel.questionList1,this)
        binding.recyclerViewList.adapter = questionAdapter

        viewModel.questionList.observe(this, Observer { questionList ->
            questionAdapter.updateQuetstionList(questionList)
        })

        viewModel.errorMessage.observe(this, Observer { errorMessage ->
            if (errorMessage != null) showError(errorMessage) else hideError()
        })

        setPullToRefreshLayout()
    }

    private fun setPullToRefreshLayout() {
        binding.swipeRefreshLayout.setColorSchemeColors(
            ContextCompat.getColor(
                this,
                R.color.colorAccent
            )
        )

        binding.swipeRefreshLayout.setOnRefreshListener() {
            viewModel.getData()
            binding.swipeRefreshLayout.isRefreshing = false;
        }
    }

    private fun showError(errorMessage: Int) {
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry) { viewModel.getData() }
        errorSnackbar?.show()
    }

    private fun hideError() {
        errorSnackbar?.dismiss()
    }

    override fun onQuestionClick(questionItemList: QuestionItemList) {
        val intent = Intent(this, WebViewActivity::class.java)
        intent.putExtra(KEY_stack__URL,questionItemList.link )
        startActivity(intent)
    }


}