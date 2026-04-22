package com.antoine_tommy;

import com.antoine_tommy.ndarray.Dtype;
import com.antoine_tommy.ndarray.Ndarray;
import com.antoine_tommy.ndarray.Shape;

import java.util.logging.Logger;

public class Demo {
    private static final Logger LOGGER = Logger.getLogger(Demo.class.getName());
    public static void main(String[] args) {
        LOGGER.info("=== NDARRAY DEMO ===");

        Ndarray arrayString1 = new Ndarray(new Object[]{"Hello"}, new Shape(1, 1), Dtype.STRING);
        Ndarray arrayString2 = new Ndarray(new Object[]{" world"}, new Shape(1, 1), Dtype.STRING);

        LOGGER.info("On créer deux NDarray de String dont voici la représentation");
        LOGGER.info(arrayString1.toNumpyString());
        LOGGER.info(arrayString2.toNumpyString());

        LOGGER.info("Puis voici le résultat de l'addition de ces deux arrays");

        LOGGER.info(arrayString1.add(arrayString2).toString());

        LOGGER.info("C'était super hein :) ");
    }
}
