package com.example.miammiam.bd.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import java.sql.Date;

@Entity(
        foreignKeys = @ForeignKey(
                entity = Usuario.class,          // Entidade relacionada
                parentColumns = "email",         // Coluna na tabela `Usuario` (chave primária)
                childColumns = "usuarioEmail",   // Coluna na tabela `Receita` (chave estrangeira)
                onDelete = ForeignKey.CASCADE    // Deletar receitas quando o usuário for excluído
        )
)
public class Receita {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @NonNull
    private String nome;
    private String ingredientes;
    private String preparo;
    private String fotoURI;
    private boolean favorito;
    @NonNull
    private String usuarioEmail;
    private Date calendario;

    public Receita(String nome, String ingredientes, String preparo, String fotoURI, String usuarioEmail) {
        this.nome = nome;
        this.ingredientes = ingredientes;
        this.preparo = preparo;
        this.fotoURI = fotoURI;
        this.favorito = false;
        this.usuarioEmail = usuarioEmail;
        this.calendario = null;
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

    public String getFotoURI() {
        return fotoURI;
    }

    public void setFotoURI(String fotoURI) {
        this.fotoURI = fotoURI;
    }

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }

    public String getUsuarioEmail() {
        return usuarioEmail;
    }

    public void setUsuarioEmail(String usuarioEmail) {
        this.usuarioEmail = usuarioEmail;
    }

    public Date getCalendario() {
        return calendario;
    }

    public void setCalendario(Date calendario) {
        this.calendario = calendario;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Verifica se é o mesmo objeto
        if (obj == null || getClass() != obj.getClass()) return false; // Verifica se são do mesmo tipo

        Receita receita = (Receita) obj;

        // Compara os campos relevantes para determinar igualdade
        return id == receita.id &&
                favorito == receita.favorito &&
                nome.equals(receita.nome) &&
                ingredientes.equals(receita.ingredientes) &&
                preparo.equals(receita.preparo) &&
                (fotoURI != null ? fotoURI.equals(receita.fotoURI) : receita.fotoURI == null) &&
                usuarioEmail.equals(receita.usuarioEmail) &&
                (calendario != null ? calendario.equals(receita.calendario) : receita.calendario == null);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (ingredientes != null ? ingredientes.hashCode() : 0);
        result = 31 * result + (preparo != null ? preparo.hashCode() : 0);
        result = 31 * result + (fotoURI != null ? fotoURI.hashCode() : 0);
        result = 31 * result + (favorito ? 1 : 0);
        result = 31 * result + (usuarioEmail != null ? usuarioEmail.hashCode() : 0);
        result = 31 * result + (calendario != null ? calendario.hashCode() : 0);
        return result;
    }

}