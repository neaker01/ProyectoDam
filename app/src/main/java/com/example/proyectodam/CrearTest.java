package com.example.proyectodam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CrearTest extends AppCompatActivity {

    private EditText txPregunta;
    private EditText txRespuesta1;
    private EditText txRespuesta2;
    private EditText txRespuesta3;
    private EditText txCorrecta;
    private Button btGuardarPregunta;
    private Button btCrearTest;

    private ArrayList<Pregunta> preguntas;

    private FirebaseAuth autentificador;
    private FirebaseUser usuario;
    private FirebaseDatabase database;
    private DatabaseReference dbReference, ref2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_test);
        init();


        btGuardarPregunta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                comprobarPregunta(); //Habra que comprobar que ninguna respuesta este vacia, y que el test no exista

            }
        });


        btCrearTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                guardarTest();
            }
        });


    }
// Hay que comprobar si estamos creando un test nuevo o modificando uno antiguo
    public void comprobarPregunta() {

        if (txPregunta.getText().toString().isEmpty() ||
                txRespuesta1.getText().toString().isEmpty() ||
                txRespuesta2.getText().toString().isEmpty() ||
                txRespuesta3.getText().toString().isEmpty() ||
                txCorrecta.getText().toString().isEmpty() )
                {

            Toast.makeText(this, "Debe rellenar todos los campos", Toast.LENGTH_SHORT).show();

        } else {
            System.out.println();
            String correcta = "";

            try {
                int numCorrecta = Integer.parseInt(txCorrecta.getText().toString());

                if (numCorrecta == 1) {
                    correcta = txRespuesta1.getText().toString();
                } else if (numCorrecta == 2) {
                    correcta = txRespuesta2.getText().toString();
                } else if (numCorrecta == 3) {
                    correcta = txRespuesta3.getText().toString();
                }
                Pregunta pregunta = new Pregunta(txPregunta.getText().toString(),
                        new String[]{txRespuesta1.getText().toString(), txRespuesta2.getText().toString(),
                                txRespuesta3.getText().toString()}, correcta);

                if (preguntas.size() < 10) {
                    //if (comprobarPreguntaRepetida(pregunta)) {
                    pregunta.setId(preguntas.size() + 1);
                    preguntas.add(pregunta);
                    //}else{
                    Toast.makeText(this, "Esa pregunta ya está guardada", Toast.LENGTH_SHORT).show();

                    //  }
                    //   borrarTextos();
                } else {
                    Toast.makeText(this, "El test ya contiene todas las preguntas, puede guardarlo", Toast.LENGTH_SHORT).show();
                }
            }
            catch (NumberFormatException ex){
                Toast.makeText(this, "El numero de la respuesta correcta debe estar entre 1 y 3", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void guardarTest(){
        Pregunta[] lista = new Pregunta[preguntas.size()];
        for (int i = 0; i < preguntas.size(); i++){
            lista[i] = preguntas.get(i);
        }

        Test test = new Test(lista, false);
        Map<String, Object> saveItem = new HashMap<>();
         String key = dbReference.child("plantillas").push().getKey();
        test.setKey(key);
        System.out.println("LA KEY EN LA INSERCION: " + key);
        saveItem.put("/plantillas/"  + key + "/", test.toMap());

        final Test testFinal = test;
        final String keyFinal = key;


        // saveItem.put("/tests/"  + key + "/preguntas", (Pregunta)lista[0].to);
        dbReference.updateChildren(saveItem)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        meterPreguntas(keyFinal, testFinal.getPreguntas()); // Cada test tiene un array de preguntas que hay que introducir desde otro metodo
                       // gestor.add(lugar);
                        Log.v("save", "save: " + task.isSuccessful());
                        System.out.println("SE HA GUARDADOOO");
                        preguntas = new ArrayList<>();

                        //Intent i = new Intent();
                       // setResult(GUARDADO, i);
                       // finish();
                    }


                });

    }

    public void meterPreguntas(String key, Pregunta[] preguntas){

        for (Pregunta p: preguntas) {
            Map<String, Object> saveItem = new HashMap<>();
            String key2 = dbReference.child("plantillas/"+key).push().getKey();
            System.out.println("LA KEY EN LA INSERCION: " + key);
            saveItem.put("plantillas/"+key+"/"+key2, p.toMap());

            dbReference.updateChildren(saveItem)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            System.out.println("SE HA GUARDADOOO");
                        }


                    });
        }

    }




    public boolean comprobarPreguntaRepetida(Pregunta p){
        if (preguntas.size() > 0){
            for (Pregunta preg:
                    preguntas) {
                if (preg.getPegunta().equals(p.getPegunta())){
                    return false;
                }
            }
        }
        return true;
    }

    public void borrarTextos(){

        txPregunta.setText("");
        txRespuesta1.setText("");
        txRespuesta2.setText("");
        txRespuesta3.setText("");
        txCorrecta.setText("");

    }

    private void init() {
                database = FirebaseDatabase.getInstance();
                autentificador = FirebaseAuth.getInstance();
                dbReference = database.getReference();

        preguntas = new ArrayList<>();


        btGuardarPregunta = findViewById(R.id.btGuardarPregunta);
        btCrearTest = findViewById(R.id.btCrearTest);
        txPregunta=findViewById(R.id.txPregunta);
        txRespuesta1=findViewById(R.id.txOpcion1);
        txRespuesta2=findViewById(R.id.txOpcion2);
        txRespuesta3=findViewById(R.id.txOpcion3);
        txCorrecta = findViewById(R.id.txCorrecta);

    }

    /*

      Test test = new Test(new Pregunta[]  {a, b, c}, false);
            Map<String, Object> saveItem = new HashMap<>();
            String key = dbReference.child("lugares").push().getKey();
            Test.setkey(key);
            System.out.println("LA KEY EN LA INSERCION: " + key);
            saveItem.put("/lugares/" + usuario.getUid() + "/" + key + "/", lugar.toMap());
            dbReference.updateChildren(saveItem)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            gestor.add(lugar);
                            Log.v("save", "save: " + task.isSuccessful());
                            Intent i = new Intent();
                            setResult(GUARDADO, i);
                            finish();


                        }


                    });
     */

}
