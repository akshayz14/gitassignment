package com.example.gitassignment.data.repository

import androidx.paging.PagingData
import com.example.gitassignment.base.ApiResponse
import com.example.gitassignment.domain.entity.GitResponseEntity
import com.example.gitassignment.domain.entity.GitResponseEntityItem
import com.example.gitassignment.model.GithubRequestModel
import kotlinx.coroutines.flow.Flow


interface Repository {
    suspend fun fetchAllClosedPulls(githubRequestModel: GithubRequestModel): Flow<PagingData<GitResponseEntityItem>>
}