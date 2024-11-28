package com.example.miammiam.ui.categorias;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import com.example.miammiam.R;
import com.example.miammiam.bd.AppDatabase;
import com.example.miammiam.bd.daos.CategoriaDao;
import com.example.miammiam.bd.entities.Categoria;
import java.io.File;
import androidx.annotation.NonNull;
import androidx.navigation.fragment.NavHostFragment;

/**
 * Fragmento que permite ao usuário criar uma nova categoria,
 * tirando uma foto e salvando a URI no banco de dados.
 */
public class CriarCategoriaFragment extends Fragment {

    private ImageButton imgCategoria;
    private EditText edtNomeCategoria;
    private CategoriaDao categoriaDao;
    private Uri imageUri; // URI da imagem capturada

    // Definir o código de solicitação para a permissão de câmera
    private static final int CAMERA_REQUEST_CODE = 100;
    private ActivityResultLauncher<Uri> takePictureLauncher;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inicializar o launcher para capturar a imagem
        takePictureLauncher = registerForActivityResult(
                new ActivityResultContracts.TakePicture(),
                result -> {
                    if (result) {
                        Toast.makeText(requireContext(), "Imagem salva em: " + imageUri, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(requireContext(), "Captura cancelada", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_criar_categoria, container, false);

        imgCategoria = view.findViewById(R.id.img_add_categoria);
        edtNomeCategoria = view.findViewById(R.id.edt_nome_categoria);
        categoriaDao = AppDatabase.getDatabase(requireContext()).categoriaDao();

        imgCategoria.setOnClickListener(v -> checkPermissionsAndCameraAvailability());

        // Salvar categoria ao clicar no botão de salvar
        view.findViewById(R.id.btn_salvar_categoria).setOnClickListener(v -> {
            String nome = edtNomeCategoria.getText().toString().trim();
            String userEmail = getActivity().getSharedPreferences("AppPrefs", Context.MODE_PRIVATE).getString("user_email", "");

            if (nome.isEmpty()) {
                Toast.makeText(requireContext(), "O nome da categoria não pode estar vazio.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (imageUri == null) {
                Toast.makeText(requireContext(), "Você precisa tirar uma foto para a categoria.", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                Categoria novaCategoria = new Categoria(nome, userEmail, imageUri.toString());
                if (categoriaDao.getCategoriaByNomeAndUsuario(nome, userEmail) != null) {
                    Toast.makeText(requireContext(), "Categoria já existe.", Toast.LENGTH_SHORT).show();
                } else {
                    categoriaDao.insert(novaCategoria);
                    Toast.makeText(requireContext(), "Categoria salva com sucesso!", Toast.LENGTH_SHORT).show();
                    edtNomeCategoria.setText("");
                    imageUri = null; // Limpar URI após salvar
                }
            } catch (Exception e) {
                Toast.makeText(requireContext(), "Erro ao salvar categoria.", Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Ativa a seta de voltar no ActionBar
        if (requireActivity() instanceof AppCompatActivity) {
            AppCompatActivity activity = (AppCompatActivity) requireActivity();
            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Configura o clique na seta para voltar ao primeiro fragment
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                NavHostFragment.findNavController(CriarCategoriaFragment.this).navigateUp();
            }
        });
    }

    /**
     * Verifica as permissões de câmera e armazenamento, e inicia a captura de imagem se as permissões forem concedidas.
     */
    private void checkPermissionsAndCameraAvailability() {
        if (requireActivity().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)) {
            // Verifica as permissões para armazenamento externo e câmera antes de iniciar a captura da imagem
            if (ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(requireActivity(),
                        new String[]{android.Manifest.permission.CAMERA},
                        CAMERA_REQUEST_CODE);
            } else {
                captureImage();
            }
        } else {
            Toast.makeText(requireContext(), "Câmera não disponível neste dispositivo", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == CAMERA_REQUEST_CODE) {
            boolean allPermissionsGranted = true;
            for (int result : grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    allPermissionsGranted = false;
                    break;
                }
            }

            if (allPermissionsGranted) {
                captureImage();
            } else {
                Toast.makeText(requireContext(), "Permissões necessárias não concedidas", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * Inicia a captura da imagem, criando um arquivo para armazená-la.
     */
    private void captureImage() {
        // Criar um arquivo para salvar a imagem capturada
        File imageFile = new File(requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES), "image_" + System.currentTimeMillis() + ".jpg");

        // Verifica se o arquivo foi criado corretamente
        if (!imageFile.exists()) {
            if (imageFile.mkdirs()) {
                Toast.makeText(requireContext(), "Diretório criado com sucesso", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(requireContext(), "Erro ao criar diretório", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        // Obter a URI do arquivo para uso no FileProvider
        imageUri = FileProvider.getUriForFile(requireContext(), requireContext().getPackageName() + ".fileprovider", imageFile);

        // Iniciar a captura da imagem
        takePictureLauncher.launch(imageUri);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (requireActivity() instanceof AppCompatActivity) {
            AppCompatActivity activity = (AppCompatActivity) requireActivity();
            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
    }
}