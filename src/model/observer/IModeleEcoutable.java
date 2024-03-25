package model.observer;

/**
 * IModeleEcoutable
 * @param <IEcouteurModele>
 */
public interface IModeleEcoutable{

    void addObserver(IEcouteurModele e) ;
    void removObserver(IEcouteurModele e) ;
}