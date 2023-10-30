package com.sos.sosgame;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Window;

import static com.sos.sosgame.Game.PlayerType.RED;

public class Game extends Scene {




    public enum GameMode{GENERAL, SIMPLE};
    public enum PlayerType{BLUE, RED};
    public enum MoveType{S,O}
    private BorderPane pane;

    private GameMode gameMode = GameMode.SIMPLE;


    //Two players
    private Player blue,red;

    private PlayerType playerTurn = RED;


    private Label status = new Label("RED Player's Turn");


    private Board board;




    public Game(){
        super(new BorderPane());
        pane = (BorderPane) getRoot();
        red = new Player("RED");
        blue = new Player("BLUE");



        //build the left panel
        pane.setRight(blue);
        pane.setLeft(red);

        BorderPane bottom = new BorderPane();
        bottom.setStyle("-fx-border-color: #000000; -fx-padding: 10px; -fx-background-color: #ffcfff; -fx-alignment: center; -fx-font-size: 24px; -fx-font-weight: bold;");
        bottom.prefHeight(60);
        bottom.setCenter(status);
        pane.setBottom(bottom);

        board = new Board(8,this);
        pane.setCenter(board);


    }

    public void reset(GameMode mode, int boardSize) {
        board = new Board(boardSize,this);
        pane.setCenter(board);
        gameMode = mode;

        red= new Player("RED");
        status.setText("RED Player's Turn");
        blue = new Player("BLUE");

        pane.setRight(blue);
        pane.setLeft(red);

        playerTurn = RED;

        //resize the window
        Window window = getRoot().getScene().getWindow();
        double width = boardSize*55+200;
        if(width < 700) {
            width = 700;
        }
        window.setWidth(width);

        double height = boardSize*40+200;
        if(height < 500) {
            height = 500;
        }
        window.setHeight(height);
    }

    public void setOptionPanel(Node node) {
        pane.setTop(node);
    }


    //get type of game mode for current player
    public GameMode getGameMode(){
        return gameMode;
    }

    public MoveType getCurrentMoveType(){
        if(playerTurn == PlayerType.BLUE){
            return blue.getMoveType();
        }else{
            return red.getMoveType();
        }
    }
    public void move(){

        if(playerTurn == PlayerType.BLUE){
            playerTurn = PlayerType.RED;
        }else{
            playerTurn = PlayerType.BLUE;
        }

        if(playerTurn==PlayerType.BLUE){
            status.setText("Blue Player's Turn");
        }else{
            status.setText("Red Player's Turn");
        }
    }

    public PlayerType getPlayerTurn(){
        return playerTurn;
    }


    public void incrementScore() {
        if(playerTurn==PlayerType.BLUE){
            blue.incrementScore();
        }else{
            red.incrementScore();
        }
    }


    public void endGame() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText("Game Over");
        if(blue.getScore() > red.getScore()) {
            alert.setContentText("Blue Player Wins!");
        }else if(blue.getScore() < red.getScore()) {
            alert.setContentText("Red Player Wins!");
        }else{
            alert.setContentText("It's a draw!");
        }

        alert.showAndWait();
        reset(gameMode,board.getBoardSize());


    }
}
