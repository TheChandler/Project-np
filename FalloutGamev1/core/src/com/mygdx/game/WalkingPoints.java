package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

public class WalkingPoints {
    WalkingPoint[] points;
    int counter;
    public WalkingPoints(){
        points =new WalkingPoint[1000];
        points[0]=new WalkingPoint(100,2345);
        points[1]=new WalkingPoint(400,2345);
        counter=2;
        connectPoints(0,1);
    }
    public void addPoint(Vector3 point){
        points[counter]=new WalkingPoint(point.x,point.y);
        counter++;
    }
    public void connectPoints(int one,int two){
        points[one].addConnection(points[two]);
        points[two].addConnection(points[one]);
    }
    public void render(ShapeRenderer sr){
        for (int i = 0; i<points.length;i++){
            if (points[i]!=null){
                points[i].render(sr);
            }
        }
    }
    public WalkingPoint findClosest(Vector3 mouse){
        WalkingPoint holder= points[0];

        for (int i =0;i<points.length;i++){
            if (points[i]!=null&&mouse.dst(points[i].position)<mouse.dst(holder.position)){
                holder=points[i];
            }
        }
        return holder;
    }
}
