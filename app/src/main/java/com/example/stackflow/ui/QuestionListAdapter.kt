package com.example.stackflow.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.example.stackflow.R
import com.example.stackflow.model.QuestionItemList

class QuestionListAdapter(
    private var questionItemListList: MutableList<QuestionItemList>, private val context: Context,
    private val listener: QuestionClickListener
) :
    RecyclerView.Adapter<QuestionListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.unanswered_list, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return questionItemListList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.question.setText(questionItemListList.get(position).title)
        Glide.with(context).load(questionItemListList.get(position).owner.profileImage)
            .into(holder.authorImage)
        holder.question.setOnClickListener { listener.onQuestionClicked(position) }
    }

    class ViewHolder(itemLayoutView: View) : RecyclerView.ViewHolder(itemLayoutView) {
        @BindView(R.id.iv_author)
        lateinit var authorImage: ImageView

        @BindView(R.id.tv_question)
        lateinit var question: TextView

        @BindView(R.id.tv_time)
        lateinit var time: TextView

        @BindView(R.id.tv_java)
        lateinit var java: TextView

        @BindView(R.id.tv_c_plus)
        lateinit var tvCplus: TextView

        @BindView(R.id.tv_java_native)
        lateinit var javaNative: TextView

        init {
            ButterKnife.bind(this, itemLayoutView)
        }
    }

    interface QuestionClickListener {
        fun onQuestionClicked(position: Int)
    }
}