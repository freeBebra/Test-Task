package com.example.testtask.presentation.databinding.adapters

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testtask.presentation.models.UserListUiState
import com.example.testtask.presentation.recyclerview.adapter.UserAdapter
import com.google.android.material.snackbar.Snackbar

@BindingAdapter("app:imageByUrl")
fun setUrlImageToView(imageView: ImageView, url: String) {
    Glide.with(imageView).load(url).into(imageView)
}

@BindingAdapter("app:showSnackbarOnError")
fun View.showSnackbarOnError(uiState: UserListUiState) {
    if (uiState is UserListUiState.Error) {
        Snackbar.make(this, uiState.message, Snackbar.LENGTH_LONG).show()
    }
}

@BindingAdapter("app:userListOnUpdate")
fun RecyclerView.setUserListOnUpdate(uiState: UserListUiState) {
    if (uiState is UserListUiState.Data) {
        val userAdapter = adapter as? UserAdapter
        userAdapter?.submitList(uiState.userList)
    }
}