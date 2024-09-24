package com.example.nearbylocator.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.nearbylocator.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    private val splashScreenTimeout: Long = 3000 // 3 seconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout using ViewBinding
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up a delay for the splash screen
        Handler(Looper.getMainLooper()).postDelayed({
            // After the delay, start the MainActivity
            startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
            finish() // Close SplashScreenActivity
        }, splashScreenTimeout)
    }
}
