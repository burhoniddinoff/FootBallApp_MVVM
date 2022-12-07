package com.example.footballapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.footballapp.R
import com.example.footballapp.databinding.ItemClubBinding
import com.example.footballapp.databinding.ItemEnglandBinding
import com.example.footballapp.databinding.ItemGermanyBinding
import com.example.footballapp.model.Club
import com.example.footballapp.model.England
import com.example.footballapp.model.Germany
import com.squareup.picasso.Picasso

class GermanyAdapter : ListAdapter<Germany, GermanyAdapter.GermanyViewHolder>(DiffCallBack()) {

    private class DiffCallBack : DiffUtil.ItemCallback<Germany>() {
        override fun areItemsTheSame(oldItem: Germany, newItem: Germany): Boolean {
            return oldItem.league_id == newItem.league_id
        }

        override fun areContentsTheSame(oldItem: Germany, newItem: Germany): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GermanyViewHolder {
        return GermanyViewHolder(
            ItemGermanyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: GermanyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class GermanyViewHolder(private val binding: ItemGermanyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(club: Germany) {
            with(binding) {
                Picasso.get()
                    .load(club.team_badge)
                    .placeholder(R.drawable.ic_baseline_flag_24)
                    .into(imageView)

                tvClub.text = club.team_name
                tvRank.text = club.overall_league_position
                tvP.text = club.overall_league_payed
                tvW.text = club.overall_league_W
                tvD.text = club.overall_league_D
                tvL.text = club.overall_league_L/*
                tvF.text = club.overall_league_GF
                tvA.text = club.overall_league_GA*/
                tvPts.text = club.overall_league_PTS

            }
        }
    }

}