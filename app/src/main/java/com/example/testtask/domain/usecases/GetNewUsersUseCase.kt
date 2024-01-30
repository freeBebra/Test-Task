package com.example.testtask.domain.usecases

import com.example.testtask.domain.models.Resource
import com.example.testtask.domain.models.User
import com.example.testtask.domain.repositories.UsersRepository

class GetNewUsersUseCase(private val usersRepository: UsersRepository) {
    suspend operator fun invoke(): Resource<User> {
        return usersRepository.getNew(24)
    }
}