package com.example.testtask.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtask.domain.models.Resource
import com.example.testtask.domain.models.User
import com.example.testtask.domain.usecases.GetNewUsersUseCase
import com.example.testtask.presentation.recyclerview.adapter.UserAdapter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserListViewModel(private val getNewUsersUseCase: GetNewUsersUseCase) : ViewModel() {
    val listAdapter = UserAdapter()

    private val _newUsers = MutableStateFlow<Resource<User>?>(null)
    val newUsers = _newUsers.asStateFlow()

    init {
        fetchResource()
    }

    private fun fetchResource() {
        viewModelScope.launch {
            val fetchedResource = getNewUsersUseCase.invoke()
            _newUsers.update { fetchedResource }
        }
    }

}