package com.example.gitassignment.domain.usecase

import androidx.paging.PagingData
import com.example.gitassignment.data.repository.Repository
import com.example.gitassignment.domain.entity.GitResponseEntityItem
import com.example.gitassignment.model.GithubRequestModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetClosedPullsUseCase @Inject constructor(private val gitRepository: Repository) {
    suspend fun execute(param: GithubRequestModel): Flow<PagingData<GitResponseEntityItem>> {
        return gitRepository.fetchAllClosedPulls(param)
    }
}