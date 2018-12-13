package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

public class Main implements State {
    ShapeRenderer sr=new ShapeRenderer();
    RoomSlot[][] roomSlots=new RoomSlot[10][10];
    OrthographicCamera cam;
    Vector3 mouseCords;
    Menu menu;
    WaterMenu waterMenu;
    Vector3 lastCamPosition;
    boolean camMoved;
    Person[] persons;
    int numPeople;
    int selectedPerson;
    RoomSlot selectedRoomSlot;
    PeopleHandler people;
    WalkingPoints wp;

    public Main(){
        Resources.setMain(this);
        wp=new WalkingPoints();
        selectedPerson=-1;
        people=new PeopleHandler();
        people.addPerson();
        menu=new WaterMenu(); //menu that pops up when you click on a rectangle
        waterMenu=new WaterMenu();
        cam = new OrthographicCamera(1920,1080);//every device will show 1920 by 1080 pixels

        //This for loop creates 100 roomslots. A room slot is a combination of a room and a button
        for (int i = 0 ;i <roomSlots.length;i++){
            for (int j=0;j<roomSlots[i].length;j++){
                roomSlots[i][j]=new RoomSlot(null,new Button(new Box((i)*510,j*260,500,250)));
            }
        }
        makeFirstRoom();
        //this just repositions the camera initially. It's not necessary.
        cam.position.set(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2,0);
        cam.update();
        lastCamPosition=cam.position.cpy();
        camMoved=false;

    }
    public void makeFirstRoom(){
        roomSlots[0][0].room=new Room(roomSlots[0][9].button.box.x,roomSlots[0][0].button.box.y,"POWER");
        wp.addPoint(new Vector3(250,10,0));
    }
    @Override
    public void update() {
        //I wrote my own static detector class that tells when a click has occurred
        ClickDetector.update();
        mouseCords=new Vector3(Gdx.input.getX(),Gdx.input.getY(),0);//new Vector3 that holds mouse coordinates
        mouseCords=cam.unproject(mouseCords);//translates mouse coordinates into world space

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            System.out.println("Boogie");
            people.addPerson();
        }
        people.update(this);
        for (int i =0 ;i<numPeople;i++){
            persons[i].update(this);
        }

        //updates boxes. Generates resources.
        for (int i =0;i<roomSlots.length;i++){
            for (int j=0;j<roomSlots[i].length;j++) {
                roomSlots[i][j].update(mouseCords);
            }
        }
        //detects if the camera moved in the last frame. Should probably be under cam.translate, but it doesn't work there as intended
        didCamMove();
        if (people.selected!=-1) {
            if (ClickDetector.clicked){
                people.getPerson().setDestination(wp.findClosest(mouseCords.add(0,-125,0)).position);
                people.setSelected(-1);
            }
        }else if (menu.isOpen){
            //updates buttons to tell if mouse is over the menu
            menu.update(mouseCords,cam.position);
            if (ClickDetector.clicked){
                //if you click on the menu it interacts with the menu. If you click off the menu the menu closes.
                if (menu.background.over){
                    menu.click(mouseCords,this);

                }else {
                    menu.close();
                }
            }
        }else {
            //If the menu is closed the camera can be moved by dragging.
            cam.translate(-Gdx.input.getDeltaX(), Gdx.input.getDeltaY());

            if (ClickDetector.clicked&&people.checkPersonsForClicked(mouseCords)){
                people.getPerson().removeFromRoom(this);

            }else {
                for (int i = 0; i < roomSlots.length; i++) {
                    for (int j = 0; j < roomSlots[i].length; j++) {
                        //if Clicked and if over a box
                        if (ClickDetector.clicked && roomSlots[i][j].button.over && !camMoved) {
                            openMenu(roomSlots[i][j]);
                        }
                    }
                }
            }
        }

    }
    private void openMenu(RoomSlot rs){
        //opens the menu and hands it a RoomSlot
        if (rs.room!=null){
            if (rs.room.getType().compareTo("POWER")==0){
                menu=new PowerMenu();
            } else if (rs.room.getType().compareTo("WATER")==0) {
                menu=new WaterMenu();
            } else if (rs.room.getType().compareTo("FOOD")==0){
                menu=new FoodMenu();
            }
        }else{
            menu=new Menu();
        }
        menu.open(rs.button.box.x,rs.button.box.y,rs);
        selectedRoomSlot=rs;

        //repositions the camera to view the menu and box
        cam.position.set(rs.button.box.x + 510, rs.button.box.y + 220, 0);

    }
    private Person getPerson(Vector3 mouse){
        for (int i =0;i<numPeople;i++){
            if (persons[i].position.dst(mouse)==0){
                return persons[i];
            }
        }
        return null;
    }

    public RoomSlot getRoomSlot(Vector3 mouse){
        for (int i =0;i<roomSlots.length;i++){
            for (int j =0;j<roomSlots[i].length;j++){
                if (roomSlots[i][j].button.box.x<=mouse.x&&roomSlots[i][j].button.box.x+500>mouse.x&&roomSlots[i][j].button.box.y<=mouse.y&&roomSlots[i][j].button.box.y+100>=mouse.y){
                    return roomSlots[i][j];
                }
            }
        }
        return null;
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
        people.render(sr);
        //wp.render(sr); //walking points renderer
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
