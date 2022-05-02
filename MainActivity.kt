package com.example.b_13_ise1

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var b=findViewById<Button>(R.id.button);
        var tv=findViewById<TextView>(R.id.textView2)
        var b1=findViewById<Button>(R.id.button3)
        var orientation = this.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            tv.text= "Landscape mode"

        } else {
            tv.text= "Portrait mode"
        }
        val currentOrientation=this.getResources().getConfiguration().orientation;
        b.setOnClickListener(View.OnClickListener {
            if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
                tv.text= "Orientation locked at Landscape mode"
            }
            else {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
                tv.text= "Orientation locked at Portrait mode"
            }
        })
        b1.setOnClickListener(View.OnClickListener {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_USER);

                } else {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);

                }
        })




    }
}



//reference=https://riptutorial.com/android/example/21077/lock-screen-s-rotation-programmatically