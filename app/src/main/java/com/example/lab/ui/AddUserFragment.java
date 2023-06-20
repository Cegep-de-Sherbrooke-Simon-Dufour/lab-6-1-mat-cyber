package com.example.lab.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lab.R;
import com.example.lab.model.User;

import java.io.File;
import java.util.UUID;

public class AddUserFragment extends Fragment {

    private Uri uri;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        UserListViewModel viewModel = new ViewModelProvider(requireActivity()).get(UserListViewModel.class);
        Button camera = view.findViewById(R.id.camera);
        Button gallery = view.findViewById(R.id.gallery);
        Button annulerButton = view.findViewById(R.id.btn_annuler);
        Button ajouterButton = view.findViewById(R.id.btn_ajouter);
        ImageView imageView = view.findViewById(R.id.preview_image);

        ActivityResultLauncher<Uri> cameraResultLauncher = registerForActivityResult(
                new ActivityResultContracts.TakePicture(),
                pictureTaken -> {
                    if(pictureTaken){
                        imageView.setImageURI(uri);
                    }
                }
        );

        ActivityResultLauncher<PickVisualMediaRequest> galleryResultLauncher =
                registerForActivityResult(
                        new ActivityResultContracts.PickVisualMedia(),
                        uri -> {
                            if(uri != null){
                                int flags = Intent.FLAG_GRANT_READ_URI_PERMISSION;
                                requireContext().getContentResolver().takePersistableUriPermission(uri, flags);
                                imageView.setImageURI(uri);
                                this.uri = uri;
                            }
                        }
                );

        annulerButton.setOnClickListener(v -> {
            Navigation.findNavController(view).navigateUp();
        });

        ajouterButton.setOnClickListener(v -> {
            if(uri == null){
                Toast.makeText(getContext(), "An image is mandatory", Toast.LENGTH_SHORT).show();
                return;
            }
            viewModel.addUser(
                    ((EditText)view.findViewById(R.id.name)).getText().toString(),
                    ((EditText)view.findViewById(R.id.email)).getText().toString(),
                    uri.toString());
            Navigation.findNavController(view).navigateUp();
        });

        camera.setOnClickListener(v -> {
            File folder = new File(requireContext().getFilesDir(), "camera_images");
            if(!folder.exists()) folder.mkdirs();
            File file = new File(folder, UUID.randomUUID().toString() + ".png");
            uri = FileProvider.getUriForFile(
                    requireContext(),
                    requireContext().getPackageName() + ".provider",
                    file
            );
            cameraResultLauncher.launch(uri);
        });

        gallery.setOnClickListener(v -> {
            galleryResultLauncher.launch(new PickVisualMediaRequest.Builder()
                    .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE)
                    .build());
        });
    }
}