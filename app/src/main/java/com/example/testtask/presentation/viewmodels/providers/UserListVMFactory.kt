package com.example.testtask.presentation.viewmodels.providers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testtask.data.repository.UsersRepositoryImpl
import com.example.testtask.domain.usecases.GetNewUsersUseCase
import com.example.testtask.presentation.viewmodels.UserListViewModel

class UserListVMFactory : ViewModelProvider.Factory {

    private val usersRepository = UsersRepositoryImpl.get()
    private val getNewUsersUseCase = GetNewUsersUseCase(usersRepository)

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserListViewModel(getNewUsersUseCase) as T
    }
}