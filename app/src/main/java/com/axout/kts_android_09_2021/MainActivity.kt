package com.axout.kts_android_09_2021

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.axout.kts_android_09_2021.networking.NetworkLiveData

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private var toast: Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        NetworkLiveData.observe(this, Observer {
            toast?.cancel()
            toast = if (it) {
                Toast.makeText(this,"internet connected", Toast.LENGTH_SHORT)
            } else {
                Toast.makeText(this,"no internet connection", Toast.LENGTH_SHORT)
            }
            toast?.show()
        })
    }
}