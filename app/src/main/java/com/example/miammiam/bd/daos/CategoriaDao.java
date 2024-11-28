package com.example.miammiam.bd.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.example.miammiam.bd.entities.Categoria;
import java.util.List;

/**
 * Interface DAO para manipulação da tabela de categorias no banco de dados.
 * Permite inserir, atualizar, excluir e consultar categorias.
 */
@Dao
public interface CategoriaDao {

    /**
     * Insere uma nova categoria no banco de dados.
     *
     * @param categoria A categoria a ser inserida.
     */
    @Insert
    void insert(Categoria categoria);

    /**
     * Atualiza a foto (URI) de uma categoria existente.
     *
     * @param categoria A categoria com a nova foto URI.
     */
    @Update
    void updateFoto(Categoria categoria);

    /**
     * Exclui uma categoria do banco de dados.
     *
     * @param categoria A categoria a ser excluída.
     */
    @Delete
    void delete(Categoria categoria);

    /**
     * Recupera todas as categorias de um usuário específico.
     *
     * @param usuarioEmail O email do usuário.
     * @return Lista de categorias do usuário.
     */
    @Query("SELECT * FROM categorias WHERE usuario_email = :usuarioEmail")
    List<Categoria> getCategoriasByUsuario(String usuarioEmail);

    /**
     * Recupera uma categoria pelo nome.
     *
     * @param nome O nome da categoria.
     * @return A categoria com o nome especificado.
     */
    @Query("SELECT * FROM categorias WHERE nome = :nome LIMIT 1")
    Categoria getCategoriaByNome(String nome);

    /**
     * Recupera uma categoria pelo nome e email do usuário.
     *
     * @param nome O nome da categoria.
     * @param usuarioEmail O email do usuário.
     * @return A categoria correspondente ao nome e usuário especificados.
     */
    @Query("SELECT * FROM categorias WHERE nome = :nome AND usuario_email = :usuarioEmail LIMIT 1")
    Categoria getCategoriaByNomeAndUsuario(String nome, String usuarioEmail);

}