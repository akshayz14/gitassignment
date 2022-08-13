package com.example.gitassignment.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.gitassignment.base.ApiResponse
import com.example.gitassignment.domain.entity.GitResponseEntity
import com.example.gitassignment.domain.entity.GitResponseEntityItem
import com.example.gitassignment.model.GithubRequestModel
import com.example.gitassignment.service.GitService
import com.example.testingpokemon.base.SafeApiCall
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

private const val STARTING_INDEX = 1
private const val NETWORK_PAGE_SIZE = 20

class GitPagingSource @Inject constructor(
    private val gitService: GitService,
    private val githubRequestModel: GithubRequestModel
) :
    PagingSource<Int, GitResponseEntityItem>(), SafeApiCall {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GitResponseEntityItem> {
        val pageIndex = params.key ?: STARTING_INDEX
        return try {
            val response = safeApiCall {
                gitService.fetchClosedPulls(
                    user = githubRequestModel.user,
                    repo = githubRequestModel.repoName,
                    page = pageIndex
                )
            }

            var gitList: GitResponseEntity? = null
            if (response is ApiResponse.Success) {
                gitList = response.value
            }
            if (response is ApiResponse.Failure) {
                val v = response.errorBody
                gitList = GitResponseEntity()
            }

            val nextKey =
                if (gitList?.isEmpty() == true) {
                    null
                } else {
                    pageIndex + (params.loadSize / NETWORK_PAGE_SIZE)
                }

            LoadResult.Page(
                data = gitList as List<GitResponseEntityItem>,
                prevKey = if (pageIndex == STARTING_INDEX) null else pageIndex,
                nextKey = nextKey
            )


        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, GitResponseEntityItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}