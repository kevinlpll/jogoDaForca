package com.uniso.lpdm.jogoforca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String QTD_TENTATIVAS = "QTD_TENTATIVAS";
    public static final String PALAVRA_JOGO = "PALAVRA_JOGO";

    private Button btnJogar ;
    private EditText editQuantidadeTentativas;
    private EditText editPalavraJogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnJogar = (Button) findViewById(R.id.btnJogar);
        editQuantidadeTentativas = (EditText) findViewById(R.id.editQuantidadeTentativas);
        editPalavraJogo = (EditText) findViewById(R.id.editPalavraJogo);

    }

    public void onClickJogar(View view){
        Intent intent = new Intent(this,JogoActivity.class);


        intent.putExtra(MainActivity.QTD_TENTATIVAS,Integer.parseInt(editQuantidadeTentativas.getText().toString()));
        intent.putExtra(MainActivity.PALAVRA_JOGO, editPalavraJogo.getText().toString().toUpperCase());

        startActivity(intent);

    }


}