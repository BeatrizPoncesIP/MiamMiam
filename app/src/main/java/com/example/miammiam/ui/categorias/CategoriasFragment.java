package com.example.miammiam.ui.categorias;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.miammiam.R;
import com.example.miammiam.bd.AppDatabase;
import com.example.miammiam.bd.daos.CategoriaDao;
import com.example.miammiam.databinding.FragmentCategoriasBinding;
import com.example.miammiam.bd.entities.Categoria;

import java.util.List;

/**
 * Classe responsável por exibir a lista de categorias associadas a um usuário.
 * Utiliza um RecyclerView para mostrar as categorias em uma grade e permite que
 * sejam filtradas com base no usuário atualmente autenticado.
 */
public class CategoriasFragment extends Fragment {

    // Vinculação automática para acessar os elementos da interface.
    private FragmentCategoriasBinding binding;

    // Adapter para gerenciar e exibir as categorias no RecyclerView.
    private CategoriaAdapter adapter;

    // DAO para acesso ao banco de dados relacionado às categorias.
    private CategoriaDao categoriaDao;

    /**
     * Cria a visualização do fragmento e configura os componentes principais.
     *
     * @param inflater  Objeto usado para inflar o layout do fragmento.
     * @param container Contêiner onde o fragmento será inserido.
     * @param savedInstanceState Estado salvo da instância anterior, se houver.
     * @return A visualização configurada para o fragmento.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categorias, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_categorias);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        categoriaDao = AppDatabase.getDatabase(getContext()).categoriaDao();
        String userEmail = getActivity()
                .getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
                .getString("user_email", "");

        try {
            if (!userEmail.isEmpty()) {
                List<Categoria> categorias = categoriaDao.getCategoriasByUsuario(userEmail);
                if (categorias.isEmpty()) {
                    Toast.makeText(getContext(), "Nenhuma categoria encontrada.", Toast.LENGTH_SHORT).show();
                }
                adapter = new CategoriaAdapter(categorias);
                recyclerView.setAdapter(adapter);
            } else {
                Toast.makeText(getContext(), "Usuário não autenticado.", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(getContext(), "Erro ao carregar categorias.", Toast.LENGTH_SHORT).show();
        }

        return view;
    }

    /**
     * Chamado quando a visualização do fragmento é destruída.
     * Libera a referência do binding para evitar vazamentos de memória.
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // Libera a vinculação.
    }
}