package com.example.proyectodam;

import java.util.Arrays;

public class Pregunta {

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
        for (String res:
             respuestas){
                if (r.equals(respuestaCorrecta)){
                      acertada = true;
                }
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


}
