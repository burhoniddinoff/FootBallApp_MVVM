package com.example.footballapp.activity.france

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.footballapp.R
import com.example.footballapp.activity.england.EnglandState
import com.example.footballapp.activity.england.EnglandViewModel
import com.example.footballapp.activity.england.EnglandViewModelFactory
import com.example.footballapp.adapter.EnglandAdapter
import com.example.footballapp.adapter.FranceAdapter
import com.example.footballapp.databinding.ActivityEnglandBinding
import com.example.footballapp.databinding.ActivityFranceBinding
import com.example.footballapp.network.RetroInstance
import com.example.footballapp.repository.FootballRepository

class FranceActivity : AppCompatActivity() {
    private val binding by lazy { ActivityFranceBinding.inflate(layoutInflater) }
    private val clubAdapter by lazy { FranceAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setUpRv()
        setUpViewModel()

    }

    private fun setUpViewModel() {
        val repository = FootballRepository(RetroInstance.retroInstance())
        val viewModel =
            ViewModelProvider(this, FranceViewModelFactory(repository))[FranceViewModel::class.java]
        viewModel.getClubById("168")
        viewModel.state.observe(this) {
            when (it) {
                FranceState.Loading -> {
                    binding.progress.isVisible = true
                }
                is FranceState.Error -> {
                    Toast.makeText(this, it.error, Toast.LENGTH_SHORT).show()
                }
                is FranceState.Success -> {
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
            layoutManager = LinearLayoutManager(this@FranceActivity)
        }
    }
}