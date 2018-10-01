package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

public class Main implements State {
    ShapeRenderer sr=new ShapeRenderer();
    RoomSlot[] boxes=new RoomSlot[100];
    OrthographicCamera cam;
    Vector3 mouseCords;
    Menu menu;

    public Main(){
        menu=new Menu();
        cam = new OrthographicCamera(1920,1080);
        for (int i = 0 ;i <10;i++){
            for (int j=0;j<10;j++){
                boxes[i*10+j]=new RoomSlot(null,new Button(new Box((i-3)*510,j*260,500,250)));
            }
        }
        cam.position.set(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2,0);
        cam.update();

    }
    @Override
    public void update() {
        ClickDetector.update();
        mouseCords=new Vector3(Gdx.input.getX(),Gdx.input.getY(),0);//new Vector3 that holds mouse coordinates
        mouseCords=cam.unproject(mouseCords);//translates mouse coordinates into world space

        if (menu.isOpen){
            menu.update(mouseCords,cam.position);
            if (ClickDetector.clicked){
                if (menu.background.over){
                    menu.click(mouseCords);
                }else {
                    menu.close();
                }
            }
            for (int i =0;i<boxes.length;i++){
                boxes[i].update(mouseCords);
            }
        }else {
            cam.translate(-Gdx.input.getDeltaX(), Gdx.input.getDeltaY());
            for (int i = 0; i < boxes.length; i++) {
                boxes[i].update(mouseCords);
                if (ClickDetector.clicked && boxes[i].button.over) {
                    menu.open(boxes[i]);
                    cam.position.set(boxes[i].button.box.x + 510, boxes[i].button.box.y + 220, 0);
                }

            }
        }

    }

    @Override
    public void render() {
        cam.update();
        sr.setProjectionMatrix(cam.combined);
        sr.begin(ShapeRenderer.ShapeType.Filled);
        for (int i =0;i<boxes.length;i++) {
            boxes[i].render(sr);
        }
        if (menu.isOpen){
            menu.render(sr);
        }
        Resources.render(sr,cam.position);
        sr.end();
    }
}
