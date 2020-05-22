package br.edu.faculdadedelta.exercicio1airon;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainAironActivity extends AppCompatActivity {

    private final int CODIGO_RETORNO = 0;

    private EditText etNome;
    private EditText etDataNascimento;
    private EditText etPeso;
    private EditText etAltura;
    private EditText etPesoB;

    //está variavel é uma variavel de auxilio para direcionar o cursor para o campo correspondente que esteja errado
    private EditText x;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNome = this.findViewById(R.id.etNome);
        etDataNascimento = this.findViewById(R.id.etDataNascimento);
        etPeso = this.findViewById(R.id.etPeso);
        etAltura = this.findViewById(R.id.etAltura);


    }



    public void enviar(View view) {


        Intent intent = new Intent(getBaseContext(), ValidacaoAironActivity.class);


        String nome = etNome.getText().toString();
        String dataNasc = etDataNascimento.getText().toString();
        String peso = etPeso.getText().toString();
        String altura = etAltura.getText().toString();


       if(peso.equals("")){
            peso = "00";
        }




            intent.putExtra("nome", nome);
            intent.putExtra("dataNasc", dataNasc);
            intent.putExtra("peso", peso);
            intent.putExtra("altura", altura);



            startActivityForResult(intent, CODIGO_RETORNO);

    }


    public void limpar(View view){

            etNome.setText("");
            etDataNascimento.setText("");
            etPeso.setText("");
            etAltura.setText("");


        //Este código é para direcionar o cursor para o campo nome isso quando o botão limpar for acionado
        myHandlerB.sendEmptyMessage(0);

    }


    //Este código é para direcionar o cursor para o campo nome isso quando o botão limpar for acionado
    final Handler myHandlerB = new Handler() {
        public void handleMessage(Message msg) {
            Log.i("HANDLER", "handleMessage::recebendo msg " + msg.what);
            // x.setText("");
            etNome.requestFocus();

        }
    };




    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == CODIGO_RETORNO) {

            if (resultCode == ValidacaoAironActivity.RESULT_CODE_SUCCESS) {
                String resposta = data.getStringExtra("resposta");

                //Está respostaB é para verificar qual o campo que aconteceu o erro e com isso direcionar o cursor para o campo corresepondente.
                String respostaB = data.getStringExtra("respostaB");

                if (resposta.equals("DADOS VALIDOS")) {
                    Toast.makeText(getBaseContext(), resposta, Toast.LENGTH_LONG).show();
                } else {
                    AlertDialog.Builder alerta = new AlertDialog.Builder(MainAironActivity.this);
                    alerta.setTitle("                    ERRO!");
                    alerta.setCancelable(true);
                    AlertDialog alertDialog = alerta.create();
                    alertDialog.show();

                    Toast.makeText(getBaseContext(), resposta, Toast.LENGTH_LONG).show();

                    //Esse código traz o cursor para o campo que não foi preenchido corretamente
                    myHandler.sendEmptyMessage(0);
                }

                //Esse código verifica qual o campo que foi preenchido errado e faz com que o cursor seja levado para o  campo correspondente.
                if(respostaB.equals("nome")){
                    x = etNome;
                }else if(respostaB.equals("dataNasc")){
                    x = etDataNascimento;
                }else if(respostaB.equals("peso")){
                    x = etPeso;
                }else if(respostaB.equals("altura")){
                    x = etAltura;
                }
            }

        }

    }

    //Esse código traz o cursor para o campo que não foi preenchido corretamente
    final Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
            Log.i("HANDLER", "handleMessage::recebendo msg " + msg.what);
            // x.setText("");
            x.requestFocus();

        }
    };

}
