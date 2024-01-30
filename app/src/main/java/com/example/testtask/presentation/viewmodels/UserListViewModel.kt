package com.example.testtask.presentation.viewmodels

import android.view.MenuItem
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtask.R
import com.example.testtask.domain.models.Resource
import com.example.testtask.domain.models.User
import com.example.testtask.domain.usecases.DeleteSavedUsersUseCase
import com.example.testtask.domain.usecases.GetNewUsersUseCase
import com.example.testtask.domain.usecases.GetSavedUsersUseCase
import com.example.testtask.domain.usecases.SaveUsersUseCase
import com.example.testtask.presentation.recyclerview.adapter.UserAdapter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserListViewModel(
    private val getNewUsersUseCase: GetNewUsersUseCase,
    private val getSavedUsersUseCase: GetSavedUsersUseCase,
    private val saveUsersUseCase: SaveUsersUseCase,
    private val deleteSavedUsersUseCase: DeleteSavedUsersUseCase
) : ViewModel() {
    val listAdapter = UserAdapter()

    private val _newUsers = MutableStateFlow<Resource<User>?>(null)
    val newUsers = _newUsers.asStateFlow()

    init {
        fetchResource()
    }

    fun onMenuItemClickListener(menuItem: MenuItem): Boolean {
        if (menuItem.itemId == R.id.refresh) {
            viewModelScope.launch {
                updateUsersAndSave()
            }
            return true
        }
        return false
    }

    private fun fetchResource() {
        viewModelScope.launch {
            val savedUsers = getSavedUsersUseCase.invoke()
            if (savedUsers.isNotEmpty()) {
                _newUsers.update { Resource.Data(savedUsers) }
                return@launch
            }
            updateUsersAndSave()
        }
    }

    private suspend fun updateUsersAndSave() {
        val newUsersResource = getNewUsersUseCase.invoke()
        viewModelScope.launch {
            _newUsers.update { newUsersResource }
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