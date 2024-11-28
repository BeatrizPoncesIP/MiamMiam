package com.example.miammiam.user;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.miammiam.MainActivity;
import com.example.miammiam.R;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.miammiam.bd.AppDatabase;
import com.example.miammiam.bd.entities.Usuario;
import com.example.miammiam.bd.DatabaseSingleton;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etSenha;
    private TextView btnNovoUsuario; // texto clicável
    private Button btnLogin;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Esconde Appbar na tela de login
        // getSupportActionBar().hide();

        etEmail = findViewById(R.id.etEmail);
        etSenha = findViewById(R.id.etSenha);
        btnLogin = findViewById(R.id.btnLogin);
        btnNovoUsuario = findViewById(R.id.btnNovoUsuario);

        // Inicializando o banco de dados usando o DatabaseSingleton
        db = DatabaseSingleton.getInstance(getApplicationContext());

        // Ação do botão de login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        // Ação do botão para adicionar novo usuário
        btnNovoUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redireciona para a tela de cadastro
                startActivity(new Intent(LoginActivity.this, CadastroActivity.class));
            }
        });
    }

    private void login() {
        String email = etEmail.getText().toString().trim();
        String senha = etSenha.getText().toString().trim();

        // Validação do e-mail
        if (email.isEmpty() || !email.contains("@")) {
            Toast.makeText(this, "E-mail inválido!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validação da senha
        if (senha.isEmpty()) {
            Toast.makeText(this, "Senha inválida!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Consulta no banco de dados
        Usuario usuario = db.usuarioDao().buscarUsuarioPorEmail(email);

        if (usuario == null) {
            Toast.makeText(this, "E-mail não cadastrado!", Toast.LENGTH_SHORT).show();
        } else {
            if (usuario.getSenha().equals(senha)) {
                // Salvar o e-mail no SharedPreferences
                getSharedPreferences("AppPrefs", MODE_PRIVATE)
                        .edit()
                        .putString("user_email", email)
                        .apply();

                // Login bem-sucedido, redireciona para a próxima tela
                Toast.makeText(this, "Login bem-sucedido!", Toast.LENGTH_SHORT).show();

                // Redirecionar para a tela principal
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            } else {
                // Senha incorreta
                Toast.makeText(this, "Senha inválida!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}