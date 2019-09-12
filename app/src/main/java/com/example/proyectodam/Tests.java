package com.example.proyectodam;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class Tests extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tests);

   Test t =  GeneraTests.doTests();

        System.out.println(t);



    }
}
