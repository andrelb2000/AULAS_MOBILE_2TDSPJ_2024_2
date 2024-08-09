package com.example.aula_02_turmaj_ex2

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    var xModelo: Int = 0
    var yModelo: Int = 0

    var eq1: Int = 0




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        ////// Aqui nosso codigo ///
        val xModView : TextView = findViewById(R.id.ID1_XMOD_textView)
        val eq1View:  TextView = findViewById(R.id.ID1_EQ1_textView2)

        val buttonUP : Button = findViewById(R.id.ID1_XUP_button)

        buttonUP.setOnClickListener {
            xModelo = xModelo + 1
            xModView.text = "X = ${xModelo}"

            eq1 = 2* xModelo + 3 * yModelo

            eq1View.text = "2X + 3Y = ${eq1}"

        }
    }
}