package common;

import java.io.Serializable;

public class Espece implements Serializable {
	private String nom;
    private int esperanceDeVie;
    
    public Espece(String nom, int esperanceDeVie) {
        this.nom = nom;
        this.esperanceDeVie = esperanceDeVie;
    }
    public String getNom() {
        return nom;
    }

    public int getEsperanceDeVie() {
        return esperanceDeVie;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

}
