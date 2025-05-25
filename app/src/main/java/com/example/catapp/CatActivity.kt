package com.example.catapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.catapp.databinding.ActivityMainBinding
import com.example.catapp.repository.CatRepository
import com.example.catapp.viewmodel.CatViewModel
import com.example.catapp.viewmodel.CatViewModelFactory
import com.example.catapp.adapter.CatAdapter

class CatActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: CatViewModel by viewModels {
        CatViewModelFactory(CatRepository(applicationContext))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupButton()
        observeData()
    }

    private fun setupRecyclerView() {
        binding.rvCats.layoutManager = LinearLayoutManager(this)
        binding.rvCats.adapter = CatAdapter(emptyList())
    }

    private fun setupButton() {
        binding.btnFetchCat.setOnClickListener {
            viewModel.fetchNewCat()
        }
    }

    private fun observeData() {
        viewModel.allCats.observe(this) { cats ->
            (binding.rvCats.adapter as? CatAdapter)?.updateList(cats)
        }
    }
}