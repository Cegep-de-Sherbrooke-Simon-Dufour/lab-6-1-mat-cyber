package com.example.lab.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.lab.R;
import com.example.lab.model.User;

public class AddUserFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        UserListViewModel viewModel = new ViewModelProvider(requireActivity()).get(UserListViewModel.class);
        Button annulerButton = view.findViewById(R.id.btn_annuler);
        Button ajouterButton = view.findViewById(R.id.btn_ajouter);

        annulerButton.setOnClickListener(v -> {
            Navigation.findNavController(view).navigateUp();
        });

        ajouterButton.setOnClickListener(v -> {
            User newUser = new User(
                    ((EditText)view.findViewById(R.id.name)).getText().toString(),
                    ((EditText)view.findViewById(R.id.email)).getText().toString());
            viewModel.addUser(newUser);
            Navigation.findNavController(view).navigateUp();
        });
    }
}