package com.example.footballapp.activity.league

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.footballapp.adapter.ClubAdapter
import com.example.footballapp.databinding.ActivityLeagueBinding
import com.example.footballapp.network.RetroInstance
import com.example.footballapp.repository.FootballRepository

class LeagueActivity : AppCompatActivity() {
    private val binding by lazy { ActivityLeagueBinding.inflate(layoutInflater) }
    private val clubAdapter by lazy { ClubAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setUpRv()
        setUpViewModel()

    }

    private fun setUpViewModel() {
        val repository = FootballRepository(RetroInstance.retroInstance())
        val viewModel =
            ViewModelProvider(this, LeagueViewModelFactory(repository))[LeagueViewModel::class.java]
        viewModel.getClubById("302")
        viewModel.state.observe(this) {
            when (it) {
                LeagueState.Loading -> {
                    binding.progress.isVisible = true
                }
                is LeagueState.Error -> {
                    Toast.makeText(this, it.error, Toast.LENGTH_SHORT).show()
                }
                is LeagueState.Success -> {
                    clubAdapter.submitList(it.list)
                    binding.progress.isVisible = false
                    Log.d("@@@Success", "setupViewModel: ${it.list}")
                }
            }
        }

    }

    private fun setUpRv() {
        binding.recyclerView.apply {
            adapter = clubAdapter
            layoutManager = LinearLayoutManager(this@LeagueActivity)
        }
    }
}