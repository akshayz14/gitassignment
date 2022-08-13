package com.example.gitassignment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.gitassignment.domain.usecase.GetClosedPullsUseCase
import com.example.gitassignment.mapper.GitEntityToModelMapper
import com.example.gitassignment.model.GitHubResponseModel
import com.example.gitassignment.model.GithubRequestModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class GithubViewModel @Inject constructor(
    private val getClosedPullsUseCase: GetClosedPullsUseCase,
    private val gitEntityToModelMapper: GitEntityToModelMapper
) : ViewModel() {

    suspend fun fetchGitPulls(
        user: String,
        repo: String
    ): Flow<PagingData<GitHubResponseModel>> {

        val gitHubRequestModel = GithubRequestModel(user, repo)

        val gitResult = withContext(Dispatchers.Default) {
            getClosedPullsUseCase.execute(gitHubRequestModel)
                .map { pagingData ->
                    pagingData.map {
                        gitEntityToModelMapper.map(it)
                    }
                }
                .cachedIn(viewModelScope)
        }
        return gitResult
    }

}