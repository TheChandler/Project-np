package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Room {
    private float amountDone;
    private int totalAmount;
    private float productionRate;
    private float roomLength;
    private String type;
    private float x,y;
    Color backgroundColor;
    Color progressColor;
    public Room(float x,float y,String type){
        totalAmount=1000;
        amountDone=0;
        productionRate=1;
        roomLength=500;
        this.type=type;
        this.x=x;
        this.y=y;

        if (this.type.compareTo("POWER")==0){
            Resources.addMaxPower(1000);
            backgroundColor=Colors.darkRed;
            progressColor=(Color.RED);
        }else if (this.type.compareTo("WATER")==0){
            Resources.addMaxWater(1000);
            backgroundColor=Colors.darkBlue;
            progressColor=(Color.BLUE);
        }else if (this.type.compareTo("FOOD")==0){
            Resources.addMaxFood(1000);
            backgroundColor=Colors.darkGreen;
            progressColor=(Color.GREEN);
        }
    }
    public String getType(){
        return type;
    }
    public void update(){
        if (type.compareTo("POWER")==0||Resources.buyWithPower(.1f)) {
            amountDone += productionRate;
        }
        if (amountDone>=totalAmount){
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
    public void add(Person p){
        if (this.type.compareTo("POWER")==0){
            productionRate+=p.strength;
        }else if (this.type.compareTo("WATER")==0){
            productionRate+=p.perception;
        }else if (this.type.compareTo("FOOD")==0){
            productionRate+=p.agility;
        }
    }
    public void remove(Person p){
        if (this.type.compareTo("POWER")==0){
            productionRate-=p.strength;
        }else if (this.type.compareTo("WATER")==0){
            productionRate-=p.perception;
        }else if (this.type.compareTo("FOOD")==0){
            productionRate-=p.agility;
        }
    }
    public boolean dispose(){
        if (this.productionRate==1) {
            if (this.type.compareTo("POWER") == 0) {
                Resources.addMaxPower(-1000);
            } else if (this.type.compareTo("WATER") == 0) {
                Resources.addMaxWater(-1000);
            } else if (this.type.compareTo("FOOD") == 0) {
                Resources.addMaxFood(-1000);
            }
            return true;
        }
        return false;
    }
    public void render(ShapeRenderer sr){
        sr.setColor(backgroundColor);
        sr.rect(x,y,500,250);
        sr.setColor(progressColor);
        sr.rect(x,y,roomLength*(amountDone/totalAmount),250);
    }
}
