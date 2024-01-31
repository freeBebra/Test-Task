package com.example.testtask.presentation.viewmodels.providers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testtask.data.repository.UsersRepositoryImpl
import com.example.testtask.domain.usecases.GetFullUserInfoUseCase
import com.example.testtask.presentation.viewmodels.UserDetailsViewModel

class UserDetailsVMFactory(private val userId: Int) : ViewModelProvider.Factory {

    private val usersRepository = UsersRepositoryImpl.get()
    private val getFullUserInfoUseCase = GetFullUserInfoUseCase(usersRepository)

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserDetailsViewModel(userId, getFullUserInfoUseCase) as T
    }
}