package pt.isel.poo.li23d.g4.powerplantapp.view;

import android.graphics.Canvas;

import pt.isel.poo.li23d.g4.powerplantapp.model.Cell;

public class BranchTile extends CellTile {

    public BranchTile(Cell c){
        super(c);
    }

    public void draw(Canvas canvas, int side){
        paint();
        switch(cell.getDirection()){
            case 'E':
                canvas.drawRect(side/3, 0, 2*side/3, side, paint);
                canvas.drawRect(2*side/3, side/3, side, 2*side/3, paint);
                break;
            case 'W':
                canvas.drawRect(side/3, 0, 2*side/3, side, paint);
                canvas.drawRect(0, side/3, side/3, 2*side/3, paint);
                break;
            case 'N':
                canvas.drawRect(0, side/3, side, 2*side/3, paint);
                canvas.drawRect(side/3, 0, 2*side/3, side/3, paint);
                break;
            case 'S':
                canvas.drawRect(0, side/3, side, 2*side/3, paint);
                canvas.drawRect(side/3, 2*side/3, 2*side/3, side, paint);
                break;
        }
    }
/*
    public void repaint(){
        clear();
        paint();

        switch(cell.getDirection()){
            case 'E':
                print(0, 1, '|');
                print(1, 1, 'o');
                print(1, 2, '-');
                print(2, 1, '|');
                break;

            case 'W':
                print(0, 1, '|');
                print(1, 1, 'o');
                print(1, 0, '-');
                print(2, 1, '|');
                break;

            case 'N':
                print(0, 1, '|');
                print(1, 1, 'o');
                print(1, 0, '-');
                print(1, 2, '-');
                break;

            case 'S':
                print(2, 1, '|');
                print(1, 1, 'o');
                print(1, 0, '-');
                print(1, 2, '-');
                break;

        }
    }
    */
}
