package com.example.testtask.presentation.models

import com.example.testtask.domain.models.UserBrief


sealed class UserListUiState {
    data object Loading : UserListUiState()
    data class Data(val userList: List<UserBrief>) : UserListUiState()
    data class Error(val message: String) : UserListUiState()
}
