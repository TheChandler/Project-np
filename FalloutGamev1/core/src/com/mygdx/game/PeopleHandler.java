package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

public class PeopleHandler {
    Person[] people;
    int numPeople;
    int selected;
    public PeopleHandler(){
        people=new Person[100];
        numPeople=0;
        selected =-1;
    }
    public void addPerson(){
        people[numPeople]=new Person();
        numPeople++;
    }
    public Person getPerson(){
        return people[selected];
    }
    public void setSelected(int i){
        selected =i;
    }

    public boolean checkPersonsForClicked(Vector3 mouseCords){
        for (int i=0;i<people.length;i++){
            if (people[i]!=null) {
                if (people[i].clicked(mouseCords)) {
                    selected = i;
                    System.out.println(people[i]);
                    return true;
                }
            }
        }
        return false;
    }

    public void render(ShapeRenderer sr){
        if (selected!=-1){
            people[selected].renderGreen(sr);
        }
        for (int i=0;i<people.length;i++){
            if (people[i]!=null){
                people[i].render(sr);
            }
        }
    }
    public void update(Main main){
        for (int i=0;i<people.length;i++){
            if (people[i]!=null){
                people[i].update(main);
            }
        }
    }
}
