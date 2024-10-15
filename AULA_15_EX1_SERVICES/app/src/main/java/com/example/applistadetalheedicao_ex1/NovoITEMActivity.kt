package com.example.applistadetalheedicao_ex1

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class NovoITEMActivity : AppCompatActivity() {
    private var confirmaButton:Button? = null
    private var cancelaButton:Button? = null
    private var listaChegada:ArrayList<Pessoa>? = null
    private var nomeEditText: EditText? = null
    private var telEditText: EditText? = null

    fun inicializar(){
        confirmaButton = findViewById(R.id.ID2_CONFIRMAbutton)
        cancelaButton  = findViewById(R.id.ID2_CANCLA_button2)
        nomeEditText   = findViewById(R.id.ID2_NOMEeditTextText)
        telEditText    = findViewById(R.id.ID2_TELEFONEeditTextText2)
    }
    fun inicializaAcoes(){
        confirmaButton?.setOnClickListener {
            //// Pegar os novos valores nos componentes de entrada (Ex, edit text, etc)
            //// Criar um NOVO objeto da classe pessoa
            var pessoa:Pessoa = Pessoa()
            pessoa?.setNomePessoa(nomeEditText?.text.toString())
            pessoa?.getTel()?.setTelefone(telEditText?.text.toString())
            //// Colocar na lista no mesmo lugar do objeto anteriror
            listaChegada?.add(pessoa) // Colocando a LISTA INTEIRA com o rotulo "LISTA2"



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
        cancelaButton?.setOnClickListener {
            // Voltar para a Activity Principal
            val mainActivityIntent = Intent(this,MainActivity::class.java)
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
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_novo_itemactivity)
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