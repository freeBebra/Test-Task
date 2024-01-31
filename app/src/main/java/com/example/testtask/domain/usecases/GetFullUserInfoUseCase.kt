package com.example.testtask.domain.usecases

import com.example.testtask.domain.models.User
import com.example.testtask.domain.repositories.UsersRepository

class GetFullUserInfoUseCase(private val usersRepository: UsersRepository) {

    suspend operator fun invoke(userId: Int): User {
        return usersRepository.getUser(userId)
    }

}