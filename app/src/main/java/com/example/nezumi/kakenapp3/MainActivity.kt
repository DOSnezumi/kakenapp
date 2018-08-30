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
        val kakenapp2 = FirebaseDatabase.getInstance()
        val kaken = kakenapp2.getReference("kakenapp2")
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
        val tuti = Notification.Builder(getApplicationContext())
                .setSmallIcon(R.drawable.notification_icon_background)
                .setContentTitle("忘れ物です！")
                .setContentText("子機１を忘れています！")
                .build()


        /*kaken.addChildEventListener(object : ChildEventListener {
            override fun onCancelled(p0: DatabaseError?) {
                text1.text = "yattazeeeeee"
            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {
            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {
                text1.text = "yattazeeeeee"
            }

            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                text1.text = "yattazeeeeee"
            }

            override fun onChildRemoved(p0: DataSnapshot) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })*/
        kt1.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                val st = p0.getValue()
                text1.text = "null"
                if(st==1){
                    text1.text ="1"
                    val manager = NotificationManagerCompat.from(applicationContext)
                    manager.notify(123, tuti)
                    kt1.setValue(0)
                }
                text1.text ="${st}"

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