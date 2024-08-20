package com.example.aula_02_rem_v1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

/// Criando Classe para o modelo ////
/// O modelo é                  ////
///  (CoefXEq1)X + (CoefYEq1)Y = (Eg1)  Ex: (2)X + (3)Y = (37)
///  (CoefXEq2)X + (CoefYEq2)Y = (Eg2)  Ex: (1)X + (-1)Y = (-4)
class ModeloMatLinear {
    // Variaveis do Modelo
    private  var xModelo: Int = 0
    private  var yModelo: Int = 0
    private  var CoefXEq1: Int = 0
    private  var CoefYEq1: Int = 3
    private  var Eq1:      Int = 30

    // Atualizacao de X ew Y
    public fun xUp(){
        this.xModelo++
    }
    // TODO xDown, YUp, YDown

    //Valores de X e Y como Strings
    public fun valorXString():String {
       return ("Valor X ${this.xModelo}")
    }

    // Inserir coeficientes
    public fun setCoefXEq1(x:Int){
        this.CoefXEq1 = x
    }
    //TODO  Inserir outros coedficientes


    // Calculo das equaçoes
    public fun eq1():Int {
        var eq1Temp: Int = 0
        ///  (CoefXEq1)X             + (CoefYEq1)Y        = (Eg1)
        eq1Temp = CoefXEq1 * xModelo +  CoefYEq1 *yModelo
        return (eq1Temp)
    }
   //TODO  Calcular Eq2

    public fun status():String{
        if(eq1() == Eq1){
            return ("Sucesso")
        } else{
            return ("Falha")
        }

    }



}




class MainActivity : AppCompatActivity() {
    ///private  var xModelo: Int = 0
    /// private  var yModelo: Int = 0
    private var meuModelo: ModeloMatLinear = ModeloMatLinear()

    private  var viewX: TextView? = null
    private  var eq1:   TextView? = null
    private  var coefAEq: EditText? = null
    private  var statusEq1: TextView? = null

    private fun inicializar(){
        val botaoUp: Button         = findViewById(R.id.ID1_XUp_button)
        val botaoNovoModelo: Button = findViewById(R.id.ID1_NovoModelo_button)
        /// Equacao
        viewX = findViewById(R.id.ID1_XMod_textView)
        eq1   = findViewById(R.id.ID1_EQ1_textView)
        coefAEq = findViewById(R.id.ID1_Eq1CoefA_editTextText)

        statusEq1 = findViewById(R.id.ID1_Status_textView)

        botaoUp.setOnClickListener {
            /// xModelo = xModelo + 1  Troca por --->
            this.meuModelo.xUp()
            ///viewX?.text = "Valor X ${xModelo}"
            viewX?.text = meuModelo.valorXString()

            // var eq1Temp: Int = 0
            /// var coefATemp: Int = 0  --> Vai para a criacao de novo modelo
            //// coefATemp = coefAEq?.text.toString().toInt()
            /// eq1Temp = coefATemp   * xModelo + 3*yModelo  ---> Isso foi para dentro da funcao  "public fun eq1():Int" { do meuModelo
            // eq1Temp.toString()
            eq1?.text = meuModelo.eq1().toString()
            statusEq1?.text = meuModelo.status().toString()
        }
        botaoNovoModelo.setOnClickListener {
            var coefATemp: Int = 0
            coefATemp = coefAEq?.text.toString().toInt()
            meuModelo.setCoefXEq1(coefATemp)
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
    }
}