package com.company;

import java.lang.invoke.DirectMethodHandle$Holder;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BattleshipGame {

    //-1 = missed shot
    //0 = empty space
    //1 = Aircraft Carrier
    //2 = Battleship
    //3 = Destroyer
    //4 = Submarine
    //5 = Patrol Boat

    private List<Ship> shipSet;
    private Player p1;
    private Player p2;
    private int rows, columns;
    private Random rnd;

    enum Direction{
        UP,DOWN,LEFT,RIGHT;
    }

    //gamestate

    public BattleshipGame(){

        //initiate the board size
        rows = 10;
        columns = 10;

        //initiate the ships
        shipSet = new ArrayList<>();
        shipSet.add(new Ship("Aircraft Carrier", 5, 1));
        shipSet.add(new Ship("Battleship", 4, 2));
        shipSet.add(new Ship("Destroyer", 3, 3));
        shipSet.add(new Ship("Submarine",3, 4));
        shipSet.add(new Ship("Patrol Boat",2, 5));

        rnd = new Random();

        //Create the two automated players
        p1 = new Player(new ArrayList<>(shipSet), rows, columns);
        p2 = new Player(new ArrayList<>(shipSet), rows, columns);

        beginGame();




    }

    private void beginGame() {

    }

    public void turnCycle(Player player){


    }

    public int[] probabilityDensity(ArrayList<Ship> opponentsFleet, int[][] shotMap){
        int[][] result = new int[10][10];
        Direction direction;

            int minSize = 0;
            for (Ship s : opponentsFleet){
                if(s.getSize() > minSize){
                    minSize = s.getSize();
                }
            }

            for (int i = 0; i < 10; i++){
                for (int j = 0; j < 10; j++){
                    if(shotMap[i][j] == 0){
                        //up = 0
                        if(i >= (minSize -1)){
                            boolean shipFits = false;

                            int size = 0;
                            do{
                                for (Ship s : opponentsFleet){

                                    size = s.getSize();

                                    int count = 0;
                                    for (int k = i; k > size - 1; k--){
                                        if(shotMap[k][j] != 0){
                                            break;
                                        }
                                        count++;
                                    }

                                    if(count == size){
                                        shipFits = true;
                                    }

                                }
                            }while(!shipFits);

                            //call a method to add to the result array, use a arraylist to calculate for the smaller ships that are included
                            direction = Direction.UP;
                            result = calcShipWeights(opponentsFleet, size, result, direction, i, j);




                        }
                        //down
                        if(i <= 9 - (minSize - 1)){
                            direction = Direction.DOWN;
                            for (Ship s : opponentsFleet){

                            }
                        }
                        //right
                        if(j <= 9 - (minSize - 1)){
                            direction = Direction.RIGHT;
                            for (Ship s : opponentsFleet){

                            }
                        }
                        //left
                        if(j >= (minSize - 1)){
                            direction = Direction.LEFT;
                            for (Ship s : opponentsFleet){

                            }
                        }




                    }
                }
            }




        return temp;
    }

    public int[][] calcShipWeights(ArrayList<Ship> ships, int size, int[][] result, Direction direction, int row, int col){
        for (Ship s : ships){
            if(s.getSize() <= size){
                switch (direction){
                    case UP:
                        for (int i = row; i <)
                    case DOWN:
                        for ()
                    case RIGHT:
                        for ()
                    case LEFT:
                        for ()
                }
            }
        }

        return result;
    }

    public Player getP1() {
        return p1;
    }

    public Player getP2() {
        return p2;
    }

    public class Ship {

        private String name;
        private int size, identifier, hits;

        public Ship(String name, int size, int identifier){
            this.name = name;
            this.size = size;
            this.identifier = identifier;
            hits = 0;
        }

        public int getSize(){
            return size;
        }

        public int getIdentifier() {
            return identifier;
        }
    }

    public class Player{
        private List<Ship> ships;
        private int turns, hits, sinks;
        public int[][] ownBoard, shotMap;

        public Player(List<Ship> ships, int rows, int cols){
            this.ships = ships;
            ownBoard = new int[rows][cols];
            shotMap = new int[rows][cols];

            for (Ship s : ships){
                placeShip(s);
            }

            turns = 0;
            hits = 0;
            sinks = 0;

        }

        public void placeShip(Ship ship){
            int size = ship.getSize();
            int identifier = ship.getIdentifier();
            boolean placed = false;
            while(!placed){
                int direction = rnd.nextInt(2); // 1 for horizontal, 0 for vertical
                int rowMax = (direction == 1) ? rows - 1 : columns - size;
                int colMax = (direction == 1) ? rows - size : columns -1;

                int row = rnd.nextInt(rowMax+1  );
                int column = rnd.nextInt(colMax+1);

                if(direction == 1){
                    for (int i = 0; i < size; i++){
                        if(ownBoard[row][column+i] != 0){
                            return;
                        }
                    }

                    for(int i = 0; i < size; i++){
                        ownBoard[row][column+i] = identifier;
                    }
                    placed = true;
                }

                if(direction == 0){
                    for (int i = 0; i < size; i++){
                        if(ownBoard[row+i][column] != 0){
                            return;
                        }
                    }

                    for(int i = 0; i < size; i++){
                        ownBoard[row+i][column] = identifier;
                    }
                    placed = true;
                }
            }

        }

        public int[][] getBoard() {
            return ownBoard;
        }


    }
}
