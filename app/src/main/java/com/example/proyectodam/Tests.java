package com.example.proyectodam;




import android.os.Bundle;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Tests extends AppCompatActivity {

    private RecyclerView recyclerLugares;
    private AdaptadorTests adaptador;
    private RecyclerView.LayoutManager lymanager;

    private ArrayList<Test> listaTest = new ArrayList<>(); //primer array donde guardamos los libros leido
    private ArrayList<Test> testFirebase = new ArrayList<>();

    private ConstraintLayout container;

    private Preferencias preferencias;
    private FirebaseAuth autentificador;
    private DatabaseReference dbRef;
    private FirebaseUser usuario;
    private FirebaseDatabase firebaseDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tests);


        dbRef = FirebaseDatabase.getInstance().getReference();
        FirebaseApp.initializeApp(this);
        autentificador = FirebaseAuth.getInstance();
        usuario = autentificador.getCurrentUser();

        preferencias = new Preferencias(getApplicationContext());
        getTests();

        usuario = autentificador.getCurrentUser();

        this.container = (ConstraintLayout) findViewById(R.id.contenedor);
        this.recyclerLugares = (RecyclerView) findViewById(R.id.recyclerLista);

        //  setAdapter(listaTest);
        // lymanager = new LinearLayoutManager(this);
        // recyclerLugares.setLayoutManager(lymanager);

    }

    public void getTests() {
        if (autentificador.getCurrentUser().getEmail().toString().contains("admin")) {
            getTestAdmin();

        } else {
            getTestUsuarios();
        }
    }


    public void getTestAdmin() {

       // String key = dbRef.child("plantillas").getKey();
      //  System.out.println("KEY KE SACA " +key+"/");
      //  System.out.println("DONDE BUSCA: plantillas/"+key+"/");


        dbRef.child("/plantillas/").
            addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange (@NonNull DataSnapshot dataSnapshot){

                    if (dataSnapshot.exists()) {
                        int cont = 0;

                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            for (DataSnapshot dsNap : ds.getChildren()) {
                                String key = dsNap.getKey();
                                cont++;
                            System.out.println("PREGUNTA " +cont);
                            Pregunta pregunt;
                                pregunt = (Pregunta) dsNap.getValue(Pregunta.class);
                                System.out.println("PREGUNTa " +pregunt);


                           // System.out.println("ESTE TEST ES " +t);
                            System.out.println("SALIDA "+ds.toString());
                            System.out.println("BUSCA EN " +""+key+"/respuestaCorrecta");

                            String nombre = (String) ds.child(""+key+"/respuestaCorrecta").getValue();
                            System.out.println("RESPUESTAA "+nombre);

                         //   listaTest.add(null);
                        }}
                    }

                }

                @Override
                public void onCancelled (@NonNull DatabaseError databaseError){

                }
            });
}

public void getTestUsuarios(){}




    private void setAdapter(ArrayList<Test> test){
        //Reemplaza el adaptador por una nueva instancia con un nuevo dataset.
        adaptador = new AdaptadorTests(test, new AdaptadorTests.OnItemClickListener() {
            @Override
            public void onItemClick(Test t) {

               // Toast.makeText(Tests.this, "PULSADO " +t.getId(), Toast.LENGTH_SHORT).show();

           //     Log.v(TAG, "LUGAR CLICK : " + l.getNombre());
              //  Intent i = new Intent(MainActivity.this, Detalle.class);
              //  i.putExtra("lugar", (Parcelable) l);
                //  Toast.makeText(MainActivity.this, "LUGAR CLICKADO " +l.getNombre(), Toast.LENGTH_SHORT).show();
              // startActivityForResult(i, DETALLE);

            }
        });
        recyclerLugares.setAdapter(adaptador);


    }
}
