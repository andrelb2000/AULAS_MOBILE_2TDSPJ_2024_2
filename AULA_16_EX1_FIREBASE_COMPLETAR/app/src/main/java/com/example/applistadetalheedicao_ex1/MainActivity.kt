package com.example.applistadetalheedicao_ex1

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
    public fun setTelefone(tel:String){
        telefone = tel
    }
    public fun getTelefone():String{
        return telefone
    }
}
//////////////////////////////////////////////
class Pessoa:Serializable{
    private var nomePessoa:String = "Joao"
    private var telPessoa:Telefone = Telefone()



    override fun toString(): String {
        return "$nomePessoa"
    }
    public fun getNomePessoa():String{
        return nomePessoa
    }
      public fun getTel():Telefone{
        return telPessoa
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

    // Funcao que vai materializar os componentes visuais (ligar com os componentes de codigo)
    fun inicializar(){
        novoButton     = findViewById(R.id.ID1_NOVObutton)
        listaItensView = findViewById(R.id.ID1_ListView)

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
        ///// Esse vai para criacao de NOVO item ////
        novoButton?.setOnClickListener {
            var pacoteEnvio: Bundle = Bundle()
            // Colocando a LISTA INTEIRA com o rotulo "LISTA"
            pacoteEnvio.putSerializable("LISTA",listaItens)
            var novaActivityIntent:Intent = Intent(this,NovoITEMActivity::class.java)
            novaActivityIntent.putExtras(pacoteEnvio)
            startActivity(novaActivityIntent)
        }
        /// Detalhe em item da lista ///
        listaItensView?.setOnItemClickListener {adaptador, view, i, l ->
            /// AGORA vou ENVIAR duas coisas:
            // 1 - A LISTA ATUALA
            // 2 - O NUMERO do ITEM que foi clicado

            var editarActivityIntent:Intent = Intent(this,EditarITEMActivity::class.java)
            var pacoteEnvio: Bundle = Bundle()
            /// Colocando o numero do item selecionado (i acima) no pacote com o rotulo "ITEM"
            pacoteEnvio.putInt("ITEM",i)
            // Colocando a LISTA INTEIRA com o rotulo "LISTA"
            pacoteEnvio.putSerializable("LISTA",listaItens)
            /// Colocando esse pacote DENTRO da INTENTE
            editarActivityIntent.putExtras(pacoteEnvio)
            startActivity(editarActivityIntent)
        }
    }
    fun obterDadosRetorno(){
        if (intent != null){
            var pacoteChegada:Bundle? = intent?.extras
            // Obteve a lista enviada
            if (pacoteChegada != null){
                listaItens = pacoteChegada?.getSerializable("LISTA2") as ArrayList<Pessoa>
                if (listaItens!=null){
                    adaptador = ArrayAdapter<Pessoa>(this,android.R.layout.simple_list_item_1, listaItens!!)
                    listaItensView?.adapter = adaptador
                    adaptador?.notifyDataSetChanged()
                }
            }
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
        obterDadosRetorno()
    }

}