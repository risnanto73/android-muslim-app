package com.trq.muslimapp.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.trq.muslimapp.MainActivity
import com.trq.muslimapp.R


class Splashscreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        val handler = Handler()
        handler.postDelayed(Runnable {
            val intent = Intent(this@Splashscreen, MainActivity::class.java)
            startActivity(intent)

            finish()
        }, 3000)

    }
}