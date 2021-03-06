package pt.isel.poo.li23d.g4.powerplantapp.view;

import android.graphics.Canvas;

import pt.isel.poo.li23d.g4.powerplantapp.PowerPlantActivity;
import pt.isel.poo.li23d.g4.powerplantapp.model.Cell;

public class HouseTile extends PointTile {

    public HouseTile(Cell c){
        super(c);
    }

    public void draw(Canvas canvas, int side){
        super.draw(canvas, side);
        if (cell.isPowered()){
            PowerPlantActivity.PoweredHouseImg.draw(canvas, side, side, paint);
        }
        else{
            PowerPlantActivity.UnpoweredHouseImg.draw(canvas, side, side, paint);
        }
    }
}
