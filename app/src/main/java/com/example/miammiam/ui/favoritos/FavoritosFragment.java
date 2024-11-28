package com.example.miammiam.ui.favoritos;

import android.os.Bundle;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import com.example.miammiam.bd.entities.Receita;
import com.example.miammiam.ui.receitas.viewmodel.BaseReceitasFragment;
import com.example.miammiam.ui.receitas.viewmodel.ReceitasViewModel;
import java.util.List;

public class FavoritosFragment extends BaseReceitasFragment {

    private ReceitasViewModel receitasViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        receitasViewModel = new ViewModelProvider(this).get(ReceitasViewModel.class);
    }

    @Override
    protected LiveData<List<Receita>> getReceitasLiveData() {
        return receitasViewModel.getReceitasFavoritas(); // Apenas receitas favoritas
    }
}