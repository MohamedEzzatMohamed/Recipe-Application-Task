package com.example.recipe_android_app.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.recipe_android_app.R
import kotlinx.coroutines.*

class SplashActivity : AppCompatActivity(){

    private val activityScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slash)

        //use coroutine to handel a splash in Main(UI) Thread for splash time
        activityScope.launch {
            delay(3000)
            var intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    override fun onPause() {
        activityScope.cancel()
        super.onPause()
    }
}