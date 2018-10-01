package com.mygdx.game;

import com.badlogic.gdx.math.Vector3;


public class Button {
    Box box;
    boolean over,clicked;
    public Button(Box b){
        this.box=b;
        over=false;
    }
    public void update(Vector3 mouse){
        if (mouse.x>box.x&&mouse.x<box.x+box.width&&mouse.y>box.y&&mouse.y<box.y+box.height){
            this.over=true;
        }else{
            this.over=false;
        }
    }
    public boolean isOver(Vector3 mouse,float x,float y) {
        if (mouse.x > box.x+x && mouse.x < box.x +x+ box.width && mouse.y > box.y+y && mouse.y < box.y +y+ box.height)
            return true;
        return false;
    }
}
