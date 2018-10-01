package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

public class Menu {
    Button background;
    boolean isOpen;
    RoomSlot roomSlot;
    Button[] buttons;
    public Menu (){
        isOpen=false;
        background=new Button(new Box(860,0,800,900));
        background.box.setColor(new Color(1,.5f,.5f,255));
        setupButtons();
    }
    public void setupButtons(){
        buttons=new Button[5];
        buttons[0]=new Button(new Box(700,800,100,100));
        buttons[0].box.setColor(Color.RED);
        buttons[1]=new Button(new Box(100,700,600,90));
        buttons[1].box.setColor(Color.RED);
        buttons[2]=new Button(new Box(100,600,600,90));
        buttons[2].box.setColor(Color.BLUE);
        buttons[3]=new Button(new Box(100,500,600,90));
        buttons[3].box.setColor(Color.GREEN);
        buttons[4]=new Button(new Box(100,400,600,90));
        buttons[4].box.setColor(Color.GRAY);
    }
    public void click(Vector3 mouse){
        if (buttons[0].isOver(mouse,background.box.x,background.box.y)){
            close();
        }else if (buttons[1].isOver(mouse,background.box.x,background.box.y)){
            roomSlot.room=new Room(roomSlot.button.box.x,roomSlot.button.box.y,"POWER");
            roomSlot.button.box.setColor(Colors.darkRed);
            close();
        }else if (buttons[2].isOver(mouse,background.box.x,background.box.y)){
            roomSlot.room=new Room(roomSlot.button.box.x,roomSlot.button.box.y,"WATER");
            roomSlot.button.box.setColor(Colors.darkBlue);
            close();
        }else if (buttons[3].isOver(mouse,background.box.x,background.box.y)){
            roomSlot.room=new Room(roomSlot.button.box.x,roomSlot.button.box.y,"FOOD");
            roomSlot.button.box.setColor(Colors.darkGreen);
            close();
        }else if (buttons[4].isOver(mouse,background.box.x,background.box.y)){
            roomSlot.room=null;
            roomSlot.button.box.setColor(new Color(.5f,.5f,.5f,1));
            close();
        }
    }
    public void open(RoomSlot rs){
        System.out.println("Opening box: "+rs.button.box);
        roomSlot=rs;
        background.box.x=rs.button.box.x+510;
        background.box.y=rs.button.box.y-250;
        isOpen=true;
    }
    public void close(){
        isOpen=false;
    }
    public void update(Vector3 mouse,Vector3 camPosition){
        if (isOpen) {
            background.update(mouse);
            for (int i=0;i<buttons.length;i++){
                buttons[i].update(mouse);
            }
        }
    }
    public void render(ShapeRenderer sr){
        background.box.render(sr);
        for (int i=0;i<buttons.length;i++){
            buttons[i].box.menuRender(sr,background.box.x,background.box.y);
        }
    }
}
