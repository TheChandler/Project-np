package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

public class Box {
    float x,y,width,height;
    Color color;
    public Box(float x,float y,float w,float h){
        this.x=x;
        this.y=y;
        this.width=w;
        this.height=h;
    }
    public String toString(){
        return "Box: "+x+" "+y+" "+width+" "+height;
    }
    public void setColor(Color c){
        color=c;
    }
    public void render(ShapeRenderer sr){
        if (this.color!=null){
            sr.setColor(this.color);
        }else{
            sr.setColor(Color.GRAY);
        }
        sr.rect(x,y,width,height);
    }
    public void menuRender(ShapeRenderer sr,float x,float y){
        if (this.color!=null){
            sr.setColor(this.color);
        }
        sr.rect(this.x+x,this.y+y,width,height);
    }
}
