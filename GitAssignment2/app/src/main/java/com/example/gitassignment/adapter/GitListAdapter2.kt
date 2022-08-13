package com.example.gitassignment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.gitassignment.R
import com.example.gitassignment.databinding.LayoutGithubListItemBinding
import com.example.gitassignment.model.GitHubResponseModel
import com.example.gitassignment.utils.loadImage

class GitListAdapter2 :
    PagingDataAdapter<GitHubResponseModel, GitListAdapter2.ViewHolder>(GithubComparator) {


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        var bindingVar = holder.binding
        item?.let {
            bindingVar.userIV.loadImage(
                item.userImage,
                R.drawable.loading_gif,
                R.drawable.error_img
            )
            bindingVar.closedDateDescriptionTV.text = item.closedDate
            bindingVar.createdDateDescriptionTV.text = item.createdAt
            bindingVar.titleDescriptionTV.text = item.title
            bindingVar.userDescriptionTV.text = item.userName
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutGithubListItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }


    object GithubComparator : DiffUtil.ItemCallback<GitHubResponseModel>() {
        override fun areItemsTheSame(
            oldItem: GitHubResponseModel,
            newItem: GitHubResponseModel
        ): Boolean {
            // Id is unique.
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(
            oldItem: GitHubResponseModel,
            newItem: GitHubResponseModel
        ): Boolean {
            return oldItem == newItem
        }
    }

    class ViewHolder(@NonNull val binding: LayoutGithubListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

}