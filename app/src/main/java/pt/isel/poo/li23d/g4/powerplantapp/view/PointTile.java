package pt.isel.poo.li23d.g4.powerplantapp.view;

import android.graphics.Canvas;

import pt.isel.poo.li23d.g4.powerplantapp.model.Cell;

public class PointTile extends CellTile {

    public PointTile(Cell c) {
        super(c);
    }

    public void draw(Canvas canvas, int side){
        paint();

        switch(cell.getDirection()){
            case 'E':
                canvas.drawRect(side/3, side/3, side, 2*side/3, paint);
                break;
            case 'W':
                canvas.drawRect(0, side/3, 2*side/3, 2*side/3, paint);
                break;
            case 'N':
                canvas.drawRect(side/3, 0, 2*side/3, 2*side/3, paint);
                break;
            case 'S':
                canvas.drawRect(side/3, side/3, 2*side/3, side, paint);
                break;

        }
    }
}
