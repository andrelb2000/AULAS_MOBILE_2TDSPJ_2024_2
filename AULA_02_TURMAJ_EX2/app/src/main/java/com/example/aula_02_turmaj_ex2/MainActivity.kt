package com.example.aula_02_turmaj_ex2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text


class Modelo {
    private  var xModelo: Int = 0
    private  var yModelo: Int = 0

    // (coefX)X + (coefY)Y = eq1
    private  var eq1: Int = 0
    private  var coefXEq1: Int = 2
    private  var coefYEq1: Int = 3




    fun getXMod():Int{
        return xModelo
    }
    fun xUp(){
        this.xModelo++
    }
    fun valorEq1(): Int? {
        eq1 = coefXEq1 * xModelo + coefYEq1 * yModelo
        return eq1
    }
    fun textoEq1(): String {
        return "(${coefXEq1})X  +  (${coefYEq1})Y = ${valorEq1()} "
    }
    fun setCoefXEq1(texto:String){
        coefXEq1 = texto.toInt()
    }




    /// TODO ///
    // Fazer Valor Eq2 //

}

class MainActivity : AppCompatActivity() {
    var xModView : TextView? = null
    var xCoefEdit: EditText? = null
    var eq1View:  TextView?  = null
    var modelo: Modelo = Modelo()

    fun inicializar(){
        ////// Aqui nosso codigo ///
        ///// CAPTURAR COMPONENTES //////////////////////////////
        this.xModView  =  findViewById(R.id.ID1_XMOD_textView)
        this.eq1View   =  findViewById(R.id.ID1_EQ1_textView2)
        this.xCoefEdit =  findViewById(R.id.ID1_XCoef_editTextText)
        val buttonUP : Button = findViewById(R.id.ID1_XUP_button)
        /////////////////////////////////////////////////////////////

        //////////////// Atribuir LISTENERS //////////////////////////////
        buttonUP.setOnClickListener {
            this.modelo.xUp()
            this.xModView?.text = "X = ${this.modelo.getXMod()}"
            this.eq1View?.text = modelo.textoEq1()
        }
        val buttoAltModelo: Button = findViewById(R.id.ID1_AltMod_button)
        buttoAltModelo.setOnClickListener {
            modelo.setCoefXEq1(this.xCoefEdit?.text.toString())
        }
        ///////////////////////////////////////////////////////////////
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
        //////  INICIALIZACAO DOS COMPONENTES //////
        inicializar()
        /////////////////////////////////////////////
    }
}