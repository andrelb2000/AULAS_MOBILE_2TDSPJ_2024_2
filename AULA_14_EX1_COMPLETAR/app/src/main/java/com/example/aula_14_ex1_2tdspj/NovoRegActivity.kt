package com.example.aula_14_ex1_2tdspj

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class NovoRegActivity : AppCompatActivity() {
    private var confirmaButton:Button? = null
    private var cancelaButton:Button?  = null
    private var listaChegada:ArrayList<Pessoa>? = null

    fun inicializar(){
        confirmaButton = findViewById(R.id.ID2_INSEREbutton)
        cancelaButton  = findViewById(R.id.ID2_CANCELAbutton2)
    }
    fun inicializaAcoes() {
        confirmaButton?.setOnClickListener {
            // Voltar para a Activity Principal
            val mainActivityIntent = Intent(this,MainActivity::class.java)
            /// Se já houver uma criada, reutilize
            mainActivityIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            //// Pegar os novos valores nos componentes de entrada (Ex, edit text, etc)
            // TODO pegar valores das entradas

            //// Criar um NOVO objeto da classe pessoa  ////
            //// Criar construtor para receber valores  ////
            var pessoa:Pessoa =  Pessoa()
            ///////////////////////////////////////////////

            //// Colocar na lista no mesmo lugar do objeto anteriror
            listaChegada?.add(pessoa)
            //// Enviar a lista de volta
            var pacoteEnvio: Bundle = Bundle()
            // Colocando a LISTA INTEIRA com o rotulo "LISTA"
            pacoteEnvio.putSerializable("LISTA2",listaChegada)
            mainActivityIntent.putExtras(pacoteEnvio)
            startActivity(mainActivityIntent)
        }
        cancelaButton?.setOnClickListener {
            // Voltar para a Activity Principal
            val mainActivityIntent = Intent(this,MainActivity::class.java)
            /// Se já houver uma criada, reutilize
            mainActivityIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP

            //// Enviar a lista de volta
            var pacoteEnvio: Bundle = Bundle()
            // Colocando a LISTA INTEIRA com o rotulo "LISTA"
            pacoteEnvio.putSerializable("LISTA2",listaChegada)
            mainActivityIntent.putExtras(pacoteEnvio)
            startActivity(mainActivityIntent)
        }

    }

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_novo_reg)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        inicializar()
        inicializaAcoes()
        var pacoteChegada = intent.extras
        // Obteve a lista enviada
        listaChegada = pacoteChegada?.getSerializable("LISTA1") as ArrayList<Pessoa>
        }
}