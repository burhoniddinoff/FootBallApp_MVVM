package com.example.footballapp.activity.test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.footballapp.activity.england.EnglandActivity
import com.example.footballapp.activity.espain.LeagueActivity
import com.example.footballapp.activity.france.FranceActivity
import com.example.footballapp.activity.germany.GermanyActivity
import com.example.footballapp.activity.italy.ItalyActivity
import com.example.footballapp.databinding.ActivityTestBinding

class TestActivity : AppCompatActivity() {
    private val binding by lazy { ActivityTestBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initView()

    }

    private fun initView() {
        binding.apply {
            espain.setOnClickListener {
                startActivity(Intent(this@TestActivity, LeagueActivity::class.java))
            }
            england.setOnClickListener {
                startActivity(Intent(this@TestActivity, EnglandActivity::class.java))
            }
            germany.setOnClickListener {
                startActivity(Intent(this@TestActivity, GermanyActivity::class.java))
            }
            france.setOnClickListener {
                startActivity(Intent(this@TestActivity, FranceActivity::class.java))
            }
            italia.setOnClickListener {
                startActivity(Intent(this@TestActivity, ItalyActivity::class.java))
            }
        }
    }
}