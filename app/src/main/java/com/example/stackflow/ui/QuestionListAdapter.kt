package com.example.stackflow.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.stackflow.R
import com.example.stackflow.databinding.UnansweredListBinding
import com.example.stackflow.model.QuestionItemList

class QuestionListAdapter(var questionItemListList: MutableList<QuestionItemList>,var questionClickListener: QuestionClickListener
) : RecyclerView.Adapter<QuestionListAdapter.ViewHolder>() {


    fun updateQuetstionList(questionItemList:MutableList<QuestionItemList>){
       this.questionItemListList= questionItemList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: UnansweredListBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.unanswered_list,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return  questionItemListList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(questionItemListList[position])
        holder.binding.tvQuestion.setOnClickListener {
            questionClickListener.onQuestionClick(questionItemListList[position])
        }

    }

    class ViewHolder(val binding: UnansweredListBinding) : RecyclerView.ViewHolder(binding.root) {
        private val viewModel = QuestionViewModel()

        fun bind(questionItemList: QuestionItemList) {
            viewModel.bind(questionItemList)
            binding.viewModel = viewModel
            binding.executePendingBindings()
        }
    }


    interface QuestionClickListener {
        fun onQuestionClick(questionItemList: QuestionItemList)
    }
}