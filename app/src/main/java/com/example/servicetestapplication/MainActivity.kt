package com.example.servicetestapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.servicetestapplication.databinding.ActivityMainBinding
import com.example.servicetestapplication.service.MyService

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.apply {
            startServiceButton.setOnClickListener {
                startService()
            }
            stopServiceButton.setOnClickListener {
                stopService()
            }
            changeBadgeButton.setOnClickListener {
                changeBadge()
            }
        }
    }

    fun startService() {
        val intent = Intent(this, MyService::class.java)
        intent.action = MyService.ACTION_START
        Log.d("KHJ", "${startForegroundService(intent)}")
    }

    fun stopService() {
        val intent = Intent(this, MyService::class.java)
        intent.action = MyService.ACTION_STOP
        Log.d("KHJ", "${stopService(intent)}")
    }
    fun changeBadge() {
        val intent = Intent(this, MyService::class.java)
        intent.action = MyService.CHANGE_BADGE
        Log.d("KHJ", "${startForegroundService(intent)}")
    }
}