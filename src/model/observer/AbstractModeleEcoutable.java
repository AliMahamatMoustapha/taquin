package model.observer;

import java.util.ArrayList;

/**
 * AbstractModeleEcoutable
 */
public abstract class AbstractModeleEcoutable implements IModeleEcoutable {
    ArrayList<IEcouteurModele> ecouteurs ;
    public AbstractModeleEcoutable(){
        ecouteurs = new ArrayList<>() ;
    }
    @Override
    public void addObserver(IEcouteurModele e) {
        ecouteurs.add(e) ;
        
    }
    @Override
    public void removObserver(IEcouteurModele e) {
        ecouteurs.remove(e) ;
    }
    public void fireChangement(){
        for (IEcouteurModele e : ecouteurs) {
            e.miseAjours(this);
        }
    }
    
    
}