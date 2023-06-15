package com.example.lab.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.lab.R;
import com.example.lab.RecyclerCallback;
import com.example.lab.model.RentalItem;

public class RentalItemListFragment extends Fragment {

    public RentalItemListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rental_item_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int userId = getArguments().getInt("userId");
        String username = getArguments().getString("username");
        String userEmail = getArguments().getString("userEmail");

        UserListViewModel viewModel = new ViewModelProvider(requireActivity()).get(UserListViewModel.class);
        RentalItemListViewModel rentalListViewModel = new ViewModelProvider(requireActivity()).get(RentalItemListViewModel.class);

        ((TextView)view.findViewById(R.id.profile_name)).setText(username);
        ((TextView)view.findViewById(R.id.profile_email)).setText(userEmail);

        view.findViewById(R.id.profile_back).setOnClickListener(v -> {
            Navigation.findNavController(view).navigateUp();
        });

        view.findViewById(R.id.profile_delete).setOnClickListener(v -> {
            viewModel.removeUserById(userId);
            Navigation.findNavController(view).navigateUp();
        });

        view.findViewById(R.id.profile_add).setOnClickListener(v -> {
            rentalListViewModel.addRentalItem(new RentalItem(((TextView)view.findViewById(R.id.profile_item_add)).getText().toString(), userId));
        });

        RecyclerView recyclerView = view.findViewById(R.id.rental_list);

        RentalItemAdapter rentalItemAdapter = new RentalItemAdapter(new RecyclerCallback<RentalItem>() {
            @Override
            public void returnValue(RentalItem object) {
                rentalListViewModel.removeRentalItem(object);
            }
        });

        recyclerView.setAdapter(rentalItemAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
    }
}