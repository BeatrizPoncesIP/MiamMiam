package com.example.miammiam.ui.receitas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import com.example.miammiam.bd.DatabaseSingleton;
import com.example.miammiam.bd.daos.ReceitaDao;
import com.example.miammiam.bd.entities.Receita;
import com.example.miammiam.ui.receitas.viewmodel.BaseReceitasFragment;
import com.example.miammiam.ui.receitas.viewmodel.ReceitasViewModel;
import com.example.miammiam.ui.receitas.viewmodel.ReceitasViewModelFactory;
import java.util.List;
import android.os.Bundle;

public class ReceitasFragment extends BaseReceitasFragment {

    private ReceitasViewModel receitasViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Obtenha o DAO diretamente do singleton
        ReceitaDao receitaDao = DatabaseSingleton.getInstance(getContext()).receitaDao();

        // Crie o ViewModel passando o DAO
        receitasViewModel = new ViewModelProvider(this, new ReceitasViewModelFactory(receitaDao)).get(ReceitasViewModel.class);
    }

    @Override
    protected LiveData<List<Receita>> getReceitasLiveData() {
        return receitasViewModel.getReceitas(); // Todas as receitas
    }
}