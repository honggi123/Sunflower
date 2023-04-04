package com.example.sunflower

import android.os.Bundle
import androidx.databinding.DataBindingUtil.setContentView
import androidx.appcompat.app.AppCompatActivity
import com.example.sunflower.R
import com.example.sunflower.databinding.ActivityGardenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GardenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView<ActivityGardenBinding>(this, R.layout.activity_garden)
    }

}