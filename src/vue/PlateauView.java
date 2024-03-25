package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import control.Control;
import model.caisse.Caisse;
import model.grille.Grid;
import model.observer.IEcouteurModele;

public class PlateauView extends JFrame implements IEcouteurModele{
    private JPanel panel_grill ;
    public JPanel getPanel_grill() {
        return panel_grill;
    }

    public JPanel getPanel_victoire() {
        return panel_victoire;
    }

    private JPanel panel_victoire ;
    private JLabel label ;
    private JButton[][]  grilleview ;
    private JButton rejouer,quiter ;
    public JButton getQuiter() {
        return quiter;
    }
    public JButton getRejouer() {
        return rejouer;
    }

    private Grid modele ;
    public void setModele(Grid modele) {
        this.modele = modele;
    }

    public PlateauView(Grid modele){
        super("Taquin");
        setSize(700, 700);
        setLocationRelativeTo(null);
        setResizable(false);
        ((JComponent) this.getContentPane()).setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.modele = modele ;
        this.panel_grill = take_grille() ; 
        panel_victoire = new JPanel() ;
        label = new JLabel(" Felicitation Vous avez Gagner ") ;
        label.setBounds(150,200,500, 70);
        label.setFont(new Font("Arial",Font.BOLD,20));
        label.setForeground(Color.WHITE);
        rejouer = new JButton("rejouer") ; 
        rejouer.setName("rejouer");
        rejouer.addActionListener(new Control(modele,this));
        quiter = new JButton("quitter") ;
        quiter.setName("quitter");
        quiter.addActionListener(new Control(modele,this));
        rejouer.setBounds(190,350,100, 30);
        quiter.setBounds(340,350,100, 30);
        panel_victoire.setBackground(Color.black);
        panel_victoire.setLayout(null);
        panel_victoire.add(label,FlowLayout.LEFT);
        panel_victoire.add(rejouer);
        panel_victoire.add(quiter);
        this.getContentPane().add(panel_grill) ;
        modele.addObserver(this);

    }
    public JPanel take_grille(){
        JPanel panel = new JPanel(new GridLayout(modele.getLigne(),modele.getColonne())) ;
        this.grilleview =new JButton[modele.getLigne()][modele.getColonne()] ;
        for(int i= 0 ;i<modele.getLigne();i++ ){
            for(int j=0 ;j<modele.getColonne();j++){
                grilleview[i][j] = new JButton();
                grilleview[i][j].putClientProperty("row", i);
                grilleview[i][j].putClientProperty("cols", j);
                grilleview[i][j].setFocusPainted(false);
                grilleview[i][j].setFont(new Font("Arial",Font.BOLD,50));
                grilleview[i][j].setName("mov");
                grilleview[i][j].addActionListener(new Control(modele,this)) ;
                if(modele.getGrille()[i][j].getValeur()!=0){
                    grilleview[i][j].setText(modele.getGrille()[i][j].getValeur()+" ");
                    grilleview[i][j].setBackground(Color.WHITE);

                }
               else {grilleview[i][j].setBackground(Color.black);grilleview[i][j].setText("");}
                panel.add(grilleview[i][j]) ;
            }
        }
        return panel ;
    } 
    public void update(Caisse[][] grille){
        for(int i= 0 ;i<grille.length;i++ ){
            for(int j=0 ;j<grille[0].length;j++){
                if(grille[i][j].getValeur()!=0){
                    grilleview[i][j].setText(grille[i][j].getValeur()+" ");
                    grilleview[i][j].setBackground(Color.WHITE);
                }
               else {grilleview[i][j].setBackground(Color.black);grilleview[i][j].setText("");}
            }
        }
    }
   
    @Override
    public void miseAjours(Object o) {
        Grid modele = (Grid)o ;
        update(modele.getGrille()); 
        if(modele.terminer()){
            this.setBackground(Color.BLACK);
            this.remove(panel_grill);
            this.getContentPane().add(panel_victoire,BorderLayout.CENTER) ;
         }
        
    }
    
}
