package com.example.miammiam.ui.receitas.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.example.miammiam.bd.daos.ReceitaDao;

public class ReceitasViewModelFactory implements ViewModelProvider.Factory {

    private final ReceitaDao receitaDao;

    public ReceitasViewModelFactory(ReceitaDao receitaDao) {
        this.receitaDao = receitaDao;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ReceitasViewModel.class)) {
            return (T) new ReceitasViewModel(receitaDao);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}