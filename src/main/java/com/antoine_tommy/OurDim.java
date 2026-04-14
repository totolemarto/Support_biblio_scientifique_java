package com.antoine_tommy;

import java.util.Objects;

public class OurDim {
    int nbRows;
    int nbCols;

    public OurDim(int nbRows, int nbCols) {
        this.nbRows = nbRows;
        this.nbCols = nbCols;
    }

    public int getNbRows() {
        return nbRows;
    }
    public void setNbRows(int nbRows) {
        this.nbRows = nbRows;
    }
    public int getNbCols() {
        return nbCols;
    }
    public void setNbCols(int nbCols) {
        this.nbCols = nbCols;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        OurDim ourDim = (OurDim) obj;
        return getNbRows() == ourDim.getNbRows() && getNbCols() == ourDim.getNbCols();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNbRows(), getNbCols());
    }
}

