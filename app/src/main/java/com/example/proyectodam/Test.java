package com.example.proyectodam;

import java.util.Arrays;

public class Test {

    private int id;
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
        if (respondidas > 0)
        this.respondido = true;
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

}
