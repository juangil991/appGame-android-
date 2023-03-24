package com.gdevelope.gameapp.game.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gdevelope.gameapp.R
import com.gdevelope.gameapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //view Binding en una Activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}