package com.example.applistadetalheedicao_ex1

import android.content.Intent
import android.os.Bundle
import android.util.JsonReader
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import com.google.firebase.firestore.FirebaseFirestore
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Executors
import javax.net.ssl.HttpsURLConnection

class NovoITEMActivity : AppCompatActivity() {
    private var confirmaButton:Button? = null
    private var cancelaButton:Button? = null
    ///// Vamos obter algo da internet /////////
    private var buscaCEPButton:Button? = null

    private var listaChegada:ArrayList<Pessoa>? = null
    private var nomeEditText: EditText? = null
    private var telEditText: EditText? = null

    private var ruaEditText: EditText? = null
    private var cepEditText: EditText? = null

    private lateinit var bancoFb: FirebaseFirestore

    fun inicializar(){
        confirmaButton = findViewById(R.id.ID2_CONFIRMAbutton)
        cancelaButton  = findViewById(R.id.ID2_CANCLA_button2)
        /// Busca de CEP ///
        buscaCEPButton = findViewById(R.id.ID2_BUSCA_CEPbutton2)

        nomeEditText   = findViewById(R.id.ID2_NOMEeditTextText)
        telEditText    = findViewById(R.id.ID2_TELEFONEeditTextText2)
        ruaEditText    = findViewById(R.id.ID2_RUAeditTextText2)
        cepEditText    = findViewById(R.id.ID2_CEPeditTextNumber)
    }
    fun inicializaAcoes(){
        confirmaButton?.setOnClickListener {
            //// Pegar os novos valores nos componentes de entrada (Ex, edit text, etc)
            //// Criar um NOVO objeto da classe pessoa
            var pessoa:Pessoa = Pessoa()
            pessoa?.nomePessoa = nomeEditText?.text.toString()
            pessoa?.telPessoa?.telefone = telEditText?.text.toString()
            /// Adicionar pessoa no FIREBASE ///
            bancoFb = FirebaseFirestore.getInstance()
            bancoFb.collection("PESSOA").add(pessoa).addOnSuccessListener {
                documento -> pessoa.idPessoaFb = documento.id
                System.out.println("Pessoa adicionada ID: ${pessoa.idPessoaFb}")
            }.addOnFailureListener { e ->
                System.out.println("Erro ao adicionar dado pessoa: $e")
            }

            bancoFb.collection("PESSOA").document(pessoa.idPessoaFb).set(pessoa).addOnSuccessListener {
                System.out.println("Pessoa adicionada ID: ${pessoa.idPessoaFb}")
            }.addOnFailureListener { e ->
                System.out.println("Erro ao adicionar dado pessoa: $e")
            }
            /////////////////////////////////////
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
        buscaCEPButton?.setOnClickListener {
        //Monta a URL acrescentando o parametro
        var cepRua = cepEditText?.text.toString()
        val url = URL("https://viacep.com.br/ws/"+  cepRua + "/json")
        ///////////////////////////////////////////
        // Começar execução em segundo plano para busca na rede
        val executorSegundoPlano = Executors.newSingleThreadExecutor()
            /// A partir desse ponto roda em segundo plano
            executorSegundoPlano.execute{
                /// Abre a conexão com a API
                val conexao = url.openConnection() as HttpsURLConnection
                // Define protocolo
                conexao.requestMethod = "GET"
                // Checa se a conexão foi bem sucedida
                if (conexao.responseCode == HttpsURLConnection.HTTP_OK){
                    // Obtem a Strem de entrada
                    val streamEntrada = conexao.inputStream
                    val leitorJason = JsonReader(InputStreamReader(streamEntrada, "UTF-8"))
                    /////// A partir desse ponto é apenas a varredura do JSON
                    leitorJason.beginObject()
                    if(leitorJason.hasNext()) {
                        val cepLabel = leitorJason.nextName()
                        leitorJason.skipValue()
                        val ruaLabel = leitorJason.nextName()
                        val ruaValue = leitorJason.nextString()
                        // Acessa o plano de execução principal//
                        runOnUiThread {
                            ruaEditText?.setText(ruaValue.toString())
                        }
                        /////////////////////////////////////////
                    }
                }
                ///////////////////////////////////////////////////////////////
            }
        /// Fechando a execução
        executorSegundoPlano.shutdown()
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