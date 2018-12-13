package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

public class WalkingPoints {
    WalkingPoint[] points;
    int counter;
    public WalkingPoints(){
        points =new WalkingPoint[1000];
        counter=1;
        points[0]=new WalkingPoint( -100,10);
    }
    public void addPoint(Vector3 point){
        points[counter]=new WalkingPoint(point.x,point.y);
        counter++;
    }
    public void removePoint(Vector3 point){
        for (int i=0;i<counter;i++){
            if (points[i].position.dst(point)==0){
                removePoint(i);
            }
        }
    }
    public void removePoint(int r){
        for (int i=r;i<counter;i++){
            points[i]=points[i+1];
        }
        counter--;
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
