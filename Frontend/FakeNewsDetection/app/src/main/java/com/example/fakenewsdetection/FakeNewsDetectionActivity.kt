package com.example.fakenewsdetection

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.fakenewsdetection.databinding.ActivityFakeNewsDetectionBinding
import com.example.fakenewsdetection.data.repository.NewsRepository
import kotlinx.coroutines.launch

class FakeNewsDetectionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFakeNewsDetectionBinding
    private lateinit var repository: NewsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFakeNewsDetectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        repository = NewsRepository()

        binding.btnAnalyze.setOnClickListener {
            analyzeNewsWithML()
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun analyzeNewsWithML() {

        val newsText = binding.etNewsText.text.toString().trim()

        if (newsText.isEmpty()) {
            binding.tvResult.text = "Please enter news text"
            return
        }

        binding.progressBar.visibility = View.VISIBLE
        binding.resultCard.visibility = View.GONE

        lifecycleScope.launch {

            try {
                val response = repository.predictNews(newsText)

                binding.progressBar.visibility = View.GONE
                binding.resultCard.visibility = View.VISIBLE

                if (response.isSuccessful && response.body() != null) {

                    val result = response.body()!!

                    binding.tvResult.text = result.prediction
                    binding.tvConfidence.text =
                        "Confidence: ${(result.confidence * 100).toInt()}%"

                    // ✅ Color based on prediction
                    when {
                        result.prediction.contains("REAL") -> {
                            binding.resultCard.setCardBackgroundColor(
                                getColor(android.R.color.holo_green_light)
                            )
                        }
                        result.prediction.contains("FAKE") -> {
                            binding.resultCard.setCardBackgroundColor(
                                getColor(android.R.color.holo_red_light)
                            )
                        }
                        else -> {
                            binding.resultCard.setCardBackgroundColor(
                                getColor(android.R.color.holo_orange_light)
                            )
                        }
                    }

                } else {
                    binding.tvResult.text = "Prediction failed"
                }

            } catch (e: Exception) {
                binding.progressBar.visibility = View.GONE
                binding.tvResult.text = "Error: ${e.message}"
            }
        }
    }
}
