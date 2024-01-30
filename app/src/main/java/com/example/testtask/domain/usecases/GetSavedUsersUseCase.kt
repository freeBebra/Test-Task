package com.example.testtask.domain.usecases

import com.example.testtask.domain.models.User
import com.example.testtask.domain.repositories.UsersRepository

class GetSavedUsersUseCase(private val usersRepository: UsersRepository) {
    suspend operator fun invoke(): List<User> {
        return usersRepository.getSaved()
    }
}