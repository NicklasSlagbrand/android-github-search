package com.nicklasslagbrand.feature.repo.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nicklasslagbrand.baseline.R
import com.nicklasslagbrand.baseline.databinding.ItemRepoBinding
import com.nicklasslagbrand.core.entity.GithubRepo
import com.nicklasslagbrand.core.extension.loadImageWithFitCenterCircleCrop

class ReposAdapter : PagingDataAdapter<GithubRepo, ReposAdapter.RepoListViewHolder>(REPO_COMPARATOR) {
    var clickListener: (GithubRepo) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoListViewHolder =
        RepoListViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_repo,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: RepoListViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it, clickListener) }
    }

    class RepoListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemRepoBinding.bind(view)

        fun bind(repo: GithubRepo, clickListener: (GithubRepo) -> Unit) {
            with(binding) {
                tvTitle.text = repo.name
                tvDescription.text = repo.description
                ivAvatar.loadImageWithFitCenterCircleCrop(repo.owner.avatarUrl)
                root.setOnClickListener {
                    clickListener(repo)
                }
            }
        }
    }

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<GithubRepo>() {
            override fun areItemsTheSame(oldItem: GithubRepo, newItem: GithubRepo): Boolean =
                oldItem.fullName == newItem.fullName

            override fun areContentsTheSame(oldItem: GithubRepo, newItem: GithubRepo): Boolean =
                oldItem == newItem
        }
    }
}

