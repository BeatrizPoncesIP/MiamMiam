package com.example.miammiam.bd.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.example.miammiam.bd.entities.Receita;
import java.util.List;

@Dao
public interface ReceitaDao {
    @Insert
    void insert(Receita receita);

    @Update
    void updateReceita(Receita receita); // Alterar receita (nome, ingredientes, preparo, foto)

    @Delete
    void delete(Receita receita);

    @Query("SELECT * FROM receitas WHERE usuario_email = :usuarioEmail")
    List<Receita> getReceitasByUsuario(String usuarioEmail); // Obter todas as receitas de um usuário

    @Query("SELECT * FROM receitas WHERE categoria_nome = :categoriaNome AND usuario_email = :usuarioEmail")
    List<Receita> getReceitasByCategoria(String categoriaNome, String usuarioEmail); // Obter todas as receitas de uma categoria de um usuário

    @Query("SELECT * FROM receitas WHERE id = :id LIMIT 1")
    Receita getReceitaById(int id); // Obter receita pelo id
}
