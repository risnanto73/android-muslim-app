package com.trq.muslimapp.ui.home.tasbih

import android.content.Context
import android.media.MediaPlayer
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.trq.muslimapp.R
import com.trq.muslimapp.databinding.ActivityTasbihBinding

class TasbihActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTasbihBinding

    var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTasbihBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Tasbih"
        // setContentView(R.layout.activity_tasbih)

        binding.btnSubhanallah.setOnClickListener {
            counter++
            binding.dzikir.text = counter.toString()

            val mediaPlayer = MediaPlayer.create(this, R.raw.tasbih)
            mediaPlayer.start()
            if(counter == 33){
                Toast.makeText(this, "Subhanallah", Toast.LENGTH_SHORT).show()
                counter = 0

                val vibrator = this.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                if (Build.VERSION.SDK_INT >= 26) {
                    vibrator.vibrate(VibrationEffect.createOneShot(3000, VibrationEffect.DEFAULT_AMPLITUDE))
                } else {
                    vibrator.vibrate(93000)
                }
            }

        }

        binding.btnAlhamdulillah.setOnClickListener {
            val mediaPlayer = MediaPlayer.create(this, R.raw.tahmid)
            mediaPlayer.start()
            counter++
            binding.dzikir.text = counter.toString()
            if (counter == 33){
                Toast.makeText(this, "Alhamdulillah", Toast.LENGTH_SHORT).show()
                counter = 0
                val vibrator = this.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                if (Build.VERSION.SDK_INT >= 26) {
                    vibrator.vibrate(VibrationEffect.createOneShot(3000, VibrationEffect.DEFAULT_AMPLITUDE))
                } else {
                    vibrator.vibrate(93000)
                }
            }
        }

        binding.btnLailahaillallah.setOnClickListener {
            counter++
            binding.dzikir.text = counter.toString()
            val mediaPlayer = MediaPlayer.create(this, R.raw.tahlil)
            mediaPlayer.start()
            if (counter == 33) {
                Toast.makeText(this, "Lailaha Illallah", Toast.LENGTH_SHORT).show()
                counter = 0
                val vibrator = this.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                if (Build.VERSION.SDK_INT >= 26) {
                    vibrator.vibrate(VibrationEffect.createOneShot(3000, VibrationEffect.DEFAULT_AMPLITUDE))
                } else {
                    vibrator.vibrate(93000)
                }
            }
        }

        binding.btnAllahuAkbar.setOnClickListener {
            counter++
            binding.dzikir.text = counter.toString()
            val mediaPlayer = MediaPlayer.create(this, R.raw.takbir)
            mediaPlayer.start()
            if (counter == 33) {
                Toast.makeText(this, "Allahu Akbar", Toast.LENGTH_SHORT).show()
                counter = 0
                val vibrator = this.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                if (Build.VERSION.SDK_INT >= 26) {
                    vibrator.vibrate(VibrationEffect.createOneShot(3000, VibrationEffect.DEFAULT_AMPLITUDE))
                } else {
                    vibrator.vibrate(93000)
                }
            }
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}