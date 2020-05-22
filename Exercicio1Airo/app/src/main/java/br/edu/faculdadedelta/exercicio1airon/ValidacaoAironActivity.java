package br.edu.faculdadedelta.exercicio1airon;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;




public class ValidacaoAironActivity extends AppCompatActivity {



    private String nome;
    private String dataNasc;
    private String peso;
    private double pesoB;
    private String altura;
    private String dataNascFormatada;
    private String dataHoje;

    public static final int RESULT_CODE_SUCCESS = 1;
    public static final int RESULT_CODE_ERROR = 0;


    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    Date data = new Date();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validacao);


        /*aqui pega a intent da activityMain*/
        Intent intent = getIntent();

        if(intent != null){

            nome = intent.getStringExtra("nome");
            dataNasc = intent.getStringExtra("dataNasc");
            peso = intent.getStringExtra("peso");
            altura = intent.getStringExtra("altura");



            pesoB = Double.parseDouble(peso);
            dataNascFormatada = dataNasc;



            TextView tvNome = findViewById(R.id.tvNome);
            tvNome.setText("Nome: "+ nome);

            TextView tvDataNascimento = findViewById(R.id.tvDataNascimento);
            tvDataNascimento.setText("DataNasc: "+dataNasc);

            TextView tvPeso = findViewById(R.id.tvPeso);
            tvPeso.setText("Peso: "+peso);

            TextView tvAltura = findViewById(R.id.tvAltura);
            tvAltura.setText("Altura: "+altura);

        }


    }




    public void validar(View view) throws ParseException {

        String resposta = "";
        String respostaB = "";


            if (nome.isEmpty()) {
                resposta = "O campo nome é obrigatório!";
                respostaB = "nome";
            } else if (nome.length() < 30) {
                resposta = "O nome deve conter mais de 30 letras!";
                respostaB = "nome";
            } else if (dataNasc.isEmpty()) {
                resposta = "O campo Data Nascimento é obrigatório!";
                respostaB = "dataNasc";
            } else if(dataNasc.length() < 10 || dataNasc.length() > 10) {
                resposta = "Formato correto da data é, por exemplo: 01/01/2000";
                respostaB = "dataNasc";
            } else if(dataNasc.length() == 10){

                dataHoje = sdf.format(data);
                Date dataAtual = new Date(sdf.parse(dataHoje).getTime());
                Date novaData = new Date(sdf.parse(dataNascFormatada).getTime());


                if (dataAtual.before(novaData)) {
                    resposta = "Data Nascimento não pode ser maior do que a data de hoje!";
                    respostaB = "dataNasc";
                } else if (pesoB == 0) {
                    resposta = "O campo Peso é obrigatório!";
                    respostaB = "peso";
                } else if (pesoB < 60.5) {
                    resposta = "O peso tem que ser maior que 60,4";
                    respostaB = "peso";
                } else if (altura.isEmpty()) {
                    resposta = "O campo Altura é obrigatório!";
                    respostaB = "altura";
                } else {
                    resposta = "DADOS VALIDOS";
                }


            } else if (peso.equals("00")) {
                resposta = "O campo Peso é obrigatório!";
            } else if (pesoB < 60.4) {
                resposta = "O peso tem que ser maior que 60,4";
            } else if (altura.isEmpty()) {
                resposta = "O campo Altura é obrigatório!";
            } else {
                resposta = "DADOS VALIDOS";
            }




        Intent data = new Intent();
        data.putExtra("resposta", resposta);
        data.putExtra("respostaB", respostaB);

        setResult(RESULT_CODE_SUCCESS, data);

        finish();


        }



}
