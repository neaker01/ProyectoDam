package com.example.proyectodam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuAdministrador extends AppCompatActivity {

    private Button crearTest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_administrador);


        crearTest = findViewById(R.id.btGuardarPregunta);

        crearTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuAdministrador.this, CrearTest.class);
                startActivity(i);


            }
        });


    }
}
