package control;
import java.awt.event.*;
import javax.swing.JButton;
import model.caisse.Position;
import model.grille.Grid;
import vue.PlateauView;
public class Control implements ActionListener {
    private Grid modele ;
    private PlateauView view ;
    public Control (Grid modele,PlateauView view ){
        this.modele = modele ;
        this.view = view ;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton)e.getSource();
         if(source.getName()=="mov"){
            int row =(int) source.getClientProperty("row");
            int column =(int) source.getClientProperty("cols");
            modele.deplacer(new Position(row, column));
        } 
        if(source.getName()=="rejouer"){
            modele.init_Grille();
            view.remove(view.getPanel_victoire());
            view.add(view.getPanel_grill()) ;
            view.repaint();
            view.revalidate();
        } 
        if(source.getName()=="quitter"){
            System.exit(0);
        }
    }
    
}
