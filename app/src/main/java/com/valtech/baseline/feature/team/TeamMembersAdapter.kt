package com.valtech.baseline.feature.team

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
import com.valtech.baseline.domain.model.Member
import kotlin.properties.Delegates
import kotlinx.android.synthetic.main.item_team_member.view.*

class TeamMembersAdapter(val context: Context) : RecyclerView.Adapter<TeamMembersAdapter.TeamMembersViewHolder>() {
    var clickListener: (Member) -> Unit = {}

    var results: List<Member> by Delegates.observable(
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
        private val tvName: TextView = view.tvName
        private val tvPosition: TextView = view.tvPosition

        fun bind(teamMember: Member, clickListener: (Member) -> Unit) {
            tvName.text = "${teamMember.firstName} ${teamMember.lastName}"
            tvPosition.text = teamMember.title

            ivAvatar.loadImageWithFitCenterTransform(
                teamMember.avatarUrl,
                RequestOptions.circleCropTransform()
            )

            view.setOnClickListener { clickListener(teamMember) }
        }
    }
}
