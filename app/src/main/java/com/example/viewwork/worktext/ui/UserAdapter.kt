package com.example.viewwork.worktext.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.viewwork.worktext.R
import com.example.viewwork.worktext.data.UserListData
import kotlinx.android.synthetic.main.item_user_list.view.*

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private var dataSet: MutableList<UserListData> = arrayListOf()

    fun updateData(dataSet: List<UserListData>) {
        this.dataSet.addAll(dataSet)
        notifyDataSetChanged()
    }


    private var onItemClick: ((data: UserListData) -> Unit)? = null

    fun setOnItemClickListener (onItemClick: ((data: UserListData) -> Unit)) {
        this.onItemClick = onItemClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_user_list, parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    private var onLoadMore: (() -> Unit)? = null

    fun setOnLoadMoreListener(onLoadMore: (() -> Unit)) {
        this.onLoadMore = onLoadMore
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        if (position == dataSet.size - 3) {
            onLoadMore?.invoke()
        }
        dataSet.let { holder.setData(it[position]) }
    }


    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setData(data: UserListData) {
            itemView.apply {
                textViewName.text = data.login
                Glide.with(imageView)
                    .load(data.avatarUrl)
                    .apply(RequestOptions.circleCropTransform())
                    .into(imageView)
                if (data.siteAdmin) {
                    textViewSiteAdmin.visibility = View.VISIBLE
                } else {
                    textViewSiteAdmin.visibility = View.GONE

                }
                setOnClickListener {
                    onItemClick?.invoke(data)
                }
            }
        }
    }
}