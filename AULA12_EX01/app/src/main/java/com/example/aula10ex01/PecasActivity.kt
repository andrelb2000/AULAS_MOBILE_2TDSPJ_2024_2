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

        val pacoteDetalhe = intent.extras

        nomePeca?.setText(pacoteDetalhe?.getString("NOME") )
        codigoPeca?.setText(pacoteDetalhe?.getString("CODIGO") )
        valorPeca?.setText(pacoteDetalhe?.getString("PRECO") )
        custoPeca?.setText(pacoteDetalhe?.getString("CUSTO") )
        servicoPeca?.setText(pacoteDetalhe?.getString("SERVICO") )

        botaoVolta?.setOnClickListener {
            val principalIntent = Intent(this,MainActivity::class.java)
            val pacoteLista = Bundle()
            val listaChegada: ArrayList<Peca> = intent.extras?.getSerializable("LISTA") as ArrayList<Peca>

            pacoteLista.putSerializable("LISTA",listaChegada)
            principalIntent.putExtras(pacoteLista)
            principalIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(principalIntent)
        }

    }
}