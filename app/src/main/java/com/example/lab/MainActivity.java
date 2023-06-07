package com.example.lab;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    UserAdapter userAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UserListViewModel viewModel = new ViewModelProvider(this).get(UserListViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.users_list);
        FloatingActionButton addBtn = findViewById(R.id.add_btn);

        userAdapter = new UserAdapter(new RecyclerCallback<User>() {
            @Override
            public void returnValue(User object) {
                viewModel.removeUser(object);
            }
        });
        recyclerView.setAdapter(userAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewModel.getUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                userAdapter.submitList(new ArrayList<User>(users));
            }
        });

        ActivityResultLauncher<Intent> getUser = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() != 0){
                            Intent data = result.getData();
                            viewModel.addUser(new User(data.getStringExtra("name"), data.getStringExtra("email")));
                        }
                    }
                }
        );


        addBtn.setOnClickListener(v -> {
            getUser.launch(new Intent(this, AddUserActivity.class));
        });

    }
}