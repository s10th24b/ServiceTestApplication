package com.example.servicetestapplication.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyService : Service() {
    companion object {
        const val ACTION_START = "com.example.servicetestapplication.START"
        const val ACTION_RUN = "com.example.servicetestapplication.RUN"
        const val ACTION_STOP = "com.example.servicetestapplication.STOP"
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val action = intent?.action
        Log.d("KHJ", "onStartCommand action: $action")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }
}