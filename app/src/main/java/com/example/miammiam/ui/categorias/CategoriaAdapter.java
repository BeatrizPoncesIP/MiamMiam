package com.example.miammiam.ui.categorias;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import com.example.miammiam.R;
import com.example.miammiam.bd.entities.Categoria;
import java.util.List;
import com.bumptech.glide.Glide;

/**
 * Classe adaptadora para exibir categorias em um RecyclerView.
 * Esta classe gerencia a criação e vinculação de views para uma lista de categorias.
 */
public class CategoriaAdapter extends RecyclerView.Adapter<CategoriaAdapter.CategoriaViewHolder> {

    // Lista de categorias a ser exibida.
    private final List<Categoria> categorias;

    /**
     * Construtor da classe.
     *
     * @param categorias Lista de categorias que será exibida no RecyclerView.
     */
    public CategoriaAdapter(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    /**
     * Cria uma nova ViewHolder para cada item do RecyclerView.
     *
     * @param parent   O ViewGroup ao qual a nova View será anexada.
     * @param viewType O tipo de view (não utilizado aqui).
     * @return Uma nova instância de CategoriaViewHolder.
     */
    @NonNull
    @Override
    public CategoriaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Infla o layout para um item de categoria.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_categoria, parent, false);
        return new CategoriaViewHolder(view);
    }

    /**
     * Vincula os dados de uma categoria a uma ViewHolder específica.
     *
     * @param holder   O ViewHolder que será atualizado.
     * @param position A posição do item na lista.
     */
    // CategoriaAdapter ajustado para evitar erros no carregamento de imagens
    @Override
    public void onBindViewHolder(@NonNull CategoriaViewHolder holder, int position) {
        if (position < categorias.size()) {
            Categoria categoria = categorias.get(position);
            holder.txtNome.setText(categoria.getNome());

            if (categoria.getFotoUri() != null && !categoria.getFotoUri().isEmpty()) {
                Uri fotoUri = Uri.parse(categoria.getFotoUri());
                Glide.with(holder.imgCategoria.getContext())
                        .load(fotoUri)
                        .placeholder(R.drawable.ic_add_foto) // Imagem temporária enquanto carrega
                        .error(R.drawable.ic_erro) // Imagem de erro se falhar
                        .into(holder.imgCategoria);
            } else {
                holder.imgCategoria.setImageResource(R.drawable.ic_add_foto);
            }
        } else {
            holder.txtNome.setText("Adicionar");
            holder.imgCategoria.setImageResource(R.drawable.button);
            holder.imgCategoria.setOnClickListener(v ->
                    Navigation.findNavController(v).navigate(R.id.action_categorias_to_criar_categoria));
        }
    }

    /**
     * Retorna o número total de itens no RecyclerView, incluindo o botão "Adicionar".
     *
     * @return O número de categorias mais um (para o botão "Adicionar").
     */
    @Override
    public int getItemCount() {
        return categorias.size() + 1;
    }

    /**
     * Classe ViewHolder para gerenciar as views de cada item de categoria.
     */
    static class CategoriaViewHolder extends RecyclerView.ViewHolder {
        // Referências para as views do layout de item.
        final ImageView imgCategoria;
        final TextView txtNome;

        /**
         * Construtor da ViewHolder.
         *
         * @param itemView A view que representa um item no RecyclerView.
         */
        CategoriaViewHolder(View itemView) {
            super(itemView);
            imgCategoria = itemView.findViewById(R.id.img_categoria);
            txtNome = itemView.findViewById(R.id.txt_nome_categoria);
        }
    }
}