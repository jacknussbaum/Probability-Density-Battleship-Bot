package com.company;

public class Main {



    public static void main(String[] args) {


        int[][] totalBoard = new int[10][10];
        for(int i = 0; i < 100000; i++){

            BattleshipGame game = new BattleshipGame();

            int[][] p1Board = game.getP1().getBoard();
            int[][] p2Board = game.getP2().getBoard();
            for (int j = 0; j < 10; j++){
                for (int k = 0; k < 10; k++){
                    if(p1Board[j][k] != 0){
                        totalBoard[j][k]++;
                    }
                    if(p2Board[j][k] != 0){
                        totalBoard[j][k]++;
                    }
                }
            }

        }
        System.out.println(totalBoard.toString());


    }


}
