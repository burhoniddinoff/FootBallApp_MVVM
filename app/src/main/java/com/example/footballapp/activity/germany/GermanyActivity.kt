package com.example.footballapp.activity.germany

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
import com.example.footballapp.adapter.GermanyAdapter
import com.example.footballapp.databinding.ActivityEnglandBinding
import com.example.footballapp.databinding.ActivityGermanyBinding
import com.example.footballapp.network.RetroInstance
import com.example.footballapp.repository.FootballRepository

class GermanyActivity : AppCompatActivity() {

    private val binding by lazy { ActivityGermanyBinding.inflate(layoutInflater) }
    private val clubAdapter by lazy { GermanyAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setUpRv()
        setUpViewModel()

    }

    private fun setUpViewModel() {
        val repository = FootballRepository(RetroInstance.retroInstance())
        val viewModel =
            ViewModelProvider(this, GermanyViewModelFactory(repository))[GermanyViewModel::class.java]
        viewModel.getClubById("175")
        viewModel.state.observe(this) {
            when (it) {
                GermanyState.Loading -> {
                    binding.progress.isVisible = true
                }
                is GermanyState.Error -> {
                    Toast.makeText(this, it.error, Toast.LENGTH_SHORT).show()
                }
                is GermanyState.Success -> {
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
            layoutManager = LinearLayoutManager(this@GermanyActivity)
        }
    }

}