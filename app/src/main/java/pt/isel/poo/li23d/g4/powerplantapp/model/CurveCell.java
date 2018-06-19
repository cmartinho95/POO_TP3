package pt.isel.poo.li23d.g4.powerplantapp.model;

public class CurveCell extends Cell {

    public CurveCell(){
        super(true, false);
    }

    public boolean isConnected(){
        previousConnections.clear();
        previousConnections.addAll(connections);
        connected = false;

        char dir = getDirection();

        switch(dir) {
            case 'N':
                if (validNextCell('S')){
                    addConnection('S');
                }
                if (validNextCell('W')){
                    addConnection('W');
                }
                break;
            case 'E':
                if (validNextCell('W')){
                    addConnection('W');
                }
                if (validNextCell('N')){
                    addConnection('N');
                }
                break;
            case 'S':
                if (validNextCell('N')){
                    addConnection('N');
                }
                if (validNextCell('E')){
                    addConnection('E');
                }
                break;
            case 'W':
                if (validNextCell('S')){
                    addConnection('S');
                }
                if (validNextCell('E')){
                    addConnection('E');
                }
                break;
        }
        removeConnections();

        return connected;
    }

    public boolean turnedWest(){
        return getDirection() == 'S' || getDirection() == 'W';
    }

    public boolean turnedEast(){
        return !turnedWest();
    }

    public boolean turnedNorth(){
        return getDirection() == 'N' || getDirection() == 'W';
    }

    public boolean turnedSouth(){
        return !turnedNorth();
    }

}
