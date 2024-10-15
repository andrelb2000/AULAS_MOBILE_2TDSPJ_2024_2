package com.example.aula_14_ex1_2tdspj

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class EditarActivity : AppCompatActivity() {
    private var confirmaButton: Button? = null
    private var removeButton: Button? = null
    private var cancelaButton: Button? = null
    private var listaChegada:ArrayList<Pessoa>? = null
    private var indice:Int? = -1

    fun inicializar(){
        confirmaButton = findViewById(R.id.ID3_EDITAbutton)
        removeButton  = findViewById(R.id.ID3_REMOVEbutton2)
        cancelaButton  = findViewById(R.id.ID3_CANCELAbutton3)
    }
    fun inicializaAcoes(){
        confirmaButton?.setOnClickListener {
            ////////// Remover o indice da lista //////////
            var meuIndice: Int? = indice?.toInt()
            if (meuIndice != null) {
                listaChegada?.get(meuIndice!!)?.setNomePessoa("MARIA")
            }
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
        removeButton?.setOnClickListener {
            ////////// Remover o indice da lista //////////
            var meuIndice: Int? = indice?.toInt()
            if (meuIndice != null) {
                listaChegada?.removeAt(meuIndice)
            }
            ///////////////////////////////////////////////
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
        setContentView(R.layout.activity_editar)
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
        indice = pacoteChegada?.getInt("INDICE")?:-1
    }
}