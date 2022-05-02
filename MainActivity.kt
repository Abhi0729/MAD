package com.example.b_13_ise1

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat


class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var count=-1
        var b=findViewById<Button>(R.id.button);
        var tv=findViewById<TextView>(R.id.textView2)
        var b1=findViewById<Button>(R.id.button3)
        var orientation = this.resources.configuration.orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            tv.text= "Landscape mode"
            count+=1
            tv.append("No o rotations are ${count}")

        } else {
            tv.text= "Portrait mode"
            count+=1
            tv.append("\nNo o rotations are ${count}")
        }
        val currentOrientation=this.resources.configuration.orientation;
        b.setOnClickListener(View.OnClickListener {

            createNotificationChannelAndNotification()
            if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE;
                tv.text= "Orientation locked at Landscape mode"
            }
            else {
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED;
                tv.text= "Orientation locked at Portrait mode"
            }
        })
        b1.setOnClickListener(View.OnClickListener {
            window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_FULL_USER;
        })


    }

    private fun createNotificationChannelAndNotification() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            val name=getString(R.string.Channel_Name)
            val discriptionText="Channel 1 Notification"
            val importance=NotificationManager.IMPORTANCE_DEFAULT
            val channel=NotificationChannel("1",name,importance).apply {
                description=discriptionText
            }
            val notificationManager:NotificationManager=
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
        // Built a notifcation object
        val builder = NotificationCompat.Builder(this, "1")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("My notification")
            .setContentText("Screen Locked!")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            // Set the intent that will fire when the user taps the notification
            //.setContentIntent(pendingIntent)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(this)) {
            // notificationId is a unique int for each notification that you must define
            notify(1, builder.build())
        }

//        var nmc= NotificationManagerCompat.from(this)
//        nmc.notify(2,builder.build())
    }
}




//reference=https://riptutorial.com/android/example/21077/lock-screen-s-rotation-programmatically
