package com.example.nezumi.kakenapp3

import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import android.content.Intent
import android.renderscript.Sampler
import android.widget.EditText
import com.google.firebase.database.*
import android.support.v4.app.NotificationManagerCompat
import android.text.SpannableStringBuilder
import android.R.id.edit




class MainActivity : AppCompatActivity() {
    var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val text1 = findViewById<TextView>(R.id.tk1)
        val btn1 = findViewById<Button>(R.id.btn1)
        val btn2 = findViewById<Button>(R.id.btn2)
        val send = findViewById<Button>(R.id.btn0)
        val btn3 = findViewById<Button>(R.id.btn3)
        val btn4 = findViewById<Button>(R.id.btn4)
        val sw1 = findViewById<Switch>(R.id.sw1)
        val sw2 = findViewById<Switch>(R.id.sw2)
        val sw3 = findViewById<Switch>(R.id.sw3)
        val sw4 = findViewById<Switch>(R.id.sw4)
        val etxt1 = findViewById<EditText>(R.id.etxt1)
        val etxt2 = findViewById<EditText>(R.id.etxt2)
        val etxt3 = findViewById<EditText>(R.id.etxt3)
        val etxt4 = findViewById<EditText>(R.id.etxt4)
        val kakenapp2 = FirebaseDatabase.getInstance()
        val ko1 = kakenapp2.getReference("ko1")
        val ko2 = kakenapp2.getReference("ko2")
        val ko3 = kakenapp2.getReference("ko3")
        val ko4 = kakenapp2.getReference("ko4")
        val kt1 = kakenapp2.getReference("kt1")
        val kt2 = kakenapp2.getReference("kt2")
        val kt3 = kakenapp2.getReference("kt3")
        val kt4 = kakenapp2.getReference("kt4")
        val swlist = listOf<Switch>(sw1,sw2,sw3,sw4)
        val kolist = listOf(ko1,ko2,ko3,ko4)
        var ab1 = 0
        var ab2 = 0
        var ab3 = 0
        var ab4 = 0
        kt1.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                if(ab1>0) {
                    var tuti1 = Notification.Builder(getApplicationContext())
                            .setSmallIcon(R.drawable.notification_icon_background)
                            .setContentTitle("忘れ物です！")
                            .setContentText("${etxt1.text}を忘れています！")
                            .setDefaults(Notification.DEFAULT_VIBRATE)
                            .build()
                    val manager = NotificationManagerCompat.from(applicationContext)
                    manager.notify(1, tuti1)
                    kt1.setValue(0)
                }
                ab1++
            }
            override fun onCancelled(p0: DatabaseError?) {
                text1.text = "?yattazeeeeee"
            }
        })

        kt2.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                if(ab2>0) {
                    var tuti2 = Notification.Builder(getApplicationContext())
                            .setSmallIcon(R.drawable.notification_icon_background)
                            .setContentTitle("忘れ物です！")
                            .setContentText("${etxt2.text}を忘れています！")
                            .setDefaults(Notification.DEFAULT_VIBRATE)
                            .build()
                    val manager = NotificationManagerCompat.from(applicationContext)
                    manager.notify(2, tuti2)
                    kt2.setValue(0)
                }
                ab2++
            }
            override fun onCancelled(p0: DatabaseError?) {
                text1.text = "?yattazeeeeee"
            }
        })

        kt3.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                if(ab3>0) {
                    var tuti3 = Notification.Builder(getApplicationContext())
                            .setSmallIcon(R.drawable.notification_icon_background)
                            .setContentTitle("忘れ物です！")
                            .setContentText("${etxt3.text}を忘れています！")
                            .setDefaults(Notification.DEFAULT_VIBRATE)
                            .build()
                    val manager = NotificationManagerCompat.from(applicationContext)
                    manager.notify(3, tuti3)
                    kt3.setValue(0)
                }
                ab3++
            }
            override fun onCancelled(p0: DatabaseError?) {
                text1.text = "?yattazeeeeee"
            }
        })

        kt4.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                if(ab4>0) {
                    var tuti4 = Notification.Builder(getApplicationContext())
                            .setSmallIcon(R.drawable.notification_icon_background)
                            .setContentTitle("忘れ物です！")
                            .setContentText("${etxt4.text}を忘れています！")
                            .setDefaults(Notification.DEFAULT_VIBRATE)
                            .build()
                    val manager = NotificationManagerCompat.from(applicationContext)
                    manager.notify(4, tuti4)
                    kt4.setValue(0)
                }
                ab4++
            }
            override fun onCancelled(p0: DatabaseError?) {
                text1.text = "?yattazeeeeee"
            }
        })

        btn1.setOnClickListener{ v ->
            count++
            text1.text = "${count}"
        }
        btn2.setOnClickListener{v ->
            count--
            text1.text ="${count}"
        }
        btn3.setOnClickListener{v ->
            count = count + 10
            text1.text ="${count}"
        }

        send.setOnClickListener{ v -> //予約のプログラム
            for( a in 0..swlist.size -1) {
                kolist[a].setValue(0)
                if (swlist[a].isChecked) {
                    kolist[a].setValue(1)
                }
            }
        }
    }
}