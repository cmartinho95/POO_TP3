package pt.isel.poo.li23d.g4.powerplantapp.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import pt.isel.poo.li23d.g4.powerplantapp.model.Cell;
import pt.isel.poo.li23d.g4.powerplantapp.model.*;
import pt.isel.poo.tile.Tile;

public abstract class CellTile implements Tile {
    public static final int SIDE = 3;
    protected Cell cell;
    public Paint paint;

    protected CellTile(Cell cell) {
        this.cell = cell;
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);

        if (cell.isPowered()){
            paint.setColor(Color.GREEN);
        }
        else{
            paint.setColor(Color.DKGRAY);
        }
    }

    public static CellTile newInstance(Cell c){
        if (c instanceof HouseCell){
            return new HouseTile(c);
        }
        else if (c instanceof LineCell){
            return new LineTile(c);
        }
        else if (c instanceof PowerCell){
            return new PowerTile(c);
        }
        else if (c instanceof CurveCell){
            return new CurveTile(c);
        }
        else if (c instanceof BranchCell){
            return new BranchTile(c);
        }
        else return new SpaceTile(c);
    }

    public void paint() {
        if (cell.isPowered()){
            paint.setColor(Color.GREEN);
        }
        else{
            paint.setColor(Color.DKGRAY);
        }
    }

    public abstract void draw(Canvas canvas, int side);

    public boolean setSelect(boolean selected){
        return true;
        //TODO
    }

}
