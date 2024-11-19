package com.example.miammiam.bd.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "receitas", foreignKeys = {
        @ForeignKey(entity = Usuario.class, parentColumns = "email", childColumns = "usuario_email", onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = Categoria.class, parentColumns = "nome", childColumns = "categoria_nome", onDelete = ForeignKey.CASCADE)
})
public class Receita {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String nome;
    private String ingredientes;
    private String preparo;

    @ColumnInfo(name = "foto")
    private byte[] foto; // Foto da receita

    @ColumnInfo(name = "usuario_email")
    @NonNull
    private String usuarioEmail;

    @ColumnInfo(name = "categoria_nome")
    @NonNull
    private String categoriaNome;

    // Construtores, getters e setters
    public Receita(String nome, String ingredientes, String preparo, byte[] foto, String usuarioEmail, String categoriaNome) {
        this.nome = nome;
        this.ingredientes = ingredientes;
        this.preparo = preparo;
        this.foto = foto;
        this.usuarioEmail = usuarioEmail;
        this.categoriaNome = categoriaNome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getPreparo() {
        return preparo;
    }

    public void setPreparo(String preparo) {
        this.preparo = preparo;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getUsuarioEmail() {
        return usuarioEmail;
    }

    public void setUsuarioEmail(String usuarioEmail) {
        this.usuarioEmail = usuarioEmail;
    }

    public String getCategoriaNome() {
        return categoriaNome;
    }

    public void setCategoriaNome(String categoriaNome) {
        this.categoriaNome = categoriaNome;
    }
}
