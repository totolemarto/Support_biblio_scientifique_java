package com.antoine_tommy;

import com.antoine_tommy.ndarray.Dtype;
import com.antoine_tommy.ndarray.Ndarray;
import com.antoine_tommy.ndarray.Shape;

public class Demo {
    public static void main(String[] args) {
        System.out.println("=== NDARRAY DEMO ===");

        Ndarray arrayString1 = new Ndarray(new Object[]{"Hello"}, new Shape(1, 1), Dtype.STRING);
        Ndarray arrayString2 = new Ndarray(new Object[]{" world"}, new Shape(1, 1), Dtype.STRING);

        System.out.println("On créer deux NDarray de String dont voici la représentation");
        System.out.println(arrayString1);
        System.out.println(arrayString2);

        System.out.println("Puis voici le résultat de l'addition de ces deux arrays");

        System.out.println(arrayString1.add(arrayString2));

        System.out.println("C'était super hein :) ");
    }
}
