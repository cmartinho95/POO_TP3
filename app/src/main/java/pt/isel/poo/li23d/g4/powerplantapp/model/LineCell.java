package pt.isel.poo.li23d.g4.powerplantapp.model;

public class LineCell extends Cell {

    public LineCell(){
        super(true,false);
    }

    public boolean isConnected(){
        previousConnections.clear();
        previousConnections.addAll(connections);
        connected = false;

        char dir = getDirection();

        switch(dir) {
            case 'N':
            case 'S':
                if (validNextCell('S')){
                    addConnection('S');
                }
                if(validNextCell('N')){
                    addConnection('N');
                }
                break;
            case 'E':
            case 'W':
                if (validNextCell('W')){
                    addConnection('W');
                }
                if(validNextCell('E')){
                    addConnection('E');
                }
                break;
        }
        removeConnections();

        return connected;
    }

    public boolean turnedWest(){
        return getDirection() == 'W' || getDirection() == 'E';
    }

    public boolean turnedEast(){
        return turnedWest();
    }

    public boolean turnedNorth(){
        return getDirection() == 'N' || getDirection() == 'S';
    }

    public boolean turnedSouth(){
        return turnedNorth();
    }

}
