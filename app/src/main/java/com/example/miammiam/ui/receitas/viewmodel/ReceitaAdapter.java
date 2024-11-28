package com.example.miammiam.ui.receitas.viewmodel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.example.miammiam.bd.entities.Receita;
import com.example.miammiam.R;

public class ReceitaAdapter extends ListAdapter<Receita, ReceitaAdapter.ReceitaViewHolder> {

    protected ReceitaAdapter() {
        super(new DiffUtil.ItemCallback<Receita>() {
            @Override
            public boolean areItemsTheSame(@NonNull Receita oldItem, @NonNull Receita newItem) {
                // Checa se os itens são os mesmos com base no ID ou qualquer outra chave única
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull Receita oldItem, @NonNull Receita newItem) {
                // Checa se o conteúdo dos itens é o mesmo (verificando os campos relevantes)
                return oldItem.equals(newItem);
            }
        });
    }

    @NonNull
    @Override
    public ReceitaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Infla o layout do item da receita
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_receita, parent, false);
        return new ReceitaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ReceitaViewHolder holder, int position) {
        // Obtém a receita para o item de posição específica
        Receita receita = getItem(position);

        // Configura os dados da receita nas views do item
        holder.nomeTextView.setText(receita.getNome());
        holder.ingredientesTextView.setText(receita.getIngredientes());
        holder.preparoTextView.setText(receita.getIngredientes());
        holder.favoritoButton.setSelected(receita.isFavorito());
        // Carregar imagem (exemplo):
        // holder.imgReceita.setImageURI(receita.getFotoURI());
    }

    // ViewHolder para os itens da lista
    public static class ReceitaViewHolder extends RecyclerView.ViewHolder {
        ImageView imgReceita;
        TextView nomeTextView;
        TextView ingredientesTextView;
        TextView preparoTextView;
        View favoritoButton;

        public ReceitaViewHolder(View itemView) {
            super(itemView);
            // Inicializa as views do item
            nomeTextView = itemView.findViewById(R.id.txt_nome);
            imgReceita = itemView.findViewById(R.id.img_receita);
            favoritoButton = itemView.findViewById(R.id.btn_favorito);
            ingredientesTextView = itemView.findViewById(R.id.txt_ingredientes);
            preparoTextView = itemView.findViewById(R.id.txt_preparo);
        }
    }
}
