package com.ahmetavci.handlerrunnableprojesi

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    var numara = 0
    var runnable: Runnable = Runnable { }
    var handler = Handler(Looper.myLooper()!!)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun baslat(view: View) {
        var textim = findViewById<TextView>(R.id.textView)

        numara = 0
        /*
        while (numara < 100){
            numara = numara + 1
            textim.text = "Sayaç: ${numara}"
            Thread.sleep(500) //her işlemin sonunda yarım saniye bekle!
        }//bu loop script i programı çökertiyor sağlıklı bir yol değil
        */

        runnable = object : Runnable {
            override fun run() {
                numara = numara + 1
                textim.text = "Sayaç: ${numara}"
                handler.postDelayed(runnable, 1000)
            }

        }
        handler.post(runnable)
    }

    fun durdur(view: View){
        var textim = findViewById<TextView>(R.id.textView)

        handler.removeCallbacks(runnable)
        numara = 0

        textim.text = "Sayaç: 0"

    }
}
