package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

public class Resources {
    public static float power=0,water=0,food=0,maxPower=1,maxWater=1,maxFood=1;
    public static Main main;
    public static void setMain(Main m){
        main=m;
    }
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
        if (food==maxFood){
            main.people.addPerson();
            food=0;
        }
    }
    public static boolean buyWithPower(float p){
        if (power>=p){
            power-=p;
            return true;
        }else{
            return false;
        }
    }
    public static boolean buyWithWater(float w){
        if (water>=w){
            water-=w;
            return true;
        }else{
            return false;
        }
    }
    public static boolean buyWithFood(float f){
        if (food>=f){
            food-=f;
            return true;
        }else{
            return false;
        }
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
