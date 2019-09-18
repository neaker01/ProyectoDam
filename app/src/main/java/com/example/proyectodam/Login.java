package com.example.proyectodam;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {

    private Button btLogin;
    private EditText txUser;
    private EditText txPass;
    private FirebaseAuth autentificador;
    private FirebaseUser fbUser;
    private FirebaseDatabase firebaseDatabase;
    private String usuario;
    private String clave;
    private Preferencias preferencias;
    private String prefEmail;
    private String prefPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseDatabase= FirebaseDatabase.getInstance();
        FirebaseApp.initializeApp(this);
        autentificador =  FirebaseAuth.getInstance();
        preferencias = new Preferencias(getApplicationContext());

    // Comprobacion de preferencias para iniciar sesion automaticamente

        if (preferencias.getSesion() != null){
            String sesion =preferencias.getSesion();
            String[] arraySesion = sesion.split("-");
            try {
                prefEmail = arraySesion[0];
                prefPassword = arraySesion[1];
                iniciarSesion(prefEmail, prefPassword);
            }catch (ArrayIndexOutOfBoundsException ex){
                // Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            }
        }

        init();

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!txUser.getText().toString().isEmpty() &&
                        !txPass.getText().toString().isEmpty()){
                    // When a user signs in to your app, pass the user's email address and password to signInWithEmailAndPassword
                    autentificador.signInWithEmailAndPassword(txUser.getText().toString(), txPass.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {


                                        System.out.println("NOMBRE USUARIOO " +autentificador.getCurrentUser().getDisplayName());


                                        System.out.println("EMAIL USUARIOO " +autentificador.getCurrentUser().getEmail());

                                        fbUser = autentificador.getCurrentUser();
                                        preferencias.guardarPreferencias(txUser.getText().toString(), txPass.getText().toString());
                                        if (txUser.getText().toString().contains("Admin")){
                                            Intent i = new Intent(Login.this, MenuAdministrador.class);
                                            startActivity(i);
                                        }else {
                                            Intent i = new Intent(Login.this, Inicial.class);
                                            startActivity(i);
                                        }
                                    } else {
                                        Toast.makeText(Login.this, "Fallo autentificacion", Toast.LENGTH_SHORT).show();

                                        try{
                                            preferencias.eliminarPreferencias();
                                        } catch (NullPointerException ex){
                                        }
                                    }
                                }
                            });
                }
            }
        });
    }


    public void iniciarSesion(String email, String password){
        final String finalEmail = email;
        final String  finalPass=password;
        autentificador.signInWithEmailAndPassword(email, password).addOnCompleteListener
                (new OnCompleteListener<AuthResult>() {
                    private static final String TAG = "MITAG";

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            fbUser= autentificador.getCurrentUser();
                            Intent i = new Intent(Login.this, Inicial.class);
                            startActivity(i);
                        }
                        else{
                            preferencias.eliminarPreferencias();
                            Log.v(TAG, "Error: "+task.getException().toString() );}}});}


    private void init() {
        btLogin=findViewById(R.id.btLogin);
        txUser=findViewById(R.id.txUser);
        txPass=findViewById(R.id.txPass);
    }
}
