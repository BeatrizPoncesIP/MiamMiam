package com.example.miammiam.bd.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.example.miammiam.bd.entities.Usuario;

@Dao
public interface UsuarioDao {

    @Insert
    void inserirUsuario(Usuario usuario);

    @Update
    void editarUsuario(Usuario usuario);

    @Delete
    void deletarUsuario(Usuario usuario);

    // Buscar um usuário por e-mail
    @Query("SELECT * FROM Usuario WHERE email = :email")
    Usuario buscarUsuarioPorEmail(String email);
}
