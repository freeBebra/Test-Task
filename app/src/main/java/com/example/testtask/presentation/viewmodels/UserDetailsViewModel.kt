package com.example.testtask.presentation.viewmodels

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import com.example.testtask.domain.models.User
import com.example.testtask.domain.usecases.GetFullUserInfoUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserDetailsViewModel(
    private val userId: Int,
    private val getFullUserInfoUseCase: GetFullUserInfoUseCase
) : ViewModel() {

    private val _user = MutableStateFlow<User?>(null)
    val user = _user.asStateFlow()

    init {
        viewModelScope.launch {
            val user = getFullUserInfoUseCase.invoke(userId)
            _user.update { user }
        }
    }

    fun onNavigationClick(view: View) {
        val navController = view.findNavController()
        navController.popBackStack()
    }
}