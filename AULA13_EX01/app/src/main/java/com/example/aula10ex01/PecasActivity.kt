package com.example.aula10ex01

import android.content.Intent
import android.os.Bundle
import android.provider.Telephony.Mms.Intents
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PecasActivity : AppCompatActivity() {
    private var nomePeca: EditText? = null
    private var codigoPeca: EditText? = null
    private var valorPeca: EditText? = null
    private var custoPeca: EditText? = null
    private var servicoPeca: EditText? = null
    private var botaoVolta: Button? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pecas)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        nomePeca = findViewById(R.id.ID2_NOMEPECAeditTextText)
        codigoPeca = findViewById(R.id.ID2_CODIGOeditTextText2)
        valorPeca = findViewById(R.id.ID2_VALOReditTextNumber2)
        custoPeca = findViewById(R.id.ID2_CUSTOeditTextNumber)
        servicoPeca = findViewById(R.id.ID2_SERVICOeditTextNumber3)
        botaoVolta = findViewById(R.id.ID2_VOLTAbutton)

        // "intent" é um atributo já pre existente me TODAS as aactivities //
        val pacoteDetalhe = intent.extras

        // Lista com todoso os itens enviado pela Activit de origem //
        val listaChegada: ArrayList<Peca> = pacoteDetalhe?.getSerializable("LISTA") as ArrayList<Peca>
        // Indicie do item selecionado
        var indice : Int = pacoteDetalhe?.getInt("INDICE")?:-1
        var pecaSelecionada:Peca? = listaChegada.get(indice)
         /*
        nomePeca?.setText(pacoteDetalhe?.getString("NOME") )
        codigoPeca?.setText(pacoteDetalhe?.getString("CODIGO") )
        valorPeca?.setText(pacoteDetalhe?.getString("PRECO") )
        custoPeca?.setText(pacoteDetalhe?.getString("CUSTO") )
        servicoPeca?.setText(pacoteDetalhe?.getString("SERVICO") )  */
        /// Substituindo o que antes vinha no Bundle e pegando direto do objeto peca que chegou na lista
        nomePeca?.setText( pecaSelecionada?.getNomePeca().toString() )
        codigoPeca?.setText( pecaSelecionada?.getCodigoPeca().toString())
        valorPeca?.setText( pecaSelecionada?.getValorPeca().toString() )
        custoPeca?.setText( pecaSelecionada?.getCustoPeca().toString())
        servicoPeca?.setText( pecaSelecionada?.getServicoPeca().toString() )

        botaoVolta?.setOnClickListener {
            val principalIntent = Intent(this,MainActivity::class.java)
            val pacoteLista = Bundle()
            // Obter dados a peca ATUAL (editada)
            var nome   = nomePeca?.text.toString()
            var codigo = codigoPeca?.text.toString()
            var valor  = valorPeca?.text.toString().toFloat()
            var custo  = custoPeca?.text.toString().toFloat()
            var servico = servicoPeca?.text.toString().toFloat()
            var pecaAlterada = Peca(nome,codigo,valor,custo,servico)
            listaChegada.set(indice,pecaAlterada)

            // Removendo daqui porque vou precisar para carregar os dados
            //  val listaChegada: ArrayList<Peca> = intent.extras?.getSerializable("LISTA") as ArrayList<Peca>

            pacoteLista.putSerializable("LISTA",listaChegada)
            // Devolvendo o pacote para a actify pai //
            principalIntent.putExtras(pacoteLista)
            principalIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(principalIntent)
        }

    }
}