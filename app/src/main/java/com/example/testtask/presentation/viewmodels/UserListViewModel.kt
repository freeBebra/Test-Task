package com.example.testtask.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.example.testtask.presentation.recyclerview.adapter.UserAdapter

class UserListViewModel : ViewModel() {
    val listAdapter = UserAdapter()
}