package com.example.palletrelease.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pallet_release_lib.actitivties.FirstComposeActivityPalletRelease
import com.example.palletrelease.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

        private lateinit var binding : ActivityMainBinding


        override fun onCreate(savedInstanceState : Bundle?) {
                super.onCreate(savedInstanceState)

                binding = ActivityMainBinding.inflate(layoutInflater)
                setContentView(binding.root)

                val baseURL = "https://swms.mobillor.net/api/api/api/swms/"


                binding.cardView.setOnClickListener {

                        val intent = Intent(this@MainActivity, FirstComposeActivityPalletRelease::class.java)
                        intent.putExtra("baseURL",baseURL)
                        intent.putExtra("primaryColor", 0xFFEC2D01)
                        intent.putExtra("secondaryColor", 0xFFA6A6A6)
                        intent.putExtra("tertiaryColor", 0xFF0F1731)
                        startActivity(intent)

                }
        }
}