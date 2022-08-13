package com.example.gitassignment.service

import com.example.gitassignment.base.ApiResponse
import com.example.gitassignment.domain.entity.GitResponseEntity
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitService {

    @GET("repos/{user}/{repo}/pulls?state=all")
    suspend fun fetchAllPulls(
        @Path("user") user: String,
        @Path("repo") repo: String
    ): ApiResponse<GitResponseEntity>


    @GET("repos/{user}/{repo}/pulls?state=closed")
    suspend fun fetchClosedPulls(
        @Path("user") user: String,
        @Path("repo") repo: String,
        @Query("page") page: Int
    ): GitResponseEntity

}