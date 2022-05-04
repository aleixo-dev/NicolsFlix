package com.nicolas.nicolsflix.view.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.nicolas.nicolsflix.MainActivity
import com.nicolas.nicolsflix.R
import com.nicolas.nicolsflix.network.NetworkDiModule
import org.koin.core.context.loadKoinModules

class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT = 3000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        loadKoinModules(NetworkDiModule.instance)
        Handler().postDelayed(
            {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            },SPLASH_TIME_OUT)
    }
}