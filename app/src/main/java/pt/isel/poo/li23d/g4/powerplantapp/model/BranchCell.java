package pt.isel.poo.li23d.g4.powerplantapp.model;

public class BranchCell extends Cell{

    public BranchCell(){
        super(true, false);
    }

    public boolean checkConnections(){
        previousConnections.clear();
        previousConnections.addAll(connections);
        setConnected(false);

        char dir = getDirection();

        switch(dir) {
            case 'N':
                if (validNextCell('E')){
                    addConnection('E');
                }
                if (validNextCell('S')){
                    addConnection('S');
                }
                if (validNextCell('W')){
                    addConnection('W');
                }
                break;
            case 'E':
                if (validNextCell('S')){
                    addConnection('S');
                }
                if (validNextCell('W')){
                    addConnection('W');
                }
                if (validNextCell('N')){
                    addConnection('N');
                }
                break;
            case 'S':
                if (validNextCell('W')){
                    addConnection('W');
                }
                if (validNextCell('N')){
                    addConnection('N');
                }
                if (validNextCell('E')) {
                    addConnection('E');
                }
                break;
            case 'W':
                if (validNextCell('N')){
                    addConnection('N');
                }
                if (validNextCell('E')) {
                    addConnection('E');
                }
                if (validNextCell('S')){
                    addConnection('S');
                }
                break;
        }
        removeConnections();

        return isConnected();
    }

    public boolean turnedWest(){
        return getDirection() == 'W' || getDirection() == 'N' || getDirection() == 'S';
    }

    public boolean turnedEast(){
        return getDirection() == 'E' || getDirection() == 'N' || getDirection() == 'S';
    }

    public boolean turnedNorth(){
        return getDirection() == 'N' || getDirection() == 'W' || getDirection() == 'E';
    }

    public boolean turnedSouth(){
        return getDirection() == 'S' || getDirection() == 'W' || getDirection() == 'E';
    }


}
