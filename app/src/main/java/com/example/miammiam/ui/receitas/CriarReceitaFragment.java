package com.example.miammiam.ui.receitas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import com.example.miammiam.R;
import com.example.miammiam.bd.AppDatabase;
import com.example.miammiam.bd.entities.Receita;
import com.example.miammiam.databinding.FragmentCriarReceitaBinding;

public class CriarReceitaFragment extends Fragment {

    private FragmentCriarReceitaBinding binding;
    private AppDatabase db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCriarReceitaBinding.inflate(inflater, container, false);
        db = AppDatabase.getInstance(requireContext()); // Instância do banco de dados

        // Configura o botão para salvar a nova receita
        binding.btnSalvar.setOnClickListener(view -> salvarReceita());

        return binding.getRoot();
    }

    private void salvarReceita() {
        String nome = binding.etNome.getText().toString().trim();
        String ingredientes = binding.etIngredientes.getText().toString().trim();
        String preparo = binding.etPreparo.getText().toString().trim();

        if (nome.isEmpty() || ingredientes.isEmpty() || preparo.isEmpty()) {
            Toast.makeText(getContext(), "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Cria a nova receita
        Receita novaReceita = new Receita(nome, ingredientes, preparo, null, "usuario@example.com");

        // Salva no banco de dados
        new Thread(() -> {
            db.receitaDao().inserirReceita(novaReceita);
            requireActivity().runOnUiThread(() -> {
                Toast.makeText(getContext(), "Receita salva com sucesso!", Toast.LENGTH_SHORT).show();

                // Navega de volta para ReceitasFragment
                Navigation.findNavController(binding.getRoot())
                        .navigate(R.id.action_criarReceitaFragment_to_nav_receitas);
            });
        }).start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}