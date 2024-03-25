package model.caisse;
/**
 * Caisse
 */
public class Caisse {
    
    private int valeur ;
    private Position position ;
    
    public Caisse(Position position ,int valeur){
        this.position = position ;
        this.valeur = valeur ; 
    }

    public int getValeur() {
        return valeur;
    }
    
    public Position getPosition() {
        return position;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }
    public boolean estVide(){
        return valeur==0 ;
    }
    public String toString(){
        return valeur+" ";
    }
    public boolean equals(Caisse c){
        return this.valeur == c.getValeur() ;
    }
}