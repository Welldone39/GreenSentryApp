package com.wildan.greensentry.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import androidx.appcompat.app.AppCompatActivity
import com.wildan.greensentry.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private val SPLASH_DISPLAY_LENGTH: Long = 3000 // Durasi splash screen dalam milidetik
    private lateinit var binding: ActivitySplashBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Buat animasi fade-in
        val fadeIn: Animation = AlphaAnimation(0f, 1f).apply {
            duration = 1000 // Durasi animasi dalam milidetik
            startOffset = 500 // Delay sebelum animasi dimulai
        }

        // Set animasi ke TextView dan ImageView
        binding.splashScreenText.startAnimation(fadeIn)
        binding.splashScreenTextBottom.startAnimation(fadeIn)
        binding.logo.startAnimation(fadeIn)

        // Tampilkan elemen-elemen dengan animasi
        binding.splashScreenText.visibility = View.VISIBLE
        binding.splashScreenTextBottom.visibility = View.VISIBLE
        binding.logo.visibility = View.VISIBLE



        // Handler untuk berpindah ke MainActivity setelah durasi splash screen
        Handler(Looper.getMainLooper()).postDelayed({
            val mainIntent = Intent(this@SplashActivity, LoginActivity::class.java)
            startActivity(mainIntent)
            finish()
        }, SPLASH_DISPLAY_LENGTH)
    }
}
