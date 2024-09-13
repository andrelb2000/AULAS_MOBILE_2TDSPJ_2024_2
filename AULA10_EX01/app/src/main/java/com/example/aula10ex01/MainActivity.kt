package com.example.aula10ex01

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Peca{
    private var nomePeca:String
    private var codigoPeca:String
    private var valorPeca:Float
    private var custoPeca:Float
    private var servicoPeca:Float

    constructor(
        nomePeca: String,
        codigoPeca: String,
        valorPeca: Float,
        custoPeca: Float,
        servicoPeca: Float
    ) {
        this.nomePeca = nomePeca
        this.codigoPeca = codigoPeca
        this.valorPeca = valorPeca
        this.custoPeca = custoPeca
        this.servicoPeca = servicoPeca
    }
    fun total():Float{
        return (valorPeca+servicoPeca)
    }
    fun lucro():Float{
        return (valorPeca-custoPeca + servicoPeca)
    }

    override fun toString(): String {
        return "Peca('$nomePeca' - '$codigoPeca' = R$ $valorPeca)"
    }

    fun getNomePeca():String {return nomePeca}
    fun getCodigoPeca():String {return codigoPeca}
    fun getValorPeca():Float{  return valorPeca}
    fun getCustoPeca():Float{  return custoPeca}
    fun getServicoPeca():Float{ return servicoPeca}

}



class MainActivity : AppCompatActivity() {
    private var nomePeca:EditText? = null
    private var codigoPeca:EditText? = null
    private var valorPeca:EditText? = null
    private var custoPeca:EditText? = null
    private var servicoPeca:EditText? = null
    private var botaoInsere:Button? = null
    private var botaoAltera:Button? = null
    private var botaoRemove:Button? = null
    private var botaoLimpa:Button? = null

    private var totalPecas:TextView? = null
    private var lucroPecas:TextView? = null
    private var listaPecas: ArrayList<Peca>? = null
    private var adaptador:ArrayAdapter<Peca>? = null
    private var listaPecasView:ListView? = null

    private var itemEmEdicao:Int = 0

    fun inicializacao(){
        nomePeca = findViewById(R.id.ID1_NOMEPECAeditTextText)
        codigoPeca = findViewById(R.id.ID1_CODIGOeditTextText2)
        valorPeca = findViewById(R.id.ID1_VALOReditTextNumber2)
        custoPeca = findViewById(R.id.ID1_CUSTOeditTextNumber)
        servicoPeca = findViewById(R.id.ID1_SERVICOeditTextNumber3)
        botaoInsere = findViewById(R.id.ID1_INSEREbutton)
        botaoAltera = findViewById(R.id.ID1_ALTERAbutton)
        botaoRemove = findViewById(R.id.ID1_REMOVEbutton2)
        botaoLimpa = findViewById(R.id.ID1_LIMPAbutton3)
        totalPecas = findViewById(R.id.ID1_TOTALtextView7)
        lucroPecas = findViewById(R.id.ID1_LUCROtextView6)
        listaPecasView = findViewById(R.id.ID1_LISTA_ListView)
        listaPecas = ArrayList<Peca>()
        adaptador = ArrayAdapter<Peca>(this,android.R.layout.simple_list_item_1, listaPecas!!)
        listaPecasView?.adapter = adaptador
        botaoInsere?.setOnClickListener {
            var nome   = nomePeca?.text.toString()
            var codigo = codigoPeca?.text.toString()
            var valor  = valorPeca?.text.toString().toFloat()
            var custo  = custoPeca?.text.toString().toFloat()
            var servico = servicoPeca?.text.toString().toFloat()
            var pecaInserida = Peca(nome,codigo,valor,custo,servico)
            listaPecas?.add(pecaInserida)
            adaptador?.notifyDataSetChanged()
            var total:Float = 0F
            var lucro:Float = 0F
            for(peca in listaPecas!!){
                total += peca.total()
                lucro += peca.lucro()
            }
            totalPecas?.text = "R$ ${total}"
            lucroPecas?.text = "R$ ${lucro}"
        }
        listaPecasView?.setOnItemClickListener { adaptador, view, i, l ->
            var peca = listaPecas?.get(i)
            nomePeca?.setText(peca?.getNomePeca() )
            codigoPeca?.setText(peca?.getCodigoPeca())
            valorPeca?.setText("${peca?.getValorPeca()}")
            custoPeca?.setText("${peca?.getCustoPeca()}")
            servicoPeca?.setText("${peca?.getServicoPeca()}")
            itemEmEdicao = i
        }
        botaoAltera?.setOnClickListener {
            var nome   = nomePeca?.text.toString()
            var codigo = codigoPeca?.text.toString()
            var valor  = valorPeca?.text.toString().toFloat()
            var custo  = custoPeca?.text.toString().toFloat()
            var servico = servicoPeca?.text.toString().toFloat()
            var pecaAlterada = Peca(nome,codigo,valor,custo,servico)
            listaPecas?.set(itemEmEdicao,pecaAlterada)
            adaptador?.notifyDataSetChanged()
            var total:Float = 0F
            var lucro:Float = 0F
            for(peca in listaPecas!!){
                total += peca.total()
                lucro += peca.lucro()
            }
            totalPecas?.text = "R$ ${total}"
            lucroPecas?.text = "R$ ${lucro}"
        }
        botaoRemove?.setOnClickListener {
            var nome   = nomePeca?.text.toString()
            var codigo = codigoPeca?.text.toString()
            var valor  = valorPeca?.text.toString().toFloat()
            var custo  = custoPeca?.text.toString().toFloat()
            var servico = servicoPeca?.text.toString().toFloat()
            listaPecas?.removeAt(itemEmEdicao)
            adaptador?.notifyDataSetChanged()
            var total:Float = 0F
            var lucro:Float = 0F
            for(peca in listaPecas!!){
                total += peca.total()
                lucro += peca.lucro()
            }
            totalPecas?.text = "R$ ${total}"
            lucroPecas?.text = "R$ ${lucro}"
        }
        botaoLimpa?.setOnClickListener {
            itemEmEdicao = 0
            listaPecas?.clear()
            adaptador?.clear()
            adaptador?.notifyDataSetChanged()
            var total:Float = 0F
            var lucro:Float = 0F
            totalPecas?.text = "R$ ${total}"
            lucroPecas?.text = "R$ ${lucro}"
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
        inicializacao()
    }
}