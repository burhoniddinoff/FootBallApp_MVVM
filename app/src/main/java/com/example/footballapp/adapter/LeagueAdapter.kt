package com.example.footballapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.footballapp.R
import com.example.footballapp.databinding.ItemLayoutBinding
import com.example.footballapp.model.League
import com.example.footballapp.network.RetroInstance
import com.squareup.picasso.Picasso

class LeagueAdapter : ListAdapter<League, LeagueAdapter.LeagueViewHolder>(DiffCallBack()) {

    lateinit var onClick: (League) -> Unit

    private class DiffCallBack : DiffUtil.ItemCallback<League>() {
        override fun areItemsTheSame(oldItem: League, newItem: League): Boolean {
            return oldItem.country_id == newItem.country_id
        }

        override fun areContentsTheSame(oldItem: League, newItem: League): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueViewHolder {
        return LeagueViewHolder(
            ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: LeagueViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class LeagueViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(league: League) {
            with(binding) {
                Picasso.get()
                    .load(league.country_logo.ifEmpty { RetroInstance.defaultUrl })
                    .placeholder(R.drawable.ic_baseline_flag_24)
                    .into(imageView)

                textName.text = league.country_name
                itemView.setOnClickListener {
                    onClick(league)
                }
            }
        }
    }

}