package pt.isel.poo.li23d.g4.powerplantapp.view;

import android.graphics.Canvas;

import pt.isel.poo.li23d.g4.powerplantapp.PowerPlantActivity;
import pt.isel.poo.li23d.g4.powerplantapp.model.Cell;

public class PowerTile extends PointTile {

    public PowerTile(Cell c){
        super(c);
    }

    public void draw(Canvas canvas, int side){
        super.draw(canvas, side);
        PowerPlantActivity.PowerImg.draw(canvas, side, side, paint);
    }

}
