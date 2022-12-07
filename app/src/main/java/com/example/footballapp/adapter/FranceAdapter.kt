package com.example.footballapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.footballapp.R
import com.example.footballapp.databinding.ItemClubBinding
import com.example.footballapp.databinding.ItemEnglandBinding
import com.example.footballapp.databinding.ItemFranceBinding
import com.example.footballapp.model.Club
import com.example.footballapp.model.England
import com.example.footballapp.model.France
import com.squareup.picasso.Picasso

class FranceAdapter : ListAdapter<France, FranceAdapter.FranceViewHolder>(DiffCallBack()) {

    private class DiffCallBack : DiffUtil.ItemCallback<France>() {
        override fun areItemsTheSame(oldItem: France, newItem: France): Boolean {
            return oldItem.league_id == newItem.league_id
        }

        override fun areContentsTheSame(oldItem: France, newItem: France): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FranceViewHolder {
        return FranceViewHolder(
            ItemFranceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: FranceViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class FranceViewHolder(private val binding: ItemFranceBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(club: France) {
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