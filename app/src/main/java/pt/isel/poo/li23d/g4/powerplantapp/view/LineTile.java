package pt.isel.poo.li23d.g4.powerplantapp.view;

import android.graphics.Canvas;

import pt.isel.poo.li23d.g4.powerplantapp.model.Cell;

public class LineTile extends CellTile {

    public LineTile(Cell c){
       super(c);
    }

    public void draw(Canvas canvas, int side){
        paint();
        switch(cell.getDirection()){
            case 'E':
            case 'W':
                canvas.drawRect(0, side/3, side, 2*side/3, paint);
                break;

            case 'N':
            case 'S':
                canvas.drawRect(side/3, 0, 2*side/3, side, paint);
                break;

        }
    }
}
