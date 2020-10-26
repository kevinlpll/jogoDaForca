package com.uniso.lpdm.jogoforca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;



public class JogoActivity extends AppCompatActivity {

    private Integer qtdTentativas;
    private String palavraJogo;
    private TextView textQtdTentativas;
    private TextView textPalavraJogo;
    private TextView textPalavrasJogadas;
    private TextView textLetrasJogadas;

    private EditText editLetraJogada;
    private EditText editPalavraJogada;

    private ArrayList<Character> letrasJogadas = new ArrayList<>();
    private ArrayList<String> palavrasJogadas = new ArrayList<>();


    private Integer tentativasRestantes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo);

        Intent intent = getIntent();
        qtdTentativas = intent.getIntExtra(MainActivity.QTD_TENTATIVAS,1);
        palavraJogo = intent.getStringExtra(MainActivity.PALAVRA_JOGO);

        this.tentativasRestantes = qtdTentativas;

        textQtdTentativas = findViewById(R.id.textQtdTentativas);
        textQtdTentativas.setText(tentativasRestantes.toString());

        textLetrasJogadas = findViewById(R.id.textLetrasTentadas);
        textPalavrasJogadas = findViewById(R.id.textPalavrasTentadas);
        textPalavraJogo = findViewById(R.id.textPalavraJogo);


        editLetraJogada = findViewById(R.id.editLetra);
        editPalavraJogada = findViewById(R.id.editPalavra);


        this.atualizaTela();
    }

    public void atualizaTela(){
        this.textPalavraJogo.setText(this.getPalavraJogoForca(this.palavraJogo,this.letrasJogadas));
        this.textLetrasJogadas.setText(this.letrasJogadas.toString().replace("[","").replace("]",""));
        this.textPalavrasJogadas.setText(this.palavrasJogadas.toString().replace("[","").replace("]",""));
        this.textQtdTentativas.setText(this.tentativasRestantes.toString());
        this.editLetraJogada.setText("");
        this.editPalavraJogada.setText("");

    }



    public void onClickTentativaLetra(View view){

        Character letra = Character.valueOf(this.editLetraJogada.getText().toString().toUpperCase().charAt(0));
        CharSequence text;
        Toast toast;



        if(letrasJogadas.contains(letra)){
            text = "Letra já informada";
        }
        else{
            this.letrasJogadas.add(letra);
            if(palavraJogo.indexOf(letra) >= 0){
                text = "Acertou";
            }else{
                this.tentativasRestantes--;
                //this.textQtdTentativas.setText(this.tentativasRestantes.toString());
                text = "Errou, tente outra letra";
            }

            this.atualizaTela();
        }
        toast = Toast.makeText(this,text,Toast.LENGTH_SHORT);
        toast.show();
    }

    public void onClickTentativaPalavra(View view){
        String palavraJogada = this.editPalavraJogada.getText().toString();
        Toast toast;
        CharSequence text;

        if(this.palavrasJogadas.contains(palavraJogada.toUpperCase())){
            text = "Palavra já jogada";
        }else{
            this.palavrasJogadas.add(palavraJogada.toUpperCase());
            if(this.palavraJogo.equalsIgnoreCase(palavraJogada)){
                text = "Acertou";
                
            }else{
                this.tentativasRestantes--;
                text = "Errou";
            }
        }


        this.atualizaTela();
        toast = Toast.makeText(this,text,Toast.LENGTH_SHORT);
        toast.show();

    }

    public String getPalavraJogoForca(String palavraJogo, ArrayList<Character> letrasJogadas ){


        Character letraAtual;

        ArrayList<Character> palavraRetornada = new ArrayList<Character>();
        for(int index = 0; index < palavraJogo.length();index++){
            palavraRetornada.add('_');
        }

        for(int indexLetra = 0; indexLetra < letrasJogadas.size(); indexLetra++){
            letraAtual = Character.valueOf(letrasJogadas.get(indexLetra).toString().toUpperCase().charAt(0));
            for(int index = 0; index < palavraJogo.length();index++){
                if(letraAtual.toString().equalsIgnoreCase(String.valueOf(palavraJogo.charAt(index)))){
                    palavraRetornada.set(index,letraAtual);
                }else if(palavraRetornada.get(index).charValue() == '_'){
                    palavraRetornada.set(index,'_');
                }
            }
        }

        return palavraRetornada.toString();
    }


}