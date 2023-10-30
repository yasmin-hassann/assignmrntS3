package com.sos.sosgame;

import javafx.event.ActionEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Player extends VBox {
    private CheckBox choiceBoxS,choiceBoxO;
    private int score;
    private Label scoreLabel;

    private Game.MoveType moveType = Game.MoveType.S;

    public Player(String name){
        super();
        score = 0;
        choiceBoxS = new CheckBox("S");
        choiceBoxO = new CheckBox("O");

        setStyle("-fx-border-color: #000000; -fx-padding: 20px;");

        Text nameText = new Text(name);
        nameText.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        getChildren().add(nameText);

        //add vertical space
        Label label = new Label(" ");
        label.setPrefHeight(150);
        getChildren().add(label);

        getChildren().add(choiceBoxS);
        getChildren().add(choiceBoxO);

        //add vertical space
        label = new Label(" ");
        label.setPrefHeight(80);
        getChildren().add(label);

        scoreLabel = new Label("" + score);
        scoreLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-alignment: center; -fx-padding: 10px;");
        getChildren().add(scoreLabel);

        choiceBoxO.setOnAction(this::onChoiceBoxO);
        choiceBoxS.setOnAction(this::onChoiceBoxS);

        choiceBoxS.setSelected(true);
        choiceBoxO.setSelected(false);
    }

    private void onChoiceBoxS(ActionEvent actionEvent) {
        choiceBoxS.setSelected(true);
        choiceBoxO.setSelected(false);
        moveType = Game.MoveType.S;
    }

    private void onChoiceBoxO(ActionEvent actionEvent) {
        choiceBoxS.setSelected(false);
        choiceBoxO.setSelected(true);
        moveType = Game.MoveType.O;
    }


    public void incrementScore() {
        score++;
        scoreLabel.setText("" + score);
    }



    public Game.MoveType getMoveType() {
        return moveType;
    }

    public int getScore() {
        return score;
    }
}
