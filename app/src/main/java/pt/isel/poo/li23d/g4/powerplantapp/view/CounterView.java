package pt.isel.poo.li23d.g4.powerplantapp.view;

import android.content.Context;
import android.util.AttributeSet;

import pt.isel.poo.tile.TileView;

public class CounterView extends TileView {
    private String text;
    private int value;

    public CounterView(Context context){
        super(context);
    }

    public CounterView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setText(String s){
        text = s;
    }

    public void setValue(int v){
        value = v;
    }

    public void increment(){
        value++;
    }
}
