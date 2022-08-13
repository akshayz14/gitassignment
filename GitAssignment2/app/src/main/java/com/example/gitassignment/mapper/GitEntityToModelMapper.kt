package com.example.gitassignment.mapper

import com.example.gitassignment.domain.entity.GitResponseEntityItem
import com.example.gitassignment.model.GitHubResponseModel
import javax.inject.Inject

class GitEntityToModelMapper @Inject constructor() {
    fun map(srcObject: GitResponseEntityItem): GitHubResponseModel {
        return GitHubResponseModel(
            srcObject.title,
            srcObject.created_at,
            srcObject.closed_at,
            srcObject.user.login,
            srcObject.user.avatar_url
        )
    }
}