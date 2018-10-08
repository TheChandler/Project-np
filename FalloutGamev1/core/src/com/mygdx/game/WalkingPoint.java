package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

public class WalkingPoint {
    Vector3 position;
    WalkingPoint[] connectedTo;
    public WalkingPoint(float x, float y){
        position=new Vector3(x,y,0);
        connectedTo=new WalkingPoint[4];
    }
    public boolean addConnection(WalkingPoint wp){
        for (int i = 0 ; i<connectedTo.length;i++){
            if (connectedTo[i]==null){
                connectedTo[i]=wp;
                return true;
            }
        }
        return false;
    }
    public void render(ShapeRenderer sr){
        sr.setColor(Color.RED);
        sr.ellipse(position.x-5,position.y-5,10,10);
        for (int i=0;i<connectedTo.length;i++){
            if (connectedTo[i]!=null){
                sr.line(position.x,position.y,connectedTo[i].position.x,connectedTo[i].position.y);
            }
        }
    }
}
