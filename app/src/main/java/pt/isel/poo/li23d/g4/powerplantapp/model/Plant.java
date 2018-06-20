package pt.isel.poo.li23d.g4.powerplantapp.model;

public class Plant {

    private ModelListener listener;
    private Cell[][] map;
    private int moves;
    private int height, width;
    private Cell current;

    public interface ModelListener {
        boolean cellChanged(int lin, int col);
    }

    public Plant(int h, int w){
        map = new Cell[h][w];
        height = h;
        width = w;
        Cell.model = this;
        moves = 0;
    }

    /* GETTERS E SETTERS*/

    public void setModelListener(ModelListener l){
        listener = l;
    }

    public int getHeight(){
        return height;
    }

    public int getWidth(){
        return width;
    }

    public Cell getCell(int l, int c){
        return map[l][c];
    }

    public void putCell(int l, int c, Cell cell){
        map[l][c] = cell;
        cell.setCoordinates(l, c);
    }

    public int getMoves(){
        return moves;
    }

    public void setMoves(int value){
        moves = value;
    }

    /* CORPO DA CLASSE*/

    public void init(){
        moves = 0;

        for(int i = 0 ; i < height; i++){
            for (int j = 0; j < width; j++){
                if (map[i][j].isActionable()){
                    map[i][j].nextDirection(); // random direction
                }
            }
        }
        for(int i = 0 ; i < height; i++){
            for (int j = 0; j < width; j++){
                if (map[i][j].isActionable()){
                    map[i][j].checkConnections();
                }
            }
        }
    }

    public boolean isCompleted(){
        for(int i = 0 ; i < height; i++){
            for (int j = 0; j < width; j++){
                if (map[i][j].isActionable() & !map[i][j].isPowered()){
                    return false;
                }
            }
        }
        return true;
    }

    public void cutPower(){
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                if(!map[i][j].isPowerCell()){
                    map[i][j].setPowered(false);
                    map[i][j].setChecked(false);
                }
                map[i][j].checkConnections();
                if(!map[i][j].isPowerCell()){
                    map[i][j].setPowered(false);
                    map[i][j].setChecked(false);
                }
            }
        }
    }

    public void connectPower(){
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                if(map[i][j].isPowerCell()){
                    map[i][j].power();
                    map[i][j].setChecked(true);
                }
            }
        }
        boolean end = false;
        for(int i = 0; i < height; i++){
            if (end){
                break;
            }
            for(int j = 0; j < width; j++){
                if (!listener.cellChanged(i, j)){
                    end = true;
                    break;
                }
            }
        }
    }

    public boolean touch(int line, int col){
        current = map[line][col];
        if (current.touch()){
            moves++;
            cutPower();
            connectPower();
        }
        listener.cellChanged(line, col);
        return true;
    }
 }
