package com.example.lab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AddUserActivity extends AppCompatActivity {

    TextView name, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        Button ajouterBtn = findViewById(R.id.btn_ajouter);
        Button annulerBtn = findViewById(R.id.btn_annuler);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);

        ajouterBtn.setOnClickListener(btnOnClickListener);
        annulerBtn.setOnClickListener(btnOnClickListener);
    }

    View.OnClickListener btnOnClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            Button btn = (Button) v;
            if(btn.getText().toString().equals(getResources().getString(R.string.annuler))){
                setResult(RESULT_CANCELED);
                finish();
            }else if(btn.getText().toString().equals(getResources().getString(R.string.ajouter))){
                Intent intent = new Intent();
                intent.putExtra("name", name.getText().toString());
                intent.putExtra("email", email.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        }
    };
}