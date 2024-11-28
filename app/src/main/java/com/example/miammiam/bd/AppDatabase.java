package com.example.miammiam.bd;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import com.example.miammiam.bd.entities.Receita;
import com.example.miammiam.bd.entities.Usuario;
import com.example.miammiam.bd.daos.ReceitaDao;
import com.example.miammiam.bd.daos.UsuarioDao;

@Database(entities = {Usuario.class, Receita.class}, version = 1)
@TypeConverters(Converters.class)  // Registra o TypeConverter
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    // Métodos abstratos para os DAOs
    public abstract UsuarioDao usuarioDao();
    public abstract ReceitaDao receitaDao();

    // Método para obter a instância do banco de dados
    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "app_database")
                    .fallbackToDestructiveMigration()  // Usado para reconstruir o banco caso haja mudanças na versão
                    .build();
        }
        return instance;
    }
}
