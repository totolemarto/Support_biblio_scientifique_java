package com.antoine_tommy;

public class OurDim{
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
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        OurDim other = (OurDim) obj;
        if (nbRows != other.nbRows)
            return false;
        if (nbCols != other.nbCols)
            return false;
        return true;
    }

}

