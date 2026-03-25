package com.biblio_scientifique.app;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Ndarray{

    int ndim;
    Dimension shape;
    int size;
    private  ArrayList<ArrayList<Float>> data;

    public Ndarray(int ndim, Dimension shape, int size) {
        this.ndim = ndim;
        this.shape = shape;
        this.size = size;
    }

    public Ndarray() {
    }

    public Ndarray(int ndim) {
        this.ndim = ndim;
        this.data = new ArrayList<>();
        this.shape = new Dimension(0, 0);
    }

    public static Ndarray arrange(int maxi){

        Ndarray result = new Ndarray(1);
        for (float i = 0; i < maxi; i++){
            result.appendValue(i, 0);
        }
        return result;
    }

    
    public boolean appendValue(float value, int dimension){
        boolean result = true;
        if (data.size() < dimension){
            for (int i = data.size(); i < dimension; i++){
                ArrayList<Float> current = new ArrayList<Float>();
                for (int j = 0; j < size; j++){
                    current.add(0f);
                }
                data.add(current);
            }
        }
        return result;
    }


    @Override
    public String toString() {
        return "Ndarray [data=" + data + "]";
    }

    public int getNdim() {
        return ndim;
    }

    public void setNdim(int ndim) {
        this.ndim = ndim;
    }

    public Dimension getShape() {
        return shape;
    }

    public void setShape(Dimension shape) {
        this.shape = shape;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void insert(float value, int dimension, int row){
        data.get(row).add(value);
    }
    
    // • zeros() : Cr´eation d’un ndarray rempli de z´eros
    public Ndarray zeros(){
        return new Ndarray(0);
    }

    // • array() : Cr´eation `a partir d’un tableau
    public static Ndarray array(float[] values){
        Ndarray result = new Ndarray(1);
        for (float value : values){
            result.insert(value, 0, 1);
        }
        return result;
    }

    public ArrayList<Float> get_values(int dimension, int row) {
        return data.get(row);
    }

    public Ndarray add(Ndarray other){
        Ndarray result = new Ndarray();
        return result;
    }

}


/*
 *
    Dans cette partie, nous d´ecrivons quelques fonctions que vous avez l’obligation d’int´egrer `a votre
    biblioth`eque dans le contexte de ce projet.
    Types de donn´ees : ndarray La structure de donn´ee de base utilis´ee dans Numpy est le ndarray.
    Une description de base est fournie ici.
    Dans votre projet, vous devez :
    M1 INFO – DevOps – 2026 UFR IM2AG 6
    • Supporter des objets de type ndarray en 1D (vecteur) et 2D (matrice). (Supporter plus de di-
    mensions est optionnel)
    • Avoir des ndarray capables de manipuler des donn´ees de type float. (Supporter d’autres types
    de donn´ees est optionnel)
    Vous devez aussi associer des attributs de base `a vos objets ndarray : ndarray.ndim, ndarray.shape,
    ndarray.size.
    Fonctions de cr´eation Pour cr´eer vos ndarray, votre biblioth`eque doit fournir les fonctions de
    cr´eation suivantes (d´ecrites ici) :
    • arange() : Cr´eation d’un ndarray initialis´e avec une s´equence de nombres.
    Affichage Vous devez fournir un affichage tel que d´ecrit ici.
    Op´erations de base Vous devez supporter des op´erations de bases telles que d´ecrites ici. Vous devrez
    supporter au moins les op´erateurs d’addition (’+’ et ’+=’).
    Changer la forme d’un ndarray Votre biblioth`eque doit fournir une fonction reshape() telle que
    d´ecrite ici.
    5.2 Fonctionnalit´es optionnelles
    Vous trouverez ci-dessous quelques suggestions concernant d’autres fonctionnalit´es `a ajouter `a votre
    biblioth`eque.
    • Pour toutes les cat´egories de fonctionnalit´es d´ecrites dans la section 5.1, vous avez bien sˆur
    la libert´e d’ajouter d’autres fonctionnalit´es que celle d´ej`a mentionn´ees si elles vous semblent
    int´eressantes. Attention cependant `a ne pas multiplier les fonctions tr`es proches en termes de
    mise en œuvre, car cela n’apportera pas grand chose `a la qualit´e de votre rendu.
    • Il peut ˆetre int´eressant d’essayer d’impl´ementer certaines des universal functions list´ees ici.
    • Il peut aussi ˆetre int´eressant de mettre en oeuvre la fonctionnalit´e de broadcast permettant d’ef-
    fectuer des op´erations combinant des ndarrays de dimensions diff´erentes.
    • Plus g´en´eralement, vous pouvez vous r´ef´erer `a la documentation du package Python Numpy pour
    trouver d’autres fonctionnalit´es que vous aimeriez mettre en œuvre.

*/
