package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

public class RoomSlot {
    Room room;
    Button button;
    public RoomSlot(Room r,Button b) {
        room = r;
        button = b;
    }
    public void update(Vector3 mouse){
        if (room!=null){
            room.update();
        }
        button.update(mouse);
    }
    public void render(ShapeRenderer sr){
        button.box.render(sr);
        if (room!=null) {
            room.render(sr);
        }
    }
}
