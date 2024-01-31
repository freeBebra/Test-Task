package com.example.testtask.presentation.recyclerview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.testtask.R
import com.example.testtask.databinding.ListItemUserBinding
import com.example.testtask.domain.models.UserBrief

class UserAdapter : ListAdapter<UserBrief, UserAdapter.UserViewHolder>(DiffUtilCallback()) {

    var listener: ((view: View, userId: Int) -> Unit)? = null

    inner class UserViewHolder(view: View) : ViewHolder(view) {

        private val binding = ListItemUserBinding.bind(view)

        init {
            view.setOnClickListener {
                listener?.invoke(it, currentList[adapterPosition].id)
            }
        }

        fun bind(user: UserBrief) {
            binding.user = user
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    class DiffUtilCallback : DiffUtil.ItemCallback<UserBrief>() {
        override fun areItemsTheSame(oldItem: UserBrief, newItem: UserBrief): Boolean {
            return oldItem.phone == newItem.phone
        }

        override fun areContentsTheSame(oldItem: UserBrief, newItem: UserBrief): Boolean {
            return oldItem == newItem
        }

    }
}