package com.bignerdranch.android.appconverterjpgtopng

import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bignerdranch.android.appconverterjpgtopng.databinding.ActivityMainBinding
import com.bignerdranch.android.appconverterjpgtopng.databinding.ActivityMainBinding.bind

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val binding by viewBinding(ActivityMainBinding::bind)
}