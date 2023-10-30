package com.sos.sosgame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Game");
        Game game = new Game();
        GameOptionPanel gameOptionPanel = new GameOptionPanel(game);
        game.setOptionPanel(gameOptionPanel);

        stage.setScene(game);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}