package com.example.footballapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.footballapp.R
import com.example.footballapp.databinding.ItemItalyBinding
import com.example.footballapp.model.Italy
import com.squareup.picasso.Picasso

class ItalyAdapter : ListAdapter<Italy, ItalyAdapter.ItalyViewHolder>(DiffCallBack()) {

    private class DiffCallBack : DiffUtil.ItemCallback<Italy>() {
        override fun areItemsTheSame(oldItem: Italy, newItem: Italy): Boolean {
            return oldItem.league_id == newItem.league_id
        }

        override fun areContentsTheSame(oldItem: Italy, newItem: Italy): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItalyViewHolder {
        return ItalyViewHolder(
            ItemItalyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItalyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ItalyViewHolder(private val binding: ItemItalyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(club: Italy) {
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