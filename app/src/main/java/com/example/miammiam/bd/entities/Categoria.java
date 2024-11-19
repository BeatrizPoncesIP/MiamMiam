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

    @ColumnInfo(name = "foto")
    private byte[] foto; // Usado para armazenar a foto como um BLOB

    // Construtores, getters e setters
    public Categoria(String nome, String usuarioEmail, byte[] foto) {
        this.nome = nome;
        this.usuarioEmail = usuarioEmail;
        this.foto = foto;
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

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
}
