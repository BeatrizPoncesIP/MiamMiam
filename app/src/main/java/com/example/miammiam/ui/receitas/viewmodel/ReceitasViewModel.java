package com.example.miammiam.ui.receitas.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.example.miammiam.bd.daos.ReceitaDao;
import com.example.miammiam.bd.entities.Receita;
import java.util.List;

public class ReceitasViewModel extends ViewModel {

    private final ReceitaDao receitaDao;

    // Construtor da ViewModel recebe o ReceitaDao
    public ReceitasViewModel(ReceitaDao receitaDao) {
        this.receitaDao = receitaDao;
    }

    // Método que retorna o LiveData diretamente do DAO
    public LiveData<List<Receita>> getReceitas() {
        return receitaDao.listarReceitasPorUsuario("usuario@exemplo.com"); // Exemplo de usuário
    }

    // Método que retorna o LiveData diretamente do DAO para receitas favoritas
    public LiveData<List<Receita>> getReceitasFavoritas() {
        return receitaDao.listarReceitasFavoritasPorUsuario("usuario@exemplo.com"); // Exemplo de usuário
    }
}