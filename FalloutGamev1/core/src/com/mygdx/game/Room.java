package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Room {
    private float amountDone;
    private int totalAmount;
    private float increaseAmount;
    private float roomLength;
    private String type;
    private float x,y;
    Color backgroundColor;
    Color progressColor;
    public Room(float x,float y,String type){
        totalAmount=1000;
        amountDone=0;
        increaseAmount=10;
        roomLength=500;
        this.type=type;
        this.x=x;
        this.y=y;

        if (this.type.compareTo("POWER")==0){
            backgroundColor=Colors.darkRed;
            progressColor=(Color.RED);
        }else if (this.type.compareTo("WATER")==0){
            backgroundColor=Colors.darkBlue;
            progressColor=(Color.BLUE);
        }else if (this.type.compareTo("FOOD")==0){
            backgroundColor=Colors.darkGreen;
            progressColor=(Color.GREEN);
        }
    }
    public void update(){
        amountDone+=increaseAmount;
        if (amountDone>totalAmount){
            amountDone=0;
            if (this.type.compareTo("POWER")==0){
                Resources.addPower(totalAmount/10);
            }else if (this.type.compareTo("WATER")==0){
                Resources.addWater(totalAmount/10);
            }else if (this.type.compareTo("FOOD")==0){
                Resources.addFood(totalAmount/10);
            }
        }
    }
    public void render(ShapeRenderer sr){
        sr.setColor(backgroundColor);
        sr.rect(x,y,500,250);
        sr.setColor(progressColor);
        sr.rect(x,y,roomLength*(amountDone/totalAmount),250);
    }
}
