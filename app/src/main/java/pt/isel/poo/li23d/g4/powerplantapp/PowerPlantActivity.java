package pt.isel.poo.li23d.g4.powerplantapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import pt.isel.poo.li23d.g4.powerplantapp.model.*;
import pt.isel.poo.li23d.g4.powerplantapp.view.*;

import java.util.InputMismatchException;
import java.util.Scanner;

import pt.isel.poo.tile.OnTileTouchListener;
import pt.isel.poo.tile.TilePanel;


public class PowerPlantActivity extends AppCompatActivity implements Plant.ModelListener {
    private static final String LEVELS_FILE = "levels.txt";
    private static final int LEVELS = 3;
    private Plant model;
    private TilePanel panel;
    private TextView levelView;
    private TextView movesView;
    private TextView winMessage;
    private Button nextLevel;
    private int level;
    private boolean end;

    public static Img PowerImg, PoweredHouseImg, UnpoweredHouseImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        panel = findViewById(R.id.panel);
        panel.setListener(new ViewListener());
        movesView = findViewById(R.id.movesView);
        levelView = findViewById(R.id.levelView);
        nextLevel = findViewById(R.id.nextLevel);
        nextLevel.setClickable(false);
        nextLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!end){
                    newLevel();
                }
                else{
                    finish();
                    System.exit(0);
                }
            }
        });
        winMessage = findViewById(R.id.winMessage);
        nextLevel.setVisibility(View.INVISIBLE);
        winMessage.setVisibility(View.INVISIBLE);

        PowerImg = new Img(this, R.drawable.power);
        PoweredHouseImg = new Img(this, R.drawable.house_on);
        UnpoweredHouseImg = new Img(this, R.drawable.house_off);

        loadLevel(1);
        playLevel();
    }

    private class ViewListener implements OnTileTouchListener {
        public boolean onClick(int xTile, int yTile){
            model.touch(yTile,xTile);
            return true;
        }

        public boolean onDrag(int xFrom, int yFrom, int xTo, int yTo){
            return false;}

        public void onDragEnd(int x, int y){}

        public void onDragCancel(){}
    }

    private boolean playLevel() {
        for (int l = 0; l < model.getHeight(); l++) {
            for (int c = 0; c < model.getWidth(); c++) {
                panel.setTile(c,l, CellTile.newInstance(model.getCell(l,c)));
            }
        }
        return true;
    }

    private boolean loadLevel(int n) {
        Scanner in = null;
        try {
            in = new Scanner(getResources().openRawResource(R.raw.levels)); // Scanner to read the file
            model = new Loader(in).load(n);                     // Load level from scanner
            panel.setSize(model.getWidth(),model.getHeight());
            model.setModelListener(this);
            movesView.setText("0");
            levelView.setText("" + n);
            level = n;
            end = false;

            return true;
        } catch (InputMismatchException e) {
            System.out.println("Error loading file \""+LEVELS_FILE+"\":\n"+e.getMessage());
            return false;
        } catch (Loader.LevelFormatException e) {
            System.out.println(e.getMessage()+" in file \""+LEVELS_FILE+"\"");
            System.out.println(" "+e.getLineNumber()+": "+e.getLine());
            return false;
        } finally {
            if (in!=null) in.close();   // Close the file
        }
    }

    private boolean winLevel() {
        if (model.isCompleted()) {
            winMessage.setText("Well done!");
            nextLevel.setText("Next level");
            winMessage.setVisibility(View.VISIBLE);
            nextLevel.setVisibility(View.VISIBLE);
            nextLevel.setClickable(true);
            winMessage.invalidate();
            nextLevel.invalidate();

            return true;
        }
        return false;
    }

    private void newLevel(){
        if (++level <= LEVELS){
            loadLevel(++level);
            playLevel();
            winMessage.setVisibility(View.INVISIBLE);
            nextLevel.setVisibility(View.INVISIBLE);
            nextLevel.setClickable(false);
        }
        else{
            winMessage.setText("No more levels available");
            end = true;
            nextLevel.setText("Exit");
            winMessage.setVisibility(View.VISIBLE);
            nextLevel.setVisibility(View.VISIBLE);
            winMessage.invalidate();
            nextLevel.invalidate();
        }
    }

    public boolean cellChanged(int lin, int col, Cell cell) {
        if (!winLevel()){
            movesView.setText(model.getMoves() + "");
            movesView.invalidate();
            panel.invalidate(lin, col);

            return true;
        }
        return false;
    }
}
