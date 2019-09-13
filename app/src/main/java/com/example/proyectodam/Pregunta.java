package com.example.proyectodam;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;


//Rellenar el parcelable
public class Pregunta implements Parcelable {

   private int id;
    private String pegunta;
    private String[] respuestas;
    private String respuestaCorrecta;
    private boolean respondida;
    private boolean acertada;

    public Pregunta(int id, String pegunta, String[] respuestas, String respuestaCorrecta) {
        this.id = id;
        this.pegunta = pegunta;
        this.respuestas = respuestas;
        this.respuestaCorrecta = respuestaCorrecta;
        this.respondida = false;
        this.acertada = false;
    }

    public Pregunta(String pegunta, String[] respuestas, String respuestaCorrecta) {
        this.id = id;
        this.pegunta = pegunta;
        this.respuestas = respuestas;
        this.respuestaCorrecta = respuestaCorrecta;this.respondida = false;
        this.respondida = false;
        this.acertada = false;
    }

    protected Pregunta(Parcel in) {
        id = in.readInt();
        pegunta = in.readString();
        respuestas = in.createStringArray();
        respuestaCorrecta = in.readString();
        respondida = in.readByte() != 0;
        acertada = in.readByte() != 0;
    }

    public static final Creator<Pregunta> CREATOR = new Creator<Pregunta>() {
        @Override
        public Pregunta createFromParcel(Parcel in) {
            return new Pregunta(in);
        }

        @Override
        public Pregunta[] newArray(int size) {
            return new Pregunta[size];
        }
    };

    public void setId(int id) {
        this.id = id;
    }

    public void setPegunta(String pegunta) {
        this.pegunta = pegunta;
    }

    public void setRespuestas(String[] respuestas) {
        this.respuestas = respuestas;
    }

    public void setRespondida() {
        this.respondida = true;
    }

    public void setResponder(String r){
        setRespondida();
        acertada = true;
                if (r.equals(respuestaCorrecta)){
                      acertada = true;
                }

    }

    public boolean isRespondida() {
        return respondida;
    }

    public boolean isAcertada() {
        return acertada;
    }

    public void setRespuestaCorrecta(String respuestaCorrecta) {
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public int getId() {
        return id;
    }

    public String getPegunta() {
        return pegunta;
    }

    public String[] getRespuestas() {
        return respuestas;
    }

    public String getRespuestaCorrecta() {
        return respuestaCorrecta;
    }


    @Override
    public String toString() {
        return "Pregunta{" +
                "id=" + id +
                ", pegunta='" + pegunta + '\'' +
                ", respuestas=" + Arrays.toString(respuestas) +
                ", respuestaCorrecta='" + respuestaCorrecta + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(pegunta);
        dest.writeStringArray(respuestas);
        dest.writeString(respuestaCorrecta);
        dest.writeByte((byte) (respondida ? 1 : 0));
        dest.writeByte((byte) (acertada ? 1 : 0));
    }
}
