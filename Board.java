package com.sos.sosgame;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;





public class Board extends Group {

        public static final int labelSize = 40;
        private Game game;
        private int size;
        private MyLabel[][] board;


        private int markedCellCount = 0;
        Board(int size, Game game){
            board = new MyLabel[size][size];
            this.size = size;
            this.game = game;


            for(int i = 0; i < size; i++){
                for(int j = 0; j < size; j++){
                    board[i][j] = new MyLabel(i,j);
                    board[i][j].setStyle("-fx-border-color: #000000; -fx-border-width: 1px; -fx-background-color: #ffffff; -fx-font-size: 20px; -fx-font-weight: bold; -fx-alignment: center; -fx-padding: 10px;");
                    board[i][j].setPrefSize(40,40);
                    board[i][j].setTranslateX(40*j);
                    board[i][j].setTranslateY(40*i);
                    board[i][j].setOnMouseClicked(e -> {

                        MyLabel label = (MyLabel) e.getSource();
                        if(label.getText().equals("S") || label.getText().equals("O")){

                            return;
                        }

                        if(game.getCurrentMoveType() == Game.MoveType.S){
                            label.setText("S");
                        }else{
                            label.setText("O");
                        }

                        checkSOS(label);
                        game.move();
                        markedCellCount++;
                        if(markedCellCount == size*size){
                            game.endGame();
                        }


                    });
                    getChildren().add(board[i][j]);
                }
            }
        }

        private void checkSOS(MyLabel label){

            int i= label.getX();
            int j = label.getY();




            if(label.getText().toString().equals("S")){

                for (int k = -1; k <=1 ; k++) {
                    for (int l = -1; l <=1 ; l++) {
                        if(k == 0 && l == 0){
                            continue;
                        }

                        if(isLabelO(i+k,j+l) && isLabelS(i+2*k,j+2*l)){
                            addLine(i,j,i+2*k,j+2*l);
                            game.incrementScore();
                        }
                    }
                }
            }
            else if(label.getText().toString().equals("O")){

                for (int k = -1; k <=1 ; k++) {
                    for (int l = -1; l <=1 ; l++) {
                        if(k == 0 && l == 0){
                            continue;
                        }

                        if(isLabelS(i+k,j+l) && isLabelS(i-k,j-l)){
                            addLine(i+k,j+l,i-k,j-l);
                            game.incrementScore();
                        }
                    }
                }

            }

        }




    private void addLine(int i, int j, int i1, int i2) {
        Line line = new Line();

        Label label1 = board[i][j];
        Label label2 = board[i1][i2];

        line.setStartX(label1.getTranslateX()+25);
        line.setStartY(label1.getTranslateY()+25);

        line.setEndX(label2.getTranslateX()+25);
        line.setEndY(label2.getTranslateY()+25);

        if(game.getPlayerTurn() == Game.PlayerType.BLUE)
            line.setStroke(Color.BLUE);
        else
            line.setStroke(Color.RED);

        line.setStrokeWidth(2);
        getChildren().add(line);
    }



    private boolean isLabelO(int i,int j){
            if(i < 0 || i >= size || j < 0 || j >= size){
                return false;
            }

            if(board[i][j].getText().toString().equals("O")){
                return true;
            }
            return false;
        }

        private boolean isLabelS(int i,int j){
            if(i < 0 || i >= size || j < 0 || j >= size){
                return false;
            }


            if(board[i][j].getText().toString().equals("S")){
                return true;
            }
            return false;
        }

    public int getBoardSize() {
        return size;
    }
}
