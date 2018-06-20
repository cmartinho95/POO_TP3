package pt.isel.poo.li23d.g4.powerplantapp.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.lang.reflect.Constructor;

import pt.isel.poo.li23d.g4.powerplantapp.model.Cell;
import pt.isel.poo.tile.Tile;

public abstract class CellTile implements Tile {
    protected Cell cell;
    public Paint paint;
    public static final String PACKAGE_PATH = "pt.isel.poo.li23d.g4.powerplantapp.view.";

    protected CellTile(Cell cell) {
        this.cell = cell;
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);

        paint();
    }

    public static CellTile newInstance(Cell c){
        try{
            Class type = c.getClass();
            String path = type.getName();
            String name = path.substring(Cell.PACKAGE_PATH.length(),path.length() - 4); //e.g. pt.isel.poo.li23d.g4.powerplantapp.model.HouseCell >> House
            Class tile = Class.forName(PACKAGE_PATH + name + "Tile");
            Constructor<?> ctor = tile.getConstructor(Cell.class);

            return (CellTile)ctor.newInstance(c);
        }
        catch (Exception e){
            System.out.println("Error creating new CellTile object"+ ": " + e.getMessage());
        }
        return null;
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
    }

}
