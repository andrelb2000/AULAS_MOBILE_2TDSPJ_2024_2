package com.example.firebaseex1

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import java.io.Serializable
data class Peca(
     var idFb: String = "",
     var nomePeca: String = "",
     var codigoPeca: String = "",
     var custoPeca: Float = 0.0F
)
class MainActivity : AppCompatActivity() {
    private var botaoTeste: Button? = null
    private lateinit var bancoFb: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        FirebaseApp.initializeApp(this)
        bancoFb = FirebaseFirestore.getInstance()
        botaoTeste = findViewById(R.id.ID_testabutton)
        botaoTeste?.setOnClickListener {
            val user = hashMapOf(
                "nome" to "Joao",
                "idade" to 58,
                "cidade" to "São Paulo"
            )
            var peca = Peca("","P1","Carburador",100F)
            System.out.println("Inserindo dados:")
            bancoFb?.collection("users")?.add(user)?.addOnSuccessListener {
                documentReference ->
                    System.out.println("Usuário adicionado com ID: ${documentReference.id}")
                }?.addOnFailureListener { e ->
                    System.out.println("Erro ao adicionar usuário: $e")
                }

            bancoFb?.collection("pecas")?.add(peca)?.addOnSuccessListener {
                    documentReference ->
                System.out.println("Peca adicionada com ID: ${documentReference.id}")
            }?.addOnFailureListener { e ->
                System.out.println("Erro ao adicionar peça: $e")
            }
        }
    }
}