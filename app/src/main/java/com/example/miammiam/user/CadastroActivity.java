package com.example.miammiam.user;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.miammiam.R;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import com.example.miammiam.bd.AppDatabase;
import com.example.miammiam.bd.entities.Usuario;
import com.example.miammiam.bd.DatabaseSingleton;

public class CadastroActivity extends AppCompatActivity {

    private EditText etNome, etEmail, etSenha;
    private Button btnCadastrar;
    private AppDatabase db;
    private ImageButton btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        // Inicializando as views
        etNome = findViewById(R.id.etNome);
        etEmail = findViewById(R.id.etEmail);
        etSenha = findViewById(R.id.etSenha);
        btnCadastrar = findViewById(R.id.btnCadastrar);
        btnVoltar = findViewById(R.id.btnVoltar);

        // Inicializando o banco de dados usando o DatabaseSingleton
        db = DatabaseSingleton.getInstance(getApplicationContext());

        // Ação do botão de cadastro
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrarUsuario();
            }
        });

        // Voltar para tela login
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Fecha a tela atual e volta para a anterior.
            }
        });
    }

    private void cadastrarUsuario() {
        String nome = etNome.getText().toString().trim();
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

        // Verifica se o e-mail já está cadastrado
        Usuario usuarioExistente = db.usuarioDao().buscarUsuarioPorEmail(email);
        if (usuarioExistente != null) {
            Toast.makeText(this, "E-mail já cadastrado!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Criação do novo usuário
        Usuario novoUsuario = new Usuario(email, senha, nome);
        db.usuarioDao().inserirUsuario(novoUsuario);

        Toast.makeText(this, "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
        finish();
    }
}