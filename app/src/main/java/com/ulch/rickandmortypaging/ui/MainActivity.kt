package com.ulch.rickandmortypaging.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ulch.rickandmortypaging.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

    }
}

