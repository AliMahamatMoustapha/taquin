package model.grille;

import java.util.LinkedList;
import java.util.Random;

import model.caisse.Caisse;
import model.caisse.Position;
import model.observer.AbstractModeleEcoutable;

public class Grid extends AbstractModeleEcoutable{
    private int ligne , colonne  ;
    public Caisse[][] grilleGagnante ;
    public Caisse[][] getGrille() {
        return grille;
    }
    private Caisse[][] grille  ;
    private LinkedList<Integer> listeValeurs ;
    Random random ;
    public Grid(int ligne ,int colonne){
        this.ligne = ligne ;
        this.colonne = colonne ;
        grilleGagnante = gagner() ;
        random =new Random() ;
        grille = new Caisse[ligne][colonne] ;
        init_Grille();
    }
    public void init_Grille(){
        listeValeurs = new LinkedList<>() ;
        for(int i =0 ;i<ligne ;i++){
            for(int j =0 ;j<colonne ;j++){
                grille[i][j] = new Caisse(new Position(i,j),nouveau_valeur()) ;
            }
        }
        fireChangement();
    }
    public void affiche_Grille(){
        for(int i =0 ;i<ligne ;i++){
            for(int j =0 ;j<colonne ;j++){
                System.out.print(grille[i][j]);
            }
        System.out.println();
        }
    }
    public int getLigne() {
        return ligne;
    }
    public int getColonne() {
        return colonne;
    }
    public int nouveau_valeur(){
        int v = random.nextInt(ligne*colonne) ;
        if(!listeValeurs.contains(v)){
            listeValeurs.add(v) ;
            return v ;
        }
        return nouveau_valeur() ; 
    } 
    /* public int nouveau_valeur(){
        int tailleMax = ligne * colonne;
        if (listeValeurs.size() >= tailleMax) {
            return -1; 
        }
    
        int v;
        do {
            v = random.nextInt(tailleMax);
        } while (listeValeurs.contains(v));
    
        listeValeurs.add(v);
        return v;
    }
     */
public Caisse caisse_vide(Position depart ,Caisse[][] grille){
    Caisse vide =null ;
    for(int i = -1 ;i<2;i++){
        for(int j = -1;j<2 ; j++){
            if((i==0 && j==0) ||(i==-1 && j==-1) || (i==-1 && j==1) || (i==1 && j==-1) || (i==1 && j==1) ){
                continue ;
            }
            int x =depart.getPosX()+i ;
            int y =depart.getPosY()+j ;
            if((x>=0 && x<ligne) && (y>=0 && y<colonne && grille[x][y].estVide())){
                vide=new Caisse(new Position(x, y),grille[x][y].getValeur());
            }
        }
    }
    return vide ;
}
public void deplacer(Position caisseDeplacer){
    Caisse caisse = caisse_vide(caisseDeplacer, grille) ;
    if(caisse != null){
        int valeur = grille[caisseDeplacer.getPosX()][caisseDeplacer.getPosY()].getValeur() ;
        grille[caisseDeplacer.getPosX()][caisseDeplacer.getPosY()].setValeur(caisse.getValeur());
        grille[caisse.getPosition().getPosX()][caisse.getPosition().getPosY()].setValeur(valeur);
        
    }
    else{
        System.out.println("impossible de daplacer");
    } 
    fireChangement() ;
}
public Caisse[][] gagner(){
   Caisse[][] grilleGagnante = new Caisse[ligne][colonne] ;
   int v =1 ;
    for(int i =0 ;i<ligne ;i++){
        for(int j =0 ;j<colonne ;j++){
            grilleGagnante[i][j] = new Caisse(new Position(i, j), v++);
        }
    }
    grilleGagnante[ligne-1][colonne-1].setValeur(0);
    return grilleGagnante;
}
public boolean terminer(){
    boolean egau =true ;
    for(int i=0 ;i<ligne;i++){
        for(int j=0 ;j<colonne ;j++){
            egau=egau && grille[i][j].equals(grilleGagnante[i][j]);
        }
    }
    return egau ;
}
}
