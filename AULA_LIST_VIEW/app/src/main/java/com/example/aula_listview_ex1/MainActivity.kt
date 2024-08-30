package com.example.aula_listview_ex1

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private val itensCompra: ArrayList<String> = ArrayList<String>()
    private lateinit var jarvis: ArrayAdapter<String>
    private var itemInsere: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        /////////
        /// 1 - Materializar o ListView do Layout
        val listView: ListView = findViewById(R.id.ID1_ListCompras_ListView)
        /// 2 - Criar o objeto Adaptador
        jarvis = ArrayAdapter(this, android.R.layout.simple_list_item_1, itensCompra)
        /// 3 - Conectar o Adaptador (Jarvis) com a lista Visual (aquela do Layout)
        listView.adapter = jarvis
        jarvis.clear()
        itensCompra.add("Meu primeiro item")
        jarvis.notifyDataSetChanged()
        //////////

        itemInsere = findViewById(R.id.ID1_INSEERE_editTextText)
        val botaoInsere: Button = findViewById(R.id.ID1_INSERE_button)
        botaoInsere.setOnClickListener {
            var item = itemInsere?.text.toString()
            itensCompra.add(item)
            jarvis.notifyDataSetChanged()
        }
    }
}