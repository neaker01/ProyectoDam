package com.example.proyectodam;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


//Rellenar el parcelable
public class Test implements Parcelable {



    private int id;
    private String key;
    private  Pregunta[] preguntas;
    private boolean respondido;
    private boolean aprobado;
    private int aciertos;
    private int fallos;

    public Test(int id, Pregunta[] preguntas, boolean respondido, boolean setAprobado, int aciertos, int fallos) {
        this.id = id;
        this.preguntas = preguntas;
        this.respondido = respondido;
        this.aprobado = setAprobado;
        this.aciertos = aciertos;
        this.fallos = fallos;
    }

    public Test(Pregunta[] preguntas, boolean respondido) {
        this.id = id;
        this.preguntas = preguntas;
        this.respondido = respondido;
    }


    protected Test(Parcel in) {
        id = in.readInt();
        preguntas = in.createTypedArray(Pregunta.CREATOR);
        respondido = in.readByte() != 0;
        aprobado = in.readByte() != 0;
        aciertos = in.readInt();
        fallos = in.readInt();
    }



    public void setKey(String key) {
        this.key = key;
    }

    public void setRespondido(boolean respondido) {
        this.respondido = respondido;
    }

    public void setAprobado(boolean aprobado) {
        this.aprobado = aprobado;
    }

    public String getKey() {
        return key;
    }

    public static Creator<Test> getCREATOR() {
        return CREATOR;
    }







    public void setId(int id) {
        this.id = id;
    }

    public void setPreguntas(Pregunta[] preguntas) {
        this.preguntas = preguntas;
    }

    public void setAprobado() {
        boolean aprobado = true;
        for (Pregunta pregunta:
                preguntas) {
            if (!pregunta.isRespondida() || !pregunta.isAcertada()){
                aprobado = false;
            }
        }
       this.aprobado = aprobado;
    }

    public void setRespondido() {
        int respondidas = 0;
        for (Pregunta pregunta:
                preguntas) {
            if (pregunta.isRespondida()){
                respondidas++;
            }
        }
        if (respondidas > 0) {
            this.respondido = true;
            setAprobado();
        }
    }



    public void setAciertos(int aciertos) {
        this.aciertos = aciertos;
    }

    public void setFallos(int fallos) {
        this.fallos = fallos;
    }

    public int getId(){
        return id;
    }

    public Pregunta[] getPreguntas() {
        return preguntas;
    }

    public boolean isRespondido() {
        return respondido;
    }

    public boolean isAprobado() {
        return aprobado;
    }

    public int getAciertos() {
        return aciertos;
    }

    public int getFallos() {
        return fallos;
    }


    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", preguntas=" + Arrays.toString(preguntas) +
                ", respondido=" + respondido +
                ", aprobado=" + aprobado +
                ", aciertos=" + aciertos +
                ", fallos=" + fallos +
                '}';
    }




    public static final Creator<Test> CREATOR = new Creator<Test>() {
        @Override
        public Test createFromParcel(Parcel in) {
            return new Test(in);
        }

        @Override
        public Test[] newArray(int size) {
            return new Test[size];
        }
    };



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeTypedArray(preguntas, flags);
        dest.writeByte((byte) (respondido ? 1 : 0));
        dest.writeByte((byte) (aprobado ? 1 : 0));
        dest.writeInt(aciertos);
        dest.writeInt(fallos);
        dest.writeString(key);
    }

    public Map<String, Object> toMap() {
        HashMap<java.lang.String, java.lang.Object> result = new HashMap<>();
        result.put( "id", id );
        result.put("key", key);
        result.put("respondido", respondido);
        result.put("aprobado", aprobado);
        result.put("aciertos", aciertos);
        result.put("fallos", fallos);
       // result.putAll("preguntas", preguntas);

        return result;
    }

}
