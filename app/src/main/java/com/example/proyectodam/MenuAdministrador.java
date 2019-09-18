package com.example.proyectodam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuAdministrador extends AppCompatActivity {

    private Button crearTest;
    private Button listaTests;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_administrador);

        crearTest = findViewById(R.id.btGuardarPregunta);
        listaTests = findViewById(R.id.btListaTest);
        crearTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuAdministrador.this, CrearTest.class);
                startActivity(i);

            }
        });

        listaTests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuAdministrador.this, Tests.class);
                startActivity(i);


            }
        });

    }
}
