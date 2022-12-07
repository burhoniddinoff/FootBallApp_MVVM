package com.example.footballapp.activity.italy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.footballapp.R
import com.example.footballapp.activity.france.FranceState
import com.example.footballapp.activity.france.FranceViewModel
import com.example.footballapp.activity.france.FranceViewModelFactory
import com.example.footballapp.adapter.FranceAdapter
import com.example.footballapp.adapter.ItalyAdapter
import com.example.footballapp.databinding.ActivityFranceBinding
import com.example.footballapp.databinding.ActivityItalyBinding
import com.example.footballapp.network.RetroInstance
import com.example.footballapp.repository.FootballRepository

class ItalyActivity : AppCompatActivity() {
    private val binding by lazy { ActivityItalyBinding.inflate(layoutInflater) }
    private val clubAdapter by lazy { ItalyAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setUpRv()
        setUpViewModel()

    }

    private fun setUpViewModel() {
        val repository = FootballRepository(RetroInstance.retroInstance())
        val viewModel =
            ViewModelProvider(this, ItalyViewModelFactory(repository))[ItalyViewModel::class.java]
        viewModel.getClubById("207")
        viewModel.state.observe(this) {
            when (it) {
                ItalyState.Loading -> {
                    binding.progress.isVisible = true
                }
                is ItalyState.Error -> {
                    Toast.makeText(this, it.error, Toast.LENGTH_SHORT).show()
                }
                is ItalyState.Success -> {
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
            layoutManager = LinearLayoutManager(this@ItalyActivity)
        }
    }
}