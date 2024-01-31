package com.example.testtask.presentation.viewmodels

import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import com.example.testtask.R
import com.example.testtask.domain.models.Resource
import com.example.testtask.domain.models.User
import com.example.testtask.domain.models.UserBrief
import com.example.testtask.domain.usecases.DeleteSavedUsersUseCase
import com.example.testtask.domain.usecases.GetNewUsersUseCase
import com.example.testtask.domain.usecases.GetSavedUsersBriefUseCase
import com.example.testtask.domain.usecases.SaveUsersUseCase
import com.example.testtask.presentation.fragments.UserListFragmentDirections
import com.example.testtask.presentation.models.UserListUiState
import com.example.testtask.presentation.recyclerview.adapter.UserAdapter
import com.example.testtask.utils.toBrief
import kotlinx.coroutines.delay
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

    private var idsAreSet = (uiState.value.currentUsers?.find { it.id != UserBrief.NO_ID } != null)

    val listAdapter
        get() = UserAdapter().also {
            it.listener = ::navigateToDetails
        }

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
                val newUsers = UserListUiState.NewUsers(savedUsers)
                _uiState.update { newUsers }
                idsAreSet = true
            } else {
                updateUsersAndSave()
            }
        }
    }

    private suspend fun updateUsersAndSave() {
        _uiState.update { UserListUiState.Loading }
        val newUsersResource = getNewUsersUseCase.invoke()
        when (newUsersResource) {
            is Resource.Data -> {
                val usersBrief = newUsersResource.users.map { it.toBrief() }
                _uiState.update { UserListUiState.NewUsers(usersBrief) }
            }

            is Resource.Error -> {
                _uiState.update { UserListUiState.Error(newUsersResource.message) }
                return
            }
        }
        deleteSavedUsers()
        saveUsers(newUsersResource).also { ids ->
            setUserIds(ids)
        }

    }

    private suspend fun deleteSavedUsers() {
        deleteSavedUsersUseCase.invoke()
    }

    private suspend fun saveUsers(resource: Resource<User>): List<Int> {
        val remoteUsers = resource as Resource.Data
        return saveUsersUseCase.invoke(remoteUsers.users)
    }

    private fun setUserIds(ids: List<Int>) {
        uiState.value.currentUsers?.forEachIndexed { index, user ->
            user.id = ids[index]
        }
        idsAreSet = true
    }

    private fun navigateToDetails(view: View, userId: Int) {
        val navController = view.findNavController()
        viewModelScope.launch {
            if (!idsAreSet) {
                repeat(5) {
                    delay(50)
                }
            }
            if (idsAreSet) {
                val direction = UserListFragmentDirections.toDetailsFragment(userId)
                navController.navigate(direction)
            }
        }
    }

}