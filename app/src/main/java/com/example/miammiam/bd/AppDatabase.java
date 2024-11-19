package com.example.miammiam.bd;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.miammiam.bd.daos.CategoriaDao;
import com.example.miammiam.bd.daos.ReceitaDao;
import com.example.miammiam.bd.daos.UsuarioDao;
import com.example.miammiam.bd.entities.Categoria;
import com.example.miammiam.bd.entities.Receita;
import com.example.miammiam.bd.entities.Usuario;

@Database(entities = {Usuario.class, Categoria.class, Receita.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;

    // Método para obter a instância do banco de dados
    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "ControleDeUsuarios")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

    // Métodos para acessar os DAOs
    public abstract UsuarioDao usuarioDao();
    public abstract CategoriaDao categoriaDao();
    public abstract ReceitaDao receitaDao();
}

