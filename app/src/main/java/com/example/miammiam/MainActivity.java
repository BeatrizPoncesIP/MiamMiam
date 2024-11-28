package com.example.miammiam;

import android.os.Bundle;
import android.view.Menu;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.miammiam.bd.AppDatabase;
import com.example.miammiam.bd.DatabaseSingleton;
import com.example.miammiam.bd.daos.ReceitaDao;
import com.example.miammiam.ui.receitas.viewmodel.ReceitasViewModel;
import com.example.miammiam.ui.receitas.viewmodel.ReceitasViewModelFactory;
import com.google.android.material.navigation.NavigationView;
import com.example.miammiam.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Infla o layout da activity
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.appBarMain.toolbar);

        // Obtenha a instância do banco de dados e do DAO
        AppDatabase database = DatabaseSingleton.getInstance(this);
        ReceitaDao receitaDao = database.receitaDao();

        // Crie a instância do ViewModel, passando o DAO
        ReceitasViewModel receitasViewModel = new ViewModelProvider(this, new ReceitasViewModelFactory(receitaDao)).get(ReceitasViewModel.class);

        // Definindo a navegação com o DrawerLayout
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        // Configuração do AppBarConfiguration
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_receitas, R.id.nav_favoritos, R.id.nav_agenda) // IDs dos destinos
                .setOpenableLayout(drawer)
                .build();

        // Inicializando o NavController depois que a view está totalmente carregada
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);

        // Configuração da navegação com a toolbar e o NavigationView
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}