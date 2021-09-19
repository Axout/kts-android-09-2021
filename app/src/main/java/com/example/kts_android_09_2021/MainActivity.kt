package com.example.kts_android_09_2021

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button).setOnClickListener {
            val view = findViewById<ConstraintLayout>(R.id.layout_id)
            view.setBackgroundColor(Color.argb(255, Random.nextInt(256), Random.nextInt(256), Random.nextInt(256)))
        }
    }
}