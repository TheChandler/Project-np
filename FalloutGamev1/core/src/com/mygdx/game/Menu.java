package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

public class Menu {
    Button background;
    boolean isOpen;
    RoomSlot roomSlot;
    float roomSlotx,roomSloty;
    Button[] buttons;
    public Menu (){
        roomSlotx=0;
        roomSloty=0;
        isOpen=false;
        background=new Button(new Box(860,0,800,900));
        background.box.setColor(new Color(.5f,.5f,.75f,255));
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
    public int click(Vector3 mouse,Main main){
        int choice = getSelected(mouse);
        switch (choice) {
            case 0:
                close();
                break;
            case 1:
                roomSlot.room = new Room(roomSlotx,roomSloty, "POWER");
                main.wp.addPoint(new Vector3(roomSlotx + 250, roomSloty + 10, 0));
                close();
                break;
            case 2:
                roomSlot.room = new Room(roomSlotx,roomSloty, "WATER");
                main.wp.addPoint(new Vector3(roomSlotx + 250, roomSloty + 10, 0));
                close();
                break;
            case 3:
                roomSlot.room = new Room(roomSlotx,roomSloty, "FOOD");
                main.wp.addPoint(new Vector3(roomSlotx + 250, roomSloty + 10, 0));
                close();
                break;
            case 4:
                if (roomSlot.room != null) {
                    if (roomSlot.room.dispose()) {
                        roomSlot.room = null;
                        main.wp.removePoint(new Vector3(roomSlotx + 250, roomSloty + 10, 0));
                        close();
                    }
                }
                break;
        }
        return -1;

    }
    public int getSelected(Vector3 mouse){
        for (int i =0;i<buttons.length;i++){
            if (buttons[i].isOver(mouse,background.box.x,background.box.y)){
                return i;
            }
        }
        return -1;
    }
    public void open(float x,float y,RoomSlot rs){
        background.box.x=x+510;
        background.box.y=y-250;
        roomSlot=rs;
        roomSlotx=x;
        roomSloty=y;
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
