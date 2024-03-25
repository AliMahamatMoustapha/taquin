import model.grille.Grid;
import vue.PlateauView;

/**
 * MainSwing
 */
public class MainSwing {

    public static void main(String[] args) {
        Grid game = new Grid(3, 3) ;
        PlateauView v = new PlateauView(game) ;
        v.setVisible(true);
        
    }
}