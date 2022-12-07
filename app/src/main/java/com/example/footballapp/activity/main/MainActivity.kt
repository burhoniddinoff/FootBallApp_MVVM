package com.example.footballapp.activity.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.footballapp.activity.espain.LeagueActivity
import com.example.footballapp.adapter.LeagueAdapter
import com.example.footballapp.databinding.ActivityMainBinding
import com.example.footballapp.network.RetroInstance
import com.example.footballapp.repository.FootballRepository

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val leagueAdapter by lazy { LeagueAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setUpRv()
        setUpViewModel()

    }

    private fun setUpRv() {
        binding.recyclerView.apply {
            adapter = leagueAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
        leagueAdapter.onClick = {
            startActivity(Intent(this, LeagueActivity::class.java))
        }
    }

    private fun setUpViewModel() {
        val repository = FootballRepository(RetroInstance.retroInstance())
        val viewModel = ViewModelProvider(this, MainViewModelFactory(repository))[MainViewModel::class.java]
        viewModel.state.observe(this) {
            when(it) {
                is MainState.Loading -> {
                    binding.progress.isVisible = true
                }
                is MainState.Error -> {
                    Toast.makeText(this, it.error, Toast.LENGTH_SHORT).show()
                }
                is MainState.Success -> {
                    leagueAdapter.submitList(it.list)
                    binding.progress.isVisible = false
                }
            }
        }
    }
}