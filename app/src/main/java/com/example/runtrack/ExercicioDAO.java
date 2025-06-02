package com.example.runtrack;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;
@Dao
public interface ExercicioDAO {
    //TODO: IMPLEMENTAR MÉTODOS PARA INSERIR UM EXERCÍCIO, BUSCAR O MAIOR EXERCÍCIO, E BUSCAR O ÚLTIMO EXERCÍCIO
    @Query("SELECT * FROM exercicios")
    List<Exercicio> buscaTodosExercicios();

}
