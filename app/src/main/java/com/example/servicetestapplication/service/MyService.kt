package com.example.servicetestapplication.service

import android.app.*
import android.content.Intent
import android.os.IBinder
import android.os.SystemClock
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.servicetestapplication.MainActivity
import com.example.servicetestapplication.R

class MyService : Service() {
    private lateinit var pendingIntent: PendingIntent
    private lateinit var notificationChannel: NotificationChannel
    private lateinit var notification: Notification


    companion object {
        const val ACTION_START = "com.example.servicetestapplication.START"
        const val ACTION_RUN = "com.example.servicetestapplication.RUN"
        const val ACTION_STOP = "com.example.servicetestapplication.STOP"
        const val CHANNEL_ID = "Foreground Channel1"
        const val CHANGE_BADGE = "CHANGE_BADGE"
    }

    override fun onCreate() {
        Log.d("KHJ", "MyService onCreate()")
        pendingIntent = Intent(this, MainActivity::class.java).let { notificationIntent ->
            PendingIntent.getActivity(this, 0, notificationIntent, 0)
        }
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val action = intent?.action
        Log.d("KHJ", "onStartCommand action: $action")
        when (action) {
            ACTION_START -> {
                notificationChannel = createNotificationChannel()
                notification = createNotification()
                startForeground(1, notification)
                val thread = ServiceThread1()
                thread.start()
            }
            CHANGE_BADGE -> {
                notification = createNotification("하잉")
//                startForeground(1, notification)
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }
    class ServiceThread1 : Thread() {
        override fun run() {
            while(true){
                SystemClock.sleep(500)
                Log.d("KHJ","in thread")
            }
        }
    }

    private fun createNotificationChannel(): NotificationChannel {
        val serviceChannel = NotificationChannel(
            CHANNEL_ID,
            "EgoEco App",
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            description = "연결댓어욤"
//            setShowBadge(true)
        }
        val manager = getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(serviceChannel)
        return serviceChannel
    }

    private fun createNotification(contentText: String = "연결됐어요옹"): Notification {
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("EgoEco Foreground Service")
            .setContentText(contentText)
            .setContentIntent(pendingIntent)
            .setTicker("hello")
            .setSmallIcon(R.mipmap.ic_launcher)
            .build()
    }

    override fun onDestroy() {
        Log.d("KHJ", "MyService onDestroy()")
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onTaskRemoved(rootIntent: Intent?) { // 앱 프로세스 종료시 서비스도 종료.
        Log.d("KHJ", "onTaskRemoved")
        stopForeground(true)
        stopSelf()
        super.onTaskRemoved(rootIntent)
    }
}