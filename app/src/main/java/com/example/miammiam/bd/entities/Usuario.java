package com.example.miammiam.bd.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Usuario {

    @NonNull
    @PrimaryKey
    private String email;
    @NonNull
    private String senha;
    private String nome;
    private boolean premium;
    private String fotoURI;

    public Usuario(String email, String senha, String nome) {
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.premium = false;
        this.fotoURI = null;
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

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    public String getFotoURI() {
        return fotoURI;
    }

    public void setFotoURI(String fotoURI) {
        this.fotoURI = fotoURI;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Verifica se é o mesmo objeto
        if (obj == null || getClass() != obj.getClass()) return false; // Verifica se são do mesmo tipo

        Usuario usuario = (Usuario) obj;

        // Compara os campos relevantes para determinar igualdade
        return email.equals(usuario.email) &&
                senha.equals(usuario.senha) &&
                nome.equals(usuario.nome);
    }

    @Override
    public int hashCode() {
        int result = email.hashCode(); // `email` é @NonNull, então pode chamar diretamente `hashCode`
        result = 31 * result + senha.hashCode(); // `senha` também é @NonNull
        result = 31 * result + (nome != null ? nome.hashCode() : 0); // `nome` pode ser null, então verificamos
        result = 31 * result + (premium ? 1 : 0); // `premium` é boolean, transformamos em int
        result = 31 * result + (fotoURI != null ? fotoURI.hashCode() : 0); // `fotoURI` também pode ser null
        return result;
    }
}