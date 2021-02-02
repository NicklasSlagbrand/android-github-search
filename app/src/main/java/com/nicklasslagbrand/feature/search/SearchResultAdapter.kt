package com.nicklasslagbrand.feature.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nicklasslagbrand.baseline.R
import com.nicklasslagbrand.baseline.databinding.ItemSearchResultBinding
import com.nicklasslagbrand.core.entity.GithubRepo
import com.nicklasslagbrand.core.extension.loadImageWithFitCenterCircleCrop

import kotlin.properties.Delegates

class SearchResultAdapter : RecyclerView.Adapter<SearchResultAdapter.RepoListViewHolder>() {
    var clickListener: (GithubRepo) -> Unit = {}

    var result: List<GithubRepo> by Delegates.observable(
        emptyList()
    ){ _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoListViewHolder =
        RepoListViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_search_result,
                parent,
                false
            )
        )

    override fun getItemCount() = result.size

    override fun onBindViewHolder(holder: RepoListViewHolder, position: Int) =
        result.get(position).let { holder.bind(it, clickListener) }

    class RepoListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemSearchResultBinding.bind(view)

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
}

