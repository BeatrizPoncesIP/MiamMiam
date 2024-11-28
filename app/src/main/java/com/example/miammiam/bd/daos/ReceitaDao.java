package com.example.miammiam.bd.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.sql.Date;
import java.util.List;
import com.example.miammiam.bd.entities.Receita;

@Dao
public interface ReceitaDao {

    @Insert
    void inserirReceita(Receita receita);

    @Update
    void editarReceita(Receita receita);

    @Delete
    void deletarReceita(Receita receita);

    // Retornar lista de receitas por usuário (usuarioEmail)
    @Query("SELECT * FROM Receita WHERE usuarioEmail = :usuarioEmail")
    LiveData<List<Receita>> listarReceitasPorUsuario(String usuarioEmail);

    // Retornar lista de receitas favoritas por usuário (usuarioEmail)
    @Query("SELECT * FROM Receita WHERE usuarioEmail = :usuarioEmail AND favorito = 1")
    LiveData<List<Receita>> listarReceitasFavoritasPorUsuario(String usuarioEmail);

    // Retornar lista de receitas por data de calendário e usuário (usuarioEmail)
    @Query("SELECT * FROM Receita WHERE usuarioEmail = :usuarioEmail AND calendario = :calendario")
    LiveData<List<Receita>> listarReceitasPorCalendario(String usuarioEmail, Date calendario);
}
