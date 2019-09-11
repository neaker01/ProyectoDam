package com.example.proyectodam;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.webkit.JavascriptInterface;

    public class Preferencias {

        private Context context;
        SharedPreferences pref;
        SharedPreferences.Editor editor;

        public Preferencias(Context context) {
            this.context = context;
        }

        public void guardarPreferencias(String usuario, String pass) {
            pref = PreferenceManager.getDefaultSharedPreferences(context);
            editor = pref.edit();
            editor.putString("credenciales", usuario +"-"+ pass);
            editor.apply();
        }

        public String getSesion() {
            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
            String v = pref.getString("credenciales", "null");
            return v;
        }

        public void eliminarPreferencias(){
            //  pref.edit().clear().apply();
            //   pref = context.getSharedPreferences("credenciales", Context.MODE_PRIVATE).edit().remove("credenciales");
            pref = PreferenceManager.getDefaultSharedPreferences(context);
            editor = pref.edit();
            pref.edit().remove("credenciales").apply();
            // this.pref = null;
            this.editor = null;
            //  this.context=null;
            System.out.println("PREFERENCIAS borradas");
        }

        @JavascriptInterface
        public void sendData(String usuario, String pass) { //enviamos el user y la pass
            guardarPreferencias(usuario, pass);
            System.out.println("Guardamos en preferencias");
        }

    }





