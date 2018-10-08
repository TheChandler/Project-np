package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

public class Person {
    float width,height;
    Vector3 position;
    Vector3 destination;
    Vector3 velocity;
    public Person(){
        destination=null;
        position=new Vector3(0,2345,0);
        velocity=new Vector3(0,0,0);
        width=50;
        height=200;
    }
    public boolean clicked(Vector3 mouse){
        return (mouse.x>position.x&&mouse.x<position.x+width&&mouse.y>position.y&&mouse.y<position.y+height);
    }
    public void setDestination(Vector3 d){
        destination=d.cpy();
    }
    public void update(){
        if (destination!=null) {
            System.out.println(destination);
            System.out.println(position);
            if (position.epsilonEquals(destination)) {
                destination = null;
            } else if (position.x <destination.x) {
                velocity.x = Math.min(destination.x- position.x, 10);
            } else if (position.x > destination.x) {
                velocity.x = Math.max(destination.x - position.x, -10);
            }

            position.add(velocity);
            velocity.set(0,0,0);
        }
    }
    public void render(ShapeRenderer sr){
        sr.setColor(.93f,.82f,.7f,1);
        sr.ellipse(position.x,position.y,width,height);
    }
    public void renderGreen(ShapeRenderer sr){
        sr.setColor(0f,.82f,0f,1);
        sr.ellipse((position.x-1f),(position.y-1f),width+2f,height+2f);
    }

}
