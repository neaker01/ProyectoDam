package com.example.proyectodam;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Inicial extends AppCompatActivity {

    private Button btPerfil;
    private Button btTests;
    private Button btVideos;
    private Button btcontacto;
    private Button btLogout;
    private Preferencias preferencias;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);
        init();



        btPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferencias.eliminarPreferencias();
                Intent i = new Intent(Inicial.this, Perfil.class);
                startActivity(i);
            }
        });


        btTests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferencias.eliminarPreferencias();
                Intent i = new Intent(Inicial.this, Tests.class);
                startActivity(i);
            }
        });

        btLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferencias.eliminarPreferencias();
                Intent i = new Intent(Inicial.this, Login.class);
                startActivity(i);
            }
        });
    }




    private void init() {
        btPerfil=findViewById(R.id.btPerfil);
        btTests=findViewById(R.id.btLista);
        btVideos=findViewById(R.id.btVideos);
        btcontacto=findViewById(R.id.btContacto);
        btLogout=findViewById(R.id.btLogout);
        preferencias = new Preferencias(getApplicationContext());
    }
}
