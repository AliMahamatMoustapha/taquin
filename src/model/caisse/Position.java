package model.caisse;

public class Position {

    private int posX,posY ;

    public Position(int x ,int y){
        posX = x ;
        posY = y ;
    }
    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }
    public String toString(){
        return "("+posX+","+posY+")" ;
    }
}
