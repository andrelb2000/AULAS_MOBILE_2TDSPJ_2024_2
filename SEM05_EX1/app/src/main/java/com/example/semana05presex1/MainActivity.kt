package com.example.semana05presex1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private var preco:EditText? = null
    private var insere:Button?= null
    private var limpa:Button?=null
    private var soma:TextView?=null
    private var maior:TextView?=null
    private var menor:TextView?=null

    private var listaView:ListView?=null

    private var somaPrecos:Float = 0F
    private var menorPreco:Float = 10000000F
    private var maiorPreco:Float = 0F



    private fun inicializarComponentes(){
        preco = findViewById(R.id.ID1_PRECOeditTextNumberDecimal)
        soma  = findViewById(R.id.ID1_SOMAtextView)
        maior = findViewById(R.id.ID1_MAIORtextView2)
        menor = findViewById(R.id.ID1_MENORtextView3)
    }
    private fun inicializaAcoes(){
        insere = findViewById(R.id.ID1_INSEREbutton)
        limpa  = findViewById(R.id.ID1_LIMPAbutton2)
        insere?.setOnClickListener {
            var tempPreco:Float = preco?.text.toString().toFloat()
            somaPrecos = somaPrecos + tempPreco
            soma?.text = "R$ ${somaPrecos}"
            if(tempPreco > maiorPreco){
                maiorPreco = tempPreco
                maior?.text = "R$ ${maiorPreco}"
            }
            if(tempPreco < menorPreco){
                menorPreco = tempPreco
                menor?.text = "R$ ${menorPreco}"
            }
        }
        limpa?.setOnClickListener {


        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        inicializarComponentes()
        inicializaAcoes()
    }
}