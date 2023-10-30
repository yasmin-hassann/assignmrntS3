package com.sos.sosgame;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import com.sos.sosgame.Game.*;

public class GameOptionPanel extends HBox {

    private Game game;
    private CheckBox general, simple;
    private ComboBox<Integer> boardSizeCombo;

    private GameMode mode;
    private int board_size;

    public GameOptionPanel(Game game){
        this.game = game;
        general = new CheckBox("General");
        simple = new CheckBox("Simple");

        boardSizeCombo = new ComboBox<>();
        boardSizeCombo.getItems().addAll(3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20);

        Label label = new Label("SOS");
        label.setStyle("-fx-font-size: 30px; -fx-font-weight: bold;");


        //add horizontal space
        getChildren().add(new Label(" "));
        getChildren().add(label);
        //add horizontal space
        getChildren().add(new Label(" "));

        HBox hBox = new HBox();
        hBox.setStyle("-fx-padding: 10px;");
        hBox.getChildren().addAll(general,new Label(" "), simple);
        getChildren().add(hBox);

        //add horizontal space
        hBox = new HBox();
        label = new Label("");
        label.setPrefWidth(100);
        Button apply = new Button("New Game");
        apply.setOnAction(e -> {
            game.reset(mode, board_size);
        });
        hBox.getChildren().addAll(apply,label);
        hBox.setStyle("-fx-padding: 10px;");



        getChildren().add(new Label(" "));
        Label boardSizeLabel = new Label("Board Size: ");
        boardSizeLabel.setStyle("-fx-font-size: 16px;");
        hBox.getChildren().add(boardSizeLabel);

        hBox.getChildren().add(boardSizeCombo);
        getChildren().add(hBox);

        setStyle("-fx-padding: 10px;");

        general.setOnAction(e -> {
            if(general.isSelected()){
                simple.setSelected(false);
                mode = GameMode.GENERAL;
                apply();
            }
        });

        simple.setOnAction(e -> {
            if(simple.isSelected()){
                general.setSelected(false);
                mode = GameMode.SIMPLE;
                apply();
            }
        });

        boardSizeCombo.setOnAction(e -> {
            board_size = boardSizeCombo.getValue();
            apply();
        });

        mode = GameMode.SIMPLE;
        board_size = 8;
        simple.setSelected(true);
        boardSizeCombo.setValue(board_size);

    }

    private void apply(){
        game.reset(mode, board_size);
    }
}
