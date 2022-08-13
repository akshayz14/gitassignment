package com.example.gitassignment.model

import androidx.annotation.Keep

@Keep
data class GithubRequestModel(
    val user: String,
    val repoName: String
)