package com.example.gitassignment.module

import com.example.gitassignment.data.repository.GitRepository
import com.example.gitassignment.data.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {
    @Binds
    fun mainRepository(gitRepository: GitRepository) : Repository
}