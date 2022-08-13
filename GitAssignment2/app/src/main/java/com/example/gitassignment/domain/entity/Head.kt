package com.example.gitassignment.domain.entity

data class Head(
    val label: String,
    val ref: String,
    val repo: RepoX,
    val sha: String,
    val user: UserX
)