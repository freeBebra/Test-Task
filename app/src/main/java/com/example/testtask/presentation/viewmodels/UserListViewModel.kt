package com.example.testtask.presentation.viewmodels

import android.view.MenuItem
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtask.R
import com.example.testtask.domain.models.Resource
import com.example.testtask.domain.models.User
import com.example.testtask.domain.usecases.DeleteSavedUsersUseCase
import com.example.testtask.domain.usecases.GetNewUsersUseCase
import com.example.testtask.domain.usecases.GetSavedUsersBriefUseCase
import com.example.testtask.domain.usecases.SaveUsersUseCase
import com.example.testtask.presentation.models.UserListUiState
import com.example.testtask.presentation.recyclerview.adapter.UserAdapter
import com.example.testtask.utils.toBrief
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserListViewModel(
    private val getNewUsersUseCase: GetNewUsersUseCase,
    private val getSavedUsersBriefUseCase: GetSavedUsersBriefUseCase,
    private val saveUsersUseCase: SaveUsersUseCase,
    private val deleteSavedUsersUseCase: DeleteSavedUsersUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UserListUiState>(UserListUiState.Loading)
    val uiState = _uiState.asStateFlow()

    val listAdapter get() = UserAdapter()

    init {
        fetchResource()
    }


    fun onMenuItemClickListener(menuItem: MenuItem): Boolean {
        if (menuItem.itemId == R.id.refresh && uiState.value != UserListUiState.Loading) {
            viewModelScope.launch {
                updateUsersAndSave()
            }
            return true
        }
        return false
    }

    private fun fetchResource() {
        viewModelScope.launch {
            _uiState.update { UserListUiState.Loading }
            val savedUsers = getSavedUsersBriefUseCase.invoke()
            if (savedUsers.isNotEmpty()) {
                val data = UserListUiState.Data(savedUsers)
                _uiState.update { data }
            } else {
                updateUsersAndSave()
            }
        }
    }

    private suspend fun updateUsersAndSave() {
        _uiState.update { UserListUiState.Loading }
        val newUsersResource = getNewUsersUseCase.invoke()
        viewModelScope.launch {
            when (newUsersResource) {
                is Resource.Data -> {
                    val usersBrief = newUsersResource.users.map { it.toBrief() }
                    _uiState.update { UserListUiState.Data(usersBrief) }
                }

                is Resource.Error -> {
                    _uiState.update { UserListUiState.Error(newUsersResource.message) }
                }
            }
        }
        viewModelScope.launch {
            deleteSavedUsers()
            saveUsers(newUsersResource)
        }
    }

    private suspend fun deleteSavedUsers() {
        deleteSavedUsersUseCase.invoke()
    }

    private suspend fun saveUsers(resource: Resource<User>) {
        val remoteUsers = resource as? Resource.Data
        remoteUsers?.let {
            saveUsersUseCase.invoke(it.users)
        }
    }

}