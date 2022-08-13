package com.example.gitassignment.model

import androidx.annotation.Keep

@Keep
data class GitHubResponseModel(

    /*Title, Created date, closed date, userName, user Image*/
    val title: String,
    val createdAt: String?,
    val closedDate: String?,
    val userName: String,
    val userImage: String?
)