package com.example.miammiam.bd.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "categorias", foreignKeys = @ForeignKey(entity = Usuario.class, parentColumns = "email", childColumns = "usuario_email", onDelete = ForeignKey.CASCADE))
public class Categoria {

    @PrimaryKey
    @NonNull
    private String nome;

    @ColumnInfo(name = "usuario_email")
    @NonNull
    private String usuarioEmail;

    @ColumnInfo(name = "foto_uri")
    private String fotoUri; // URI da foto salva

    public Categoria(String nome, String usuarioEmail, String fotoUri) {
        this.nome = nome;
        this.usuarioEmail = usuarioEmail;
        this.fotoUri = fotoUri;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsuarioEmail() {
        return usuarioEmail;
    }

    public void setUsuarioEmail(String usuarioEmail) {
        this.usuarioEmail = usuarioEmail;
    }

    public String getFotoUri() {
        return fotoUri;
    }

    public void setFotoUri(String fotoUri) {
        this.fotoUri = fotoUri;
    }
}