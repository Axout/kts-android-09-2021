package com.axout.kts_android_09_2021

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.axout.kts_android_09_2021.networking.NetworkLiveData

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        NetworkLiveData.observe(this, Observer {
            if (it) {
                //Toast.makeText(this,"internet connected", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this,R.string.not_connected, Toast.LENGTH_SHORT).show()
            }
        })
    }
}