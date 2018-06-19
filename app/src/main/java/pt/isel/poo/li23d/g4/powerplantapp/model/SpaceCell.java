package pt.isel.poo.li23d.g4.powerplantapp.model;

public class SpaceCell extends Cell {

    public SpaceCell(){
        super(false, false);
    }

    public char getDirection(){
        return ' ';
    }

    public void nextDirection(){
        getDirection();
    }

    public boolean isConnected(){
        return false;
    }

    public boolean turnedWest(){
        return false;
    }

    public boolean turnedEast(){
        return false;
    }

    public boolean turnedNorth(){
        return false;
    }

    public boolean turnedSouth(){
        return false;
    }
}
