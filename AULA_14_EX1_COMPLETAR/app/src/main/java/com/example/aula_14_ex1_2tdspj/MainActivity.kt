package com.example.aula_14_ex1_2tdspj

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.Serializable

///////////////////////////////////////////////
class Telefone:Serializable{
    private var telefone:String = "21 99999999"
}
//////////////////////////////////////////////
class Pessoa:Serializable{
    private var nomePessoa:String = "Joao"
    private var telPessoa:Telefone = Telefone()
    override fun toString(): String {
        return "==> $nomePessoa"
    }
    public fun getNomePessoa():String{
        return nomePessoa
    }
    public fun setNomePessoa(nome:String){
        nomePessoa = nome
    }
}

class MainActivity : AppCompatActivity() {
    /// Botao para disparo de Activity para NOVO ITEM
    private var novoButton: Button? = null
    // Lista visual principal
    private var listaItens: ArrayList<Pessoa>? = null
    private var listaItensView: ListView? = null
    private var adaptador: ArrayAdapter<Pessoa>? = null
    fun inicializar(){
        novoButton     = findViewById(R.id.ID1_NOVObutton)
        listaItensView = findViewById(R.id.ID1_LISTAlistView)
        // Inicializacao o completa da lista //
        listaItens = ArrayList<Pessoa>()
        adaptador = ArrayAdapter<Pessoa>(this,android.R.layout.simple_list_item_1, listaItens!!)
        listaItensView?.adapter = adaptador
        // para teste (remover depois)
        listaItens?.add(Pessoa())
        adaptador?.notifyDataSetChanged()
        //////////////////////////////////////
    }
    fun inicializarAcoes(){
        novoButton?.setOnClickListener {
            var pacoteEnvio: Bundle = Bundle()
            // Colocando a LISTA INTEIRA com o rotulo "LISTA"
            pacoteEnvio.putSerializable("LISTA1",listaItens)
            /// ir para outra activity ///
            var novoRegActivityIntent:Intent = Intent(this,NovoRegActivity::class.java)
            /// Colocando esse pacote DENTRO da INTENT
            novoRegActivityIntent.putExtras(pacoteEnvio)
            startActivity(novoRegActivityIntent)
        }
        /// Detalhe em item da lista ///
        listaItensView?.setOnItemClickListener {adaptador, view, i, l ->
            var pacoteEnvio: Bundle = Bundle()
            // Colocando a LISTA INTEIRA com o rotulo "LISTA"
            pacoteEnvio.putSerializable("LISTA1",listaItens)
            pacoteEnvio.putInt("INDICE",i)
            var editarActivityIntent:Intent = Intent(this,EditarActivity::class.java)
            /// Colocando esse pacote DENTRO da INTENT
            editarActivityIntent.putExtras(pacoteEnvio)
            startActivity(editarActivityIntent)
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
        inicializar()
        inicializarAcoes()
        if (intent != null){
            var pacoteChegada:Bundle? = intent?.extras
            // Obteve a lista enviada
            if (pacoteChegada != null){
                listaItens = pacoteChegada?.getSerializable("LISTA2") as ArrayList<Pessoa>
                adaptador = ArrayAdapter<Pessoa>(this,android.R.layout.simple_list_item_1, listaItens!!)
                listaItensView?.adapter = adaptador
                adaptador?.notifyDataSetChanged()
            }
        }



    }
}