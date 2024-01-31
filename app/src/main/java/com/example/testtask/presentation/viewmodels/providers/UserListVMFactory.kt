package com.example.testtask.presentation.viewmodels.providers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testtask.data.repository.UsersRepositoryImpl
import com.example.testtask.domain.usecases.DeleteSavedUsersUseCase
import com.example.testtask.domain.usecases.GetNewUsersUseCase
import com.example.testtask.domain.usecases.GetSavedUsersBriefUseCase
import com.example.testtask.domain.usecases.SaveUsersUseCase
import com.example.testtask.presentation.viewmodels.UserListViewModel

class UserListVMFactory : ViewModelProvider.Factory {

    private val usersRepository = UsersRepositoryImpl.get()
    private val getNewUsersUseCase = GetNewUsersUseCase(usersRepository)
    private val getSavedUsersBriefUseCase = GetSavedUsersBriefUseCase(usersRepository)
    private val saveUsersUseCase = SaveUsersUseCase(usersRepository)
    private val deleteSavedUsersUseCase = DeleteSavedUsersUseCase(usersRepository)

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserListViewModel(
            getNewUsersUseCase,
            getSavedUsersBriefUseCase,
            saveUsersUseCase,
            deleteSavedUsersUseCase
        ) as T
    }
}