package com.example.lab;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.UserHandle;
import android.view.View;
import android.widget.Button;

import com.example.lab.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayList<User> users;
    private UserAdapter userAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        users = new ArrayList<User>( Arrays.asList(
                new User("Mathieu", "blabla@gmail.com"),
                new User("Bob", "bob@bob.com"),
                new User("Alice", "alice@alice")
        ));
        RecyclerView recyclerView = findViewById(R.id.users_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        userAdapter = new UserAdapter(new RecyclerCallback<User>() {
            @Override
            public void returnValue(User object) {
                users.remove(object);
                userAdapter.submitList(new ArrayList<User>(users));
            }
        });
        userAdapter.submitList(new ArrayList<User>(users));
        recyclerView.setAdapter(userAdapter);


        FloatingActionButton addBtn = findViewById(R.id.add_btn);
        addBtn.setOnClickListener(btnOnClickListener);

    }

    View.OnClickListener btnOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, add_user.class);
            mGetNewUser.launch(intent);
        }
    };

    ActivityResultLauncher<Intent> mGetNewUser = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() != 0){
                        Intent data = result.getData();
                        User newUser = new User(data.getStringExtra("name"), data.getStringExtra("email"));
                        users.add(newUser);
                        userAdapter.submitList(new ArrayList<User>(users));
                    }
                }
            }
    );
}