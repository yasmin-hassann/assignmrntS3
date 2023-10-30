package com.sos.sosgame;



import javafx.scene.control.Label;

public class MyLabel extends Label {

    private int i,j;

    public MyLabel(int i, int j){
        this.i = i;
        this.j = j;
    }

    public int getX() {
        return i;
    }

    public int getY() {
        return j;
    }

}
