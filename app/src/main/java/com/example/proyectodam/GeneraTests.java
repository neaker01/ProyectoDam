package com.example.proyectodam;

public  class GeneraTests {

    public static Test doTests(){

        Pregunta a = new Pregunta("Las siglas correctas son: ", new String[] {"PAS" , "SEO", "SOS"}, "PAS");

        Pregunta b = new Pregunta("En un stop hay que: ", new String[] {"Acelerar" , "Frenar un poco", "Detenerse completamente"}, "Detenerse completamente");

        Pregunta c = new Pregunta("En un stop hay que: ", new String[] {"Acelerar" , "Frenar un poco", "Detenerse completamente"}, "Detenerse completamente");



        Test test = new Test(new Pregunta[]  {a, b, c}, false);

        return test;
    }

}
