package com.example.applistadetalheedicao_ex1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class EditarITEMActivity : AppCompatActivity() {
    private var nomeEditText: EditText? = null
    private var telEditText: EditText? = null

    private var confirmaButton: Button? = null
    private var removeButton: Button? = null
    private var cancelaButton: Button? = null

    var listaChegada:ArrayList<Pessoa>? = null
    var indice:Int? = -1

    fun inicializar(){
        confirmaButton = findViewById(R.id.ID3_CONFIRMAbutton)
        removeButton  = findViewById(R.id.ID3_REMOVEbutton2)
        cancelaButton  = findViewById(R.id.ID3_CANCELAbutton3)
        nomeEditText   = findViewById(R.id.ID3_NOMEeditTextText)
        telEditText    = findViewById(R.id.ID3_TELeditTextText2)
    }
    fun inicializaAcoes(){
        confirmaButton?.setOnClickListener {
            //// Pegar os novos valores nos componentes de entrada (Ex, edit text, etc)
            //// Criar um NOVO objeto da classe pessoa
            var pessoa:Pessoa = Pessoa()
            if (pessoa != null) {
                pessoa?.setNomePessoa(nomeEditText?.text.toString())
                pessoa?.getTel()?.setTelefone(telEditText?.text.toString())
            }
            //// Colocar na lista no mesmo lugar do objeto anteriror
            listaChegada?.set(indice!!,pessoa) // Colocando a LISTA INTEIRA com o rotulo "LISTA2"

            var pacoteEnvio: Bundle = Bundle()
            pacoteEnvio.putSerializable("LISTA2",listaChegada)
            // Voltar para a Activity Principal
            val mainActivityIntent = Intent(this,MainActivity::class.java)
            //// Enviar a lista de volta
            mainActivityIntent.putExtras(pacoteEnvio)
            /// Se já houver uma criada, reutilize
            mainActivityIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(mainActivityIntent)
        }
        removeButton?.setOnClickListener {
            ////////// Remover o indice da lista //////////
            if (indice != null) {
                listaChegada?.removeAt(indice!!)
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
            var pacoteEnvio: Bundle = Bundle()
            pacoteEnvio.putSerializable("LISTA2",listaChegada)
            // Voltar para a Activity Principal
            val mainActivityIntent = Intent(this,MainActivity::class.java)
            //// Enviar a lista de volta
            mainActivityIntent.putExtras(pacoteEnvio)
            /// Se já houver uma criada, reutilize
            mainActivityIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(mainActivityIntent)
        }
    }
    fun obterDadosChegada(){
        if (intent != null){
            var pacoteChegada:Bundle? = intent?.extras
            // Obteve a lista enviada
            if (pacoteChegada != null){
                listaChegada = pacoteChegada?.getSerializable("LISTA") as ArrayList<Pessoa>
            }
            // Indice do item da lista que foi selecionado na activity anterior //
            indice = pacoteChegada?.getInt("ITEM")?:-1
            /// Obter a pessoa dentro da lilsta
            var pessoa: Pessoa? = listaChegada?.get(indice!!)
            nomeEditText?.setText(pessoa?.getNomePessoa().toString())
            telEditText?.setText(pessoa?.getTel()?.getTelefone().toString())
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_editar_itemactivity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        inicializar()
        inicializaAcoes()
        obterDadosChegada()
    }
}