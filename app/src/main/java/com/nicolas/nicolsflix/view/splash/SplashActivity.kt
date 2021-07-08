package com.nicolas.nicolsflix.view.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.nicolas.nicolsflix.NicolsFlixActivity
import com.nicolas.nicolsflix.R

class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT = 3000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed(
            {
                startActivity(Intent(this, NicolsFlixActivity::class.java))
                finish()
            },SPLASH_TIME_OUT)
    }
}