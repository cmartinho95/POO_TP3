package pt.isel.poo.li23d.g4.powerplantapp.model;

import java.util.*;
import java.lang.*;

public abstract class Cell {
    protected static Plant model;

    private int line;
    private int column;
    private char[] directions = {'N', 'E', 'S', 'W'}; // north, east, south, west
    private int d = 4;
    private char currentDirection = ' ';
    private final boolean actionable;
    private final boolean powerCell;

    private static ArrayList<Character> classLetters = new ArrayList<>();
    private static ArrayList<String> classNames = new ArrayList<>();
    public static final String PACKAGE_PATH = "pt.isel.poo.li23d.g4.powerplantapp.model.";

    private boolean connected;
    private boolean powered;
    private boolean checked;

    protected ArrayList<Cell> connections = new ArrayList<>();
    protected ArrayList<Cell> previousConnections = new ArrayList<>();

    public abstract boolean checkConnections();
    public abstract boolean turnedWest();
    public abstract boolean turnedEast();
    public abstract boolean turnedNorth();
    public abstract boolean turnedSouth();


    public Cell(boolean actionable, boolean powerCell){
        this.actionable = actionable;
        this.powerCell = powerCell;

        if(powerCell){
            setPowered(true);
        }
    }
    /* GETTERS E SETTERS */

    public void setCoordinates(int lin, int col){
        line = lin;
        column = col;
    }
    public int[] getCoordinates(){
        int[] coords = {line, column};
        return coords;
    }

    public boolean isActionable() {
        return actionable;
    }

    public boolean isPowered(){
        return powered;
    }

    public void setPowered(boolean value){
        powered = value;
    }

    public boolean isPowerCell(){
        return powerCell;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isChecked() {
        return checked;
    }

    public boolean isConnected(){
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }


    public char getDirection() {
        return currentDirection;
    }

    public void setDirection(char d) {
        currentDirection = d;
    }

    /* CORPO DA CLASSE*/

    public static void setClasses(ArrayList<Character> letters, ArrayList<String> classes){
        classLetters = letters;
        classNames = classes;
    }

    public static Cell newInstance(char c){
        try{
            int i = classLetters.indexOf(c);
            String classname = classNames.get(i);
            Class cellType = Class.forName(PACKAGE_PATH + classname);

            return (Cell)cellType.newInstance();
        }
        catch(Exception e){
            System.out.println("Error creating new Cell object"+ ": " + e.getMessage());
        }
        return null;
    }

    public void nextDirection() {
        if (currentDirection == ' ') {  //inicio do jogo
            d = (int) (Math.random() * directions.length);  // seleciona direcao aleatoria para o tile
        } else {
            d++;
            if (d == 4) {
                d = 0;
            }
        }
        currentDirection = directions[d];
    }

    public boolean touch() {
        if(!actionable) return false;
        nextDirection();
        checkConnections();

        return true;
    }

    protected boolean validNextCell(char dir){
        switch(dir){
            case 'S':
                if (line - 1 >= 0 && model.getCell(line - 1, column).turnedSouth()){
                    return true;
                }
                break;
            case 'N':
                if (line + 1 < model.getHeight() && model.getCell(line + 1, column).turnedNorth()){
                    return true;
                }
                break;
            case 'E':
                if (column - 1 >= 0 && model.getCell(line, column - 1).turnedEast()){
                    return true;
                }
                break;
            case 'W':
                if (column + 1 < model.getWidth() && model.getCell(line, column + 1).turnedWest()){
                    return true;
                }
                break;
        }
        return false;
    }

    public void addConnection(char dir){
        int[] coords = getCoordinates();
        int line = coords[0];
        int column = coords[1];

        switch(dir) {
            case 'S':
                if(validNextCell('S')){
                    if(!connections.contains(model.getCell(line - 1, column))){
                        connections.add(model.getCell(line - 1, column));
                    }
                    if (!model.getCell(line - 1, column).connections.contains(this)) {
                        model.getCell(line - 1, column).connections.add(this);
                    }
                    connected = true;
                    model.getCell(line - 1, column).connected = true;
                }
                break;
            case 'W':
                if(validNextCell('W')){
                    if(!connections.contains(model.getCell(line, column + 1))){
                        connections.add(model.getCell(line, column + 1));
                    }
                    if(!model.getCell(line, column + 1).connections.contains(this)) {
                        model.getCell(line, column + 1).connections.add(this);
                    }
                    connected = true;
                    model.getCell(line, column + 1).connected = true;
                }
                break;
            case 'N':
                if (validNextCell('N')){
                    if (!connections.contains(model.getCell(line + 1, column))){
                        connections.add(model.getCell(line + 1, column));
                    }
                    if(!model.getCell(line + 1, column).connections.contains(this)) {
                        model.getCell(line + 1, column).connections.add(this);
                    }
                    connected = true;
                    model.getCell(line + 1, column).connected = true;
                }
                break;
            case 'E':
                if(validNextCell('E')){
                    if (!connections.contains(model.getCell(line, column - 1))){
                        connections.add(model.getCell(line, column - 1));
                    }
                    if(!model.getCell(line, column - 1).connections.contains(this)) {
                        model.getCell(line, column - 1).connections.add(this);
                    }
                    connected = true;
                    model.getCell(line, column - 1).connected = true;
                }
                break;
        }
    }

    public boolean canBeConnected(Cell adjacent){
        int[] coords = adjacent.getCoordinates();
        int l = coords[0]; int c = coords[1];

        if (l < line){ // adjacent to the North
            return adjacent.turnedSouth() && this.turnedNorth();
        }
        else if (l > line){ // adjacent to the South
            return adjacent.turnedNorth() && this.turnedSouth();
        }
        else if (c < column){ //adjacent to West
            return adjacent.turnedEast() && this.turnedWest();
        }
        else if (c > column){ // adjacent to East
            return adjacent.turnedWest() && this.turnedEast();
        }
        else{
            return false;
        }
    }

    public void removeConnections(){
        for (int i = 0; i < previousConnections.size(); i++){  // checks if any cells were disconnected from current and removes each from connections of each other
            if (!canBeConnected(previousConnections.get(i))){
                connections.remove(previousConnections.get(i));
                previousConnections.get(i).connections.remove(this);

                if (previousConnections.get(i).connections.size() == 0) {
                    previousConnections.get(i).connected = false;
                }
            }
        }
        if (connections.size() == 0) {
            connected = false;
        }
        if (!connected) {
            connections.clear();
        }
    }

    public void power(){
        for(int i = 0; i < connections.size(); i++){
            if(!connections.get(i).isChecked() && !connections.get(i).isPowerCell()){
                connections.get(i).setPowered(true);
                connections.get(i).setChecked(true);
                connections.get(i).power();
            }
        }
    }
}
