package com.example.miammiam.ui.agenda;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.miammiam.bd.AppDatabase;
import com.example.miammiam.bd.entities.Receita;
import androidx.annotation.NonNull;
import java.sql.Date;
import java.util.List;

public class AgendaViewModel extends AndroidViewModel {

    private final AppDatabase database;

    public AgendaViewModel(@NonNull Application application) {
        super(application);
        database = AppDatabase.getInstance(application);
    }

    // Retorna diretamente o LiveData do DAO, que já fornece os dados de forma reativa
    public LiveData<List<Receita>> getReceitasPorData(Date data) {
        // Utiliza o método do DAO para buscar as receitas com a data selecionada
        return database.receitaDao().listarReceitasPorCalendario("usuario_email_aqui", data);
    }
}