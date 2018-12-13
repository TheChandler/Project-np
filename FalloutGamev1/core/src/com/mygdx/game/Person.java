package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;

public class Person {
    float width,height;
    Random random=new Random();
    Vector3 position;
    Vector3 destination;
    Vector3 velocity;
    int strength,perception,agility;
    float walkSpeed=5;
    String name;
    public Person(){
        name=getRandomName();
        strength=random.nextInt(11);
        perception=random.nextInt(11);
        agility=random.nextInt(11);
        destination=null;
        position=new Vector3(-100,10,0);
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
    public void update(Main m){
        Resources.buyWithFood(.05f);
        if (destination!=null&&Resources.buyWithWater(1)) {
            if (position.epsilonEquals(destination)) {
                addToRoom(m);
                System.out.println("done Walking");
                destination = null;
            } else if (position.x <destination.x) {
                velocity.x = Math.min(destination.x- position.x, walkSpeed);
            } else if (position.x > destination.x) {
                velocity.x = Math.max(destination.x - position.x, -walkSpeed);
            } else if (position.y <destination.y) {
                velocity.y = Math.min(destination.y- position.y, walkSpeed);
            } else if (position.y > destination.y) {
                velocity.y = Math.max(destination.y - position.y, -walkSpeed);
            }

            position.add(velocity);
            velocity.set(0,0,0);
        }
    }
    public void addToRoom(Main m){
        RoomSlot rs=m.getRoomSlot(position);
        if (rs!=null){
            rs.room.add(this);
        }
    };
    public void removeFromRoom(Main m){
        RoomSlot rs=m.getRoomSlot(position);
        if (rs!= null&&rs.room!=null){
            rs.room.remove(this);
        }
    };

    public void render(ShapeRenderer sr){
        sr.setColor(.93f,.82f,.7f,1);
        sr.ellipse(position.x,position.y,width,height);
    }
    public void renderGreen(ShapeRenderer sr){
        sr.setColor(0f,.82f,0f,1);
        sr.ellipse((position.x-4f),(position.y-4f),width+8f,height+8f);
    }
    public String toString(){
        return name+" Strength: "+strength+" Perception: "+perception+" Agility: "+agility;
    }
    public String getRandomName(){
        String[] names ={"Chandler","Anthony","Harold","Bob","Brayton","Alpha","Charlie","Chuck","Dennis","Dead Guy","Goofball","Sarah","Sam","Kelly","Michael","Raymond","Frank","Fuck face","Violet","Jimmy","Mac","Angela","Sandy"};
        return names[random.nextInt(names.length)];
    }

}
