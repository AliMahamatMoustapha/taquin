import java.util.Scanner;
import model.caisse.Position;
import model.grille.Grid;

public class MainConsole {
    public static void main(String[] args){
         Grid g = new Grid(3, 3) ;
         g.affiche_Grille();
         System.out.println("-------------------------------");
         System.out.println("----------------------------------");
         Scanner sc = new Scanner(System.in);
        while (!g.terminer()) {
            System.out.println("entre x : ");
            int x = sc.nextInt() ;
            System.out.println("entre y : ");
            int y = sc.nextInt() ;
            System.out.println("Deplacement de : "+g.getGrille()[x][y]);
            g.deplacer(new Position(x, y));
            g.affiche_Grille();
         } 
         sc.close();
}
}
