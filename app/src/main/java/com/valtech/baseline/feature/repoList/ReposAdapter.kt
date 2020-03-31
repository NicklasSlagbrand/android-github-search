package com.valtech.baseline.feature.repoList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.RequestOptions
import com.valtech.baseline.R
import com.valtech.baseline.core.extension.loadImageWithFitCenterTransform
import com.valtech.baseline.domain.model.GithubRepo
import kotlin.properties.Delegates
import kotlinx.android.synthetic.main.item_team_member.view.*

class ReposAdapter(val context: Context) : RecyclerView.Adapter<ReposAdapter.TeamMembersViewHolder>() {
    var clickListener: (GithubRepo) -> Unit = {}

    var results: List<GithubRepo> by Delegates.observable(
        emptyList()
    ) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamMembersViewHolder =
        TeamMembersViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_team_member,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: TeamMembersViewHolder, position: Int) {
        holder.bind(results[position], clickListener)
    }

    override fun getItemCount() = results.size

    class TeamMembersViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val ivAvatar: ImageView = view.ivAvatar
        private val tvName: TextView = view.tvTitle
        private val tvDescription: TextView = view.tvDescription

        fun bind(teamMember: GithubRepo, clickListener: (GithubRepo) -> Unit) {
            tvName.text = teamMember.title
            tvDescription.text = teamMember.description

            ivAvatar.loadImageWithFitCenterTransform(
                teamMember.owner.avatarUrl?: "",
                RequestOptions.circleCropTransform()
            )

            view.setOnClickListener { clickListener(teamMember) }
        }
    }
}
