package com.example.testtask.domain.usecases

import com.example.testtask.domain.models.User
import com.example.testtask.domain.repositories.UsersRepository

class SaveUsersUseCase(private val usersRepository: UsersRepository) {
    suspend operator fun invoke(users: List<User>): List<Int> {
        return usersRepository.save(users)
    }
}