package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

public class Main implements State {
    ShapeRenderer sr=new ShapeRenderer();
    RoomSlot[][] roomSlots=new RoomSlot[10][10];
    OrthographicCamera cam;
    Vector3 mouseCords;
    Menu menu;
    Vector3 lastCamPosition;
    boolean camMoved;
    Person[] persons;
    int numPeople;
    int selectedPerson;
    WalkingPoints wp;
    public Main(){
        wp=new WalkingPoints();
        selectedPerson=-1;
        persons=new Person[100];
        persons[0]=new Person();
        numPeople=1;
        menu=new Menu(); //menu that pops up when you click on a rectangle
        cam = new OrthographicCamera(1920,1080);//every device will show 1920 by 1080 pixels

        //This for loop creates 100 roomslots. A room slot is a combination of a room and a button
        for (int i = 0 ;i <roomSlots.length;i++){
            for (int j=0;j<roomSlots[i].length;j++){
                roomSlots[i][j]=new RoomSlot(null,new Button(new Box((i)*510,j*260,500,250)));
            }
        }
        //this just repositions the camera initially. It's not necessary.
        cam.position.set(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2,0);
        cam.update();
        lastCamPosition=cam.position.cpy();
        camMoved=false;

    }
    @Override
    public void update() {
        //I wrote my own static detector class that tells when a click has occurred
        ClickDetector.update();
        mouseCords=new Vector3(Gdx.input.getX(),Gdx.input.getY(),0);//new Vector3 that holds mouse coordinates
        mouseCords=cam.unproject(mouseCords);//translates mouse coordinates into world space


        for (int i =0 ;i<numPeople;i++){
            persons[i].update();
        }

        //updates boxes. Generates resources.
        for (int i =0;i<roomSlots.length;i++){
            for (int j=0;j<roomSlots[i].length;j++) {
                roomSlots[i][j].update(mouseCords);
            }
        }
        //detects if the camera moved in the last frame. Should probably be under cam.translate, but it doesn't work there as intended
        didCamMove();
        if (selectedPerson!=-1) {
            if (ClickDetector.clicked){
                persons[selectedPerson].setDestination(wp.findClosest(mouseCords).position);
                selectedPerson=-1;
            }
        }else if (menu.isOpen){
            //updates buttons to tell if mouse is over the menu
            menu.update(mouseCords,cam.position);
            if (ClickDetector.clicked){
                //if you click on the menu it interacts with the menu. If you click off the menu the menu closes.
                if (menu.background.over){
                    menu.click(mouseCords);
                }else {
                    menu.close();
                }
            }
        }else {
            //If the menu is closed the camera can be moved by dragging.
            cam.translate(-Gdx.input.getDeltaX(), Gdx.input.getDeltaY());

            if (ClickDetector.clicked&&checkPersonsForClicked()){

            }else {
                for (int i = 0; i < roomSlots.length; i++) {
                    for (int j = 0; j < roomSlots[i].length; j++) {
                        //if Clicked and if over a box
                        if (ClickDetector.clicked && roomSlots[i][j].button.over && !camMoved) {
                            //opens the menu and hands it a RoomSlot
                            menu.open(roomSlots[i][j]);
                            //repositions the camera to view the menu and box
                            cam.position.set(roomSlots[i][j].button.box.x + 510, roomSlots[i][j].button.box.y + 220, 0);
                        }
                    }

                }
            }
        }

    }
    private boolean checkPersonsForClicked(){
        for (int i=0;i<numPeople;i++){
            if (persons[i].clicked(mouseCords)){
                selectedPerson=i;
                return true;
            }
        }
        return false;
    }

    @Override
    public void render() {

        //applies updates to the camera and
        cam.update();
        sr.setProjectionMatrix(cam.combined);
        sr.begin(ShapeRenderer.ShapeType.Filled);
        for (int i =0;i<roomSlots.length;i++) {
            for (int j = 0;j<roomSlots[i].length;j++) {
                roomSlots[i][j].render(sr);
            }
        }
        for (int i = 0 ;i<numPeople;i++) {
            if (selectedPerson == i) {
                persons[i].renderGreen(sr);
            }
            persons[i].render(sr);
        }
        wp.render(sr);
        if (menu.isOpen){
            menu.render(sr);
        }
        Resources.render(sr,cam.position);
        sr.end();
    }
    public void didCamMove(){
        camMoved=lastCamPosition.dst(cam.position)!=0;
        lastCamPosition = cam.position.cpy();
    }
}
