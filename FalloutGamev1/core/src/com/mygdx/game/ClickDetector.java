package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class ClickDetector {
    static boolean buttonDown=false;
    static long startTime=0;
    static boolean clicked=false;
    public static void update(){
        clicked=false;
        if (buttonDown) {
            if (!Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
                buttonDown=false;
                if (System.currentTimeMillis()-startTime<200){
                    clicked=true;
                }
            }
        }else {
            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                buttonDown=true;
                startTime=System.currentTimeMillis();
            }
        }
    }
}
