package com.example.miammiam.ui.agenda;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.miammiam.bd.entities.Receita;
import com.example.miammiam.databinding.FragmentAgendaBinding;
import java.sql.Date;
import java.util.List;

public class AgendaFragment extends Fragment {

    private FragmentAgendaBinding binding;
    private AgendaViewModel agendaViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAgendaBinding.inflate(inflater, container, false);
        agendaViewModel = new ViewModelProvider(this).get(AgendaViewModel.class);

        // Configurar o CalendarView
        binding.calendario.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            // Corrigir a criação do objeto Date (ano precisa ser ajustado para 1900)
            Date selectedDate = new Date(year - 1900, month, dayOfMonth);

            // Atualizar as receitas para a data selecionada
            agendaViewModel.getReceitasPorData(selectedDate).observe(getViewLifecycleOwner(), receitas -> {
                if (receitas != null && receitas.isEmpty()) {
                    // Exibir mensagem de "nenhuma receita"
                    Toast.makeText(getContext(), "Nenhuma receita para esta data.", Toast.LENGTH_SHORT).show();
                } else if (receitas != null) {
                    // Exibir as receitas (exemplo usando Toast ou RecyclerView se necessário)
                    StringBuilder receitasStr = new StringBuilder("Receitas:\n");
                    for (Receita receita : receitas) {
                        receitasStr.append("- ").append(receita.getNome()).append("\n");
                    }
                    Toast.makeText(getContext(), receitasStr.toString(), Toast.LENGTH_LONG).show();
                }
            });
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;  // Limpeza da binding
    }
}