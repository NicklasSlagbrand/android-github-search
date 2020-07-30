package com.nicklasslagbrand.baseline.feature.repo.repoList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.RequestOptions
import com.nicklasslagbrand.baseline.R
import com.nicklasslagbrand.baseline.core.extension.loadImageWithFitCenterTransform
import com.nicklasslagbrand.baseline.domain.model.GithubRepo
import kotlinx.android.synthetic.main.item_repo.view.*

class ReposAdapter : PagedListAdapter<GithubRepo, ReposAdapter.RepoListViewHolder>(DiffUtilCallBack()) {
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

    class RepoListViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(repo: GithubRepo, clickListener: (GithubRepo) -> Unit) {
            view.tvTitle.text = repo.title
            view.tvDescription.text = repo.description

            view.ivAvatar.loadImageWithFitCenterTransform(
                repo.owner.avatarUrl ?: "",
                RequestOptions.circleCropTransform()
            )
            view.setOnClickListener {
                clickListener(repo)
            }
        }
    }
}
class DiffUtilCallBack : DiffUtil.ItemCallback<GithubRepo>() {
    override fun areItemsTheSame(oldItem: GithubRepo, newItem: GithubRepo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: GithubRepo, newItem: GithubRepo): Boolean {
        return oldItem.id == newItem.id
    }
}
