package pt.isel.poo.li23d.g4.powerplantapp.model;

public abstract class PointCell extends Cell {

    public PointCell(boolean powerCell){
        super(true, powerCell);
    }

    public boolean checkConnections(){
        previousConnections.clear();
        previousConnections.addAll(connections);
        setConnected(false);

        char dir = getDirection();

        switch(dir) {
            case 'N':
                if (validNextCell('S')){
                    addConnection('S');
                }
                break;
            case 'E':
                if (validNextCell('W')){
                    addConnection('W');
                }
                break;
            case 'S':
                if (validNextCell('N')){
                    addConnection('N');
                }
                break;
            case 'W':
                if (validNextCell('E')){
                    addConnection('E');
                }
                break;
        }
        removeConnections();

        return isConnected();
    }

    public boolean turnedWest(){
        return getDirection() == 'W';
    }

    public boolean turnedEast(){
        return getDirection() == 'E';
    }

    public boolean turnedNorth(){
        return getDirection() == 'N';
    }

    public boolean turnedSouth(){
        return getDirection() == 'S';
    }
}
