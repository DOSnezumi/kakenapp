package com.example.nezumi.kakenapp3

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase





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
        val kakenapp2 = FirebaseDatabase.getInstance()
        val ko1 = kakenapp2.getReference("ko1")
        val ko2 = kakenapp2.getReference("ko2")
        val ko3 = kakenapp2.getReference("ko3")
        val ko4 = kakenapp2.getReference("ko4")
        val swlist = listOf<Switch>(sw1,sw2,sw3,sw4)
        val kolist = listOf(ko1,ko2,ko3,ko4)
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
            Log.d("aaaa",swlist.size.toString())
            Log.d("aaaa","loopstart")
            for( a in 0..swlist.size -1) {
                Log.d("aaaa","fo${a}rrrr")
                if(a>=4){
                    Log.d("aaaa","errer")
                    continue

                }
                kolist[a].setValue(0)
                if (swlist[a].isChecked) {
                    kolist[a].setValue(1)
                }
            }
        }
    }
}