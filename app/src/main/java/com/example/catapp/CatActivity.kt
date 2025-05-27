package com.example.catapp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.catapp.adapter.CatAdapter
import com.example.catapp.databinding.ActivityMainBinding
import com.example.catapp.repository.CatRepository
import com.example.catapp.viewmodel.CatViewModel
import com.example.catapp.viewmodel.CatViewModelFactory

class CatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: CatViewModel by viewModels {
        CatViewModelFactory(CatRepository(application))
    }
    private lateinit var adapter: CatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupObservers()
    }

    private fun setupUI() {
        adapter = CatAdapter(emptyList())
        binding.rvCats.apply {
            layoutManager = LinearLayoutManager(this@CatActivity)
            adapter = this@CatActivity.adapter
        }

        binding.btnFetchCat.setOnClickListener {
            viewModel.fetchNewCat()
        }
    }

    private fun setupObservers() {
        viewModel.allCats.observe(this) { cats ->
            cats?.let { adapter.updateList(it) }
        }
    }
}