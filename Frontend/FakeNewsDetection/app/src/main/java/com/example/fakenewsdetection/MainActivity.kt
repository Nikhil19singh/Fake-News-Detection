package com.example.fakenewsdetection // Package changed to match your base path structure

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
// Corrected imports to use the com.example.fakenewsdetection package path
import com.example.fakenewsdetection.databinding.ActivityMainBinding
import com.example.fakenewsdetection.data.repository.NewsRepository
import com.example.fakenewsdetection.data.repository.UserRepository
import com.example.fakenewsdetection.ui.adapters.NewsAdapter
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var newsRepository: NewsRepository
    private lateinit var userRepository: UserRepository
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // The NewsRepository constructor might require the NewsApiService,
        // depending on how you structured it (e.g., NewsRepository(RetrofitClient.apiService))
        // Assuming for now it works without arguments.
        newsRepository = NewsRepository()
        userRepository = UserRepository(this)

        setupRecyclerView()
        setupListeners()
        loadNews()
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()
        binding.rvNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun setupListeners() {
        binding.btnDetectFake.setOnClickListener {
            // Ensure FakeNewsDetectionActivity is in the same package or imported correctly
            startActivity(Intent(this, FakeNewsDetectionActivity::class.java))
        }

        binding.btnLogout.setOnClickListener {
            userRepository.logout()
            // Ensure LoginActivity is in the same package or imported correctly
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        binding.btnRefresh.setOnClickListener {
            loadNews()
        }
    }

    private fun loadNews() {
        binding.progressBar.visibility = View.VISIBLE

        lifecycleScope.launch {
            try {
                // IMPORTANT: This call relies on the Retrofit/API setup in NewsRepository
                val response = newsRepository.getTopHeadlines()

                if (response.isSuccessful && response.body() != null) {
                    newsAdapter.submitList(response.body()!!.articles)
                } else {
                    Toast.makeText(this@MainActivity, "Failed to load news", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                // Catching exceptions like Network errors (IOException)
                Toast.makeText(this@MainActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            } finally {
                binding.progressBar.visibility = View.GONE
            }
        }
    }
}