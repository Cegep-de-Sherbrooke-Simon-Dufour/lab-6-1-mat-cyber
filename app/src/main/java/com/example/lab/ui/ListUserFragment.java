package com.example.lab.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lab.R;
import com.example.lab.RecyclerCallback;
import com.example.lab.model.User;

import java.util.ArrayList;
import java.util.List;

public class ListUserFragment extends Fragment {

    UserAdapter userAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.add_btn).setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_listUserFragment_to_addUserFragment);
        });

        UserListViewModel viewModel = new ViewModelProvider(requireActivity()).get(UserListViewModel.class);
        RecyclerView recyclerView = view.findViewById(R.id.users_list);

        userAdapter = new UserAdapter(new RecyclerCallback<User>() {
            @Override
            public void returnValue(User object) {
                Bundle bundle = new Bundle();
                bundle.putInt("userId", object.getUid());
                bundle.putString("username", object.getName());
                bundle.putString("userEmail", object.getEmail());
                if(object.getUri() != null) bundle.putString("image", object.getUri());
                Navigation.findNavController(view).navigate(R.id.action_listUserFragment_to_rentalItemListFragment, bundle);
            }
        });

        recyclerView.setAdapter(userAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        viewModel.getUsers().observe(getViewLifecycleOwner(), new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                userAdapter.submitList(new ArrayList<User>(users));
            }
        });
    }
}