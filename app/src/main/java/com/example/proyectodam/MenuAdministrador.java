package com.example.proyectodam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class MenuAdministrador extends AppCompatActivity {

    private Button crearTest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_administrador);


        crearTest = findViewById(R.id.btCrearTest);

        crearTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                


            }
        });


    }
}
