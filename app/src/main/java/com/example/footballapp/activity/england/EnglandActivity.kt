package com.example.footballapp.activity.england

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.footballapp.activity.league.LeagueState
import com.example.footballapp.adapter.ClubAdapter
import com.example.footballapp.adapter.EnglandAdapter
import com.example.footballapp.databinding.ActivityEnglandBinding
import com.example.footballapp.network.RetroInstance
import com.example.footballapp.repository.FootballRepository

class EnglandActivity : AppCompatActivity() {

    private val binding by lazy { ActivityEnglandBinding.inflate(layoutInflater) }
    private val clubAdapter by lazy { EnglandAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setUpRv()
        setUpViewModel()

    }

    private fun setUpViewModel() {
        val repository = FootballRepository(RetroInstance.retroInstance())
        val viewModel =
            ViewModelProvider(this, EnglandViewModelFactory(repository))[EnglandViewModel::class.java]
        viewModel.getClubById("152")
        viewModel.state.observe(this) {
            when (it) {
                EnglandState.Loading -> {
                    binding.progress.isVisible = true
                }
                is EnglandState.Error -> {
                    Toast.makeText(this, it.error, Toast.LENGTH_SHORT).show()
                }
                is EnglandState.Success -> {
                    clubAdapter.submitList(it.list)
                    binding.progress.isVisible = false
                    Log.d("@@@England", "setupViewModel: ${it.list}")
                }
            }
        }

    }

    private fun setUpRv() {
        binding.recyclerView.apply {
            adapter = clubAdapter
            layoutManager = LinearLayoutManager(this@EnglandActivity)
        }
    }

}