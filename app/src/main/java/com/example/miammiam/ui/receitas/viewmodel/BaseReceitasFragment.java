package com.example.miammiam.ui.receitas.viewmodel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miammiam.R;
import com.example.miammiam.bd.entities.Receita;
import com.example.miammiam.databinding.FragmentReceitasBinding;

import java.util.List;

public abstract class BaseReceitasFragment extends Fragment {

    private FragmentReceitasBinding binding;
    private ReceitaAdapter receitaAdapter;

    @NonNull
    protected abstract LiveData<List<Receita>> getReceitasLiveData(); // Método abstrato para fornecer as receitas filtradas

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentReceitasBinding.inflate(inflater, container, false);

        // Configura o botão flutuante para navegar para CriarReceitaFragment
        binding.btnAdicionar.setOnClickListener(view ->
                Navigation.findNavController(view)
                        .navigate(R.id.action_nav_receitas_to_criarReceitaFragment)
        );

        setupRecyclerView();

        // Observar os dados da lista de receitas
        getReceitasLiveData().observe(getViewLifecycleOwner(), receitas -> receitaAdapter.submitList(receitas));

        return binding.getRoot();
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = binding.recyclerReceitas;
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        receitaAdapter = new ReceitaAdapter();
        recyclerView.setAdapter(receitaAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
