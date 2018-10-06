package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

public class Resources {
    public static int power=0,water=0,food=0,maxPower=1000,maxWater=1000,maxFood=1000;
    public static void addPower(int p){
        power+=p;
        power=Math.min(power,maxPower);
    }
    public static void addWater(int w ){
        water+=w;
        water=Math.min(water,maxWater);
    }
    public static void addFood(int f){
        food+=f;
        food=Math.min(food,maxFood);
    }
    public static void addMaxPower(int p){
        maxPower+=p;
    }
    public static void addMaxWater(int w){
        maxWater+=w;
    }
    public static void addMaxFood(int f){
        maxFood+=f;
    }
    public static void render(ShapeRenderer sr, Vector3 cam){
        sr.setColor(Colors.darkRed);
        sr.rect(cam.x-770,cam.y+475,500,50);
        sr.setColor(Colors.darkGreen);
        sr.rect(cam.x-250,cam.y+475,500,50);
        sr.setColor(Colors.darkBlue);
        sr.rect(cam.x+270,cam.y+475,500,50);

        sr.setColor(Color.RED);
        sr.rect(cam.x-770,cam.y+475,500*power/maxPower,50);
        sr.setColor(Color.GREEN);
        sr.rect(cam.x-250,cam.y+475,500*food/maxFood,50);
        sr.setColor(Color.BLUE);
        sr.rect(cam.x+270,cam.y+475,500*water/maxWater,50);
    }
}
