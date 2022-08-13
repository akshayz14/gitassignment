package com.example.gitassignment.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.gitassignment.domain.entity.GitResponseEntityItem
import com.example.gitassignment.model.GithubRequestModel
import com.example.gitassignment.pagination.GitPagingSource
import com.example.gitassignment.service.GitService
import com.example.testingpokemon.base.SafeApiCall
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GitRepository @Inject constructor(private val gitService: GitService) : Repository,
    SafeApiCall {

    companion object {
        private const val PAGING_SIZE = 20
    }

    override suspend fun fetchAllClosedPulls(githubRequestModel: GithubRequestModel): Flow<PagingData<GitResponseEntityItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGING_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                GitPagingSource(gitService = gitService, githubRequestModel)
            }
        ).flow
    }

}