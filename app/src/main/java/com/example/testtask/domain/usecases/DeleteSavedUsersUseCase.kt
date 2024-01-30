package com.example.testtask.domain.usecases

import com.example.testtask.domain.repositories.UsersRepository

class DeleteSavedUsersUseCase(private val usersRepository: UsersRepository) {
    suspend operator fun invoke() {
        return usersRepository.deleteAll()
    }
}