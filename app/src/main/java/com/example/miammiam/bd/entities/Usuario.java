package com.example.miammiam.bd.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "usuarios")
public class Usuario {

    @PrimaryKey
    @NonNull
    private String email;

    @NonNull
    private String senha;

    @NonNull
    private String nome;

    @NonNull
    private Boolean premium;

    // Construtores, getters e setters
    public Usuario(String email, String senha, String nome, Boolean premium) {
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.premium = premium;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getPremium() {
        return premium;
    }

    public void setPremium(Boolean premium) {
        this.premium = premium;
    }
}
