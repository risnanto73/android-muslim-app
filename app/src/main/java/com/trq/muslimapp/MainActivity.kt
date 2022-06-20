package com.trq.muslimapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.trq.muslimapp.databinding.ActivityMainBinding
import com.trq.muslimapp.helpers.SharedPreference

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = SharedPreference(this)
        val user = sharedPreferences.getUser()
        if (user == null) {
            Glide.with(this).load(R.drawable.user)
                .into(binding.navView.getHeaderView(0).findViewById<ImageView>(R.id.imgUser))
            binding.navView.getHeaderView(0).findViewById<TextView>(R.id.tv_email).text =
                "Guest@gmail.com"
            binding.navView.getHeaderView(0).findViewById<TextView>(R.id.tv_name).text = "Guest"
        } else {

            val urlImage = "http://muslim.app.tiorisnanto.com/storage/${user.gambar}"
            if (user.gambar != null) {
                Glide.with(this).load(urlImage)
                    .into(binding.navView.getHeaderView(0).findViewById<ImageView>(R.id.imgUser))
            } else if (user.gambar == null) {
                Glide.with(this).load("https://ui-avatars.com/api/?name=${user.name}")
                    .into(binding.navView.getHeaderView(0).findViewById<ImageView>(R.id.imgUser))
            } else {
                Glide.with(this).load(R.drawable.user)
                    .into(binding.navView.getHeaderView(0).findViewById<ImageView>(R.id.imgUser))
            }
            binding.navView.getHeaderView(0).findViewById<TextView>(R.id.tv_email).text = user.email
            binding.navView.getHeaderView(0).findViewById<TextView>(R.id.tv_name).text = user.name
        }

        setSupportActionBar(binding.appBarMain2.toolbar2)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_profile, R.id.nav_notes
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.action_settings -> {
                AlertDialog.Builder(this)
                    .setTitle("App Version V1.0.0")
                    .setMessage("Muslim App By Muhammad Rifqi Syatria & Tio Risnanto")
                    .setPositiveButton("OK") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

}