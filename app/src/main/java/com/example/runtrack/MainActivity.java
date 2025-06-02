package com.example.runtrack;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText descricao;
    private EditText distancia;
    private Button salvar, excluir;
    private TextView informacoes;
    private MyDatabase db;
    private int distanciaTotal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //INICIALIZAÇÃO DE VARIÁVEIS
        descricao = findViewById(R.id.editTextDescricao);
        excluir = findViewById(R.id.buttonExcluir);
        distancia = findViewById(R.id.editTextDistancia);
        salvar = findViewById(R.id.buttonSalvar);
        informacoes = findViewById(R.id.textViewInformacoes);

        //CRIAÇÃO DO BANCO DE DADOS NA VARIÁVEL "db"
        db = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, "exercicios.db").build();

        //INICIA O APP EXIBINDO AS INFORMAÇÕES DE EXERCÍCIOS CADASTRADOS, CASO EXISTAM
        buscarExercicios();

        //BOTÃO QUE CHAMA O CADASTRO DE EXERCÍCIOS
        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarExercicio();
            }
        });

        //BOTÃO QUE EXCLUI O ÚLTIMO EXERCÍCIO CADASTRADO
        excluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                excluirExercicio();
            }
        });
    }

    private void excluirExercicio() {
        //TODO: BUSCAR O ÚLTIMO EXERCÍCIO NO BANCO DE DADOS, EXCLUÍ-LO, E DEPOIS BUSCAR TODOS OS EXERCÍCIOS NOVAMENTE, PARA ATUALIZAR A TELA
        //OBS: LEMBRAR QUE O ACESSO AO BANCO DE DADOS PRECISA SER FEITO EM UMA NOVA THREAD
    }

    private void salvarExercicio() {
        //TODO: CAPTURAR AS INFORMAÇÕES DO EXERCÍCIO, INSERIR NO BANCO DE DADOS, E DEPOIS BUSCAR TODOS OS EXERCÍCIOS NOVAMENTE, PARA ATUALIZAR A TELA
        //OBS: LEMBRAR QUE O ACESSO AO BANCO DE DADOS PRECISA SER FEITO EM UMA NOVA THREAD
    }

    private void buscarExercicios() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Exercicio> todosExercicios = db.exercicioDAO().buscaTodosExercicios();
                //TODO: BUSCAR O ÚLTIMO EXERCÍCIO CADASTRADO, E O MAIOR EXERCÍCIO CADASTRADO
                distanciaTotal = 0;
                if (!todosExercicios.isEmpty()) {
                    for (Exercicio exercicioAtual : todosExercicios) {
                        distanciaTotal += exercicioAtual.getDistancia();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            descricao.setText("");
                            distancia.setText("");
                            Toast.makeText(MainActivity.this, "Exercício Cadastrado!", Toast.LENGTH_SHORT).show();

                            //TODO: ATUALIZAR A TELA COM AS INFORMAÇÕES DO ÚLTIMO EXERCÍCIO CADASTRADO, E DO MAIOR EXERCÍCIO CADASTRADO
                            informacoes.setText("Distância total percorrida: " + distanciaTotal + " metros");
                        }
                    });
                }else{
                    informacoes.setText("Nenhum exercício cadastrado até o momento");
                }
            }
        }).start();
    }
}