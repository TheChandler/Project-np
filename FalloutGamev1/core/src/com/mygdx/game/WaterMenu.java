package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;

public class WaterMenu extends Menu {
    public WaterMenu() {
        isOpen=false;
        background=new Button(new Box(860,0,800,900));
        background.box.setColor(new Color(.5f,.5f,1f,255));
        setupButtons();
    }
    @Override
    public void setupButtons(){
        buttons=new Button[2];
        buttons[0]=new Button(new Box(700,800,100,100));
        buttons[0].box.setColor(Color.RED);
        buttons[1]=new Button(new Box(100,400,600,90));
        buttons[1].box.setColor(Color.GRAY);
    }
    @Override
    public int click(Vector3 mouse,Main main){
        int choice = getSelected(mouse);
        switch (choice) {
            case 0:
                close();
                break;
            case 1:
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
}
