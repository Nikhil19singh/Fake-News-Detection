package com.example.fakenewsdetection

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.BounceInterpolator
import android.view.animation.OvershootInterpolator
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.example.fakenewsdetection.data.repository.UserRepository

class SplashActivity : AppCompatActivity() {

    private lateinit var userRepository: UserRepository
    private lateinit var ivLogo: ImageView
    private lateinit var tvAppName: TextView
    private lateinit var tvTagline: TextView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        userRepository = UserRepository(this)

        // Initialize views
        ivLogo = findViewById(R.id.ivLogo)
        tvAppName = findViewById(R.id.tvAppName)
        tvTagline = findViewById(R.id.tvTagline)
        progressBar = findViewById(R.id.progressBar)

        // Start animations
        startAnimations()

        // Navigate after delay
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = if (userRepository.getCurrentUser() != null) {
                Intent(this, MainActivity::class.java)
            } else {
                Intent(this, LoginActivity::class.java)
            }
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }, 4000)
    }

    private fun startAnimations() {
        // Logo animation - Scale and rotation with bounce
        val scaleXLogo = ObjectAnimator.ofFloat(ivLogo, View.SCALE_X, 0f, 1f).apply {
            duration = 1000
            interpolator = BounceInterpolator()
        }
        val scaleYLogo = ObjectAnimator.ofFloat(ivLogo, View.SCALE_Y, 0f, 1f).apply {
            duration = 1000
            interpolator = BounceInterpolator()
        }
        val rotationLogo = ObjectAnimator.ofFloat(ivLogo, View.ROTATION, 0f, 360f).apply {
            duration = 1000
            interpolator = AccelerateDecelerateInterpolator()
        }
        val alphaLogo = ObjectAnimator.ofFloat(ivLogo, View.ALPHA, 0f, 1f).apply {
            duration = 800
        }

        // App name animation - Slide from left with overshoot
        val translationXAppName = ObjectAnimator.ofFloat(tvAppName, View.TRANSLATION_X, -500f, 0f).apply {
            duration = 1200
            startDelay = 500
            interpolator = OvershootInterpolator()
        }
        val alphaAppName = ObjectAnimator.ofFloat(tvAppName, View.ALPHA, 0f, 1f).apply {
            duration = 800
            startDelay = 500
        }

        // Tagline animation - Slide from right
        val translationXTagline = ObjectAnimator.ofFloat(tvTagline, View.TRANSLATION_X, 500f, 0f).apply {
            duration = 1200
            startDelay = 800
            interpolator = OvershootInterpolator()
        }
        val alphaTagline = ObjectAnimator.ofFloat(tvTagline, View.ALPHA, 0f, 1f).apply {
            duration = 800
            startDelay = 800
        }

        // Progress bar animation - Fade in from bottom
        val translationYProgress = ObjectAnimator.ofFloat(progressBar, View.TRANSLATION_Y, 200f, 0f).apply {
            duration = 1000
            startDelay = 1200
            interpolator = AccelerateDecelerateInterpolator()
        }
        val alphaProgress = ObjectAnimator.ofFloat(progressBar, View.ALPHA, 0f, 1f).apply {
            duration = 800
            startDelay = 1200
        }

        // Continuous pulse animation for logo after initial animation
        Handler(Looper.getMainLooper()).postDelayed({
            startPulseAnimation()
        }, 1000)

        // Start all animations
        AnimatorSet().apply {
            playTogether(
                scaleXLogo, scaleYLogo, rotationLogo, alphaLogo,
                translationXAppName, alphaAppName,
                translationXTagline, alphaTagline,
                translationYProgress, alphaProgress
            )
            start()
        }
    }

    private fun startPulseAnimation() {
        val scaleUpX = ObjectAnimator.ofFloat(ivLogo, View.SCALE_X, 1f, 1.1f).apply {
            duration = 800
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
            interpolator = AccelerateDecelerateInterpolator()
        }
        val scaleUpY = ObjectAnimator.ofFloat(ivLogo, View.SCALE_Y, 1f, 1.1f).apply {
            duration = 800
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
            interpolator = AccelerateDecelerateInterpolator()
        }

        AnimatorSet().apply {
            playTogether(scaleUpX, scaleUpY)
            start()
        }
    }
}