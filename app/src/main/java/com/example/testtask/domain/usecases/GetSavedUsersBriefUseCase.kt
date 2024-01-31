package com.example.testtask.domain.usecases

import com.example.testtask.domain.models.UserBrief
import com.example.testtask.domain.repositories.UsersRepository

class GetSavedUsersBriefUseCase(private val usersRepository: UsersRepository) {
    suspend operator fun invoke(): List<UserBrief> {
        return usersRepository.getSavedBrief()
    }
}