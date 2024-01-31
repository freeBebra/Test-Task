package com.example.testtask.presentation.models

import com.example.testtask.domain.models.UserBrief


sealed class UserListUiState(val currentUsers: List<UserBrief>?) {
    data object Loading : UserListUiState(null)
    data class NewUsers(val userList: List<UserBrief>) : UserListUiState(userList)
    data class Error(val message: String) : UserListUiState(null)
}
