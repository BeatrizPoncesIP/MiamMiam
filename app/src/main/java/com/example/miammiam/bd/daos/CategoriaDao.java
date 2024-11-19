package com.example.miammiam.bd.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.example.miammiam.bd.entities.Categoria;
import java.util.List;

@Dao
public interface CategoriaDao {
    @Insert
    void insert(Categoria categoria);

    @Update
    void updateFoto(Categoria categoria); // Alterar a foto de uma categoria

    @Delete
    void delete(Categoria categoria);

    @Query("SELECT * FROM categorias WHERE usuario_email = :usuarioEmail")
    List<Categoria> getCategoriasByUsuario(String usuarioEmail); // Obter todas as categorias de um usuário

    @Query("SELECT * FROM categorias WHERE nome = :nome LIMIT 1")
    Categoria getCategoriaByNome(String nome);
}
