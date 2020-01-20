package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 */
public class Maze {

    /**
     *
     */
    private int rows;
    /**
     *
     */
    private int columns;
    /**
     *
     */
    private Field fieldUnderPlayer;
    /**
     *
     */
    private Player thePlayer;
    /**
     *
     */
    private ArrayList<Field> fields;

    /**
     *  zwraca pole na podstawie podanej pozycji z hashmapy
     * @param position
     * @return
     */
    private Field getField(Position position){
        if(position.getX() > columns || position.getY() > rows){
            throw new IllegalArgumentException("This position does not exist in maze!");
        }
        else{
            for (int i = 0; i < fields.size(); i++) {
                if(fields.get(i).getPosition().equals(position)){
                    return fields.get(i);
                }
            }
        }
        return null;
    }

    /**
     * przesuwa gracza o jedno pole w podanym kierunku
     * @param directions
     * @return
     */
    public boolean movePlayer(Directions directions) {
        Position temporaryPosition = new Position(thePlayer.getPosition().getX(), thePlayer.getPosition().getY());
        if (directions.equals(Directions.N)) {
            temporaryPosition.setY(temporaryPosition.getY() - 1);
        }
        if (directions.equals(Directions.S)) {
            temporaryPosition.setY(temporaryPosition.getY() + 1);
        }
        if (directions.equals(Directions.E)) {
            temporaryPosition.setX(temporaryPosition.getX() + 1);
        }
        if (directions.equals(Directions.W)) {
            temporaryPosition.setX(temporaryPosition.getX() - 1);
        }
        if(getField(temporaryPosition) instanceof Box){
            if(moveBox((Box)getField(temporaryPosition), directions)){
                setPlayerNewPosition(temporaryPosition);
                return true;
            }
            else{
                return false;
            }
        }
        if(getField(temporaryPosition) instanceof Path || getField(temporaryPosition) instanceof Home){
            setPlayerNewPosition(temporaryPosition);
            return true;
        }else{
            return false;
        }
    }

    /**
     * przesuwa pudelko o jedna pozycje w podanym kierunku
     * @param box
     * @param directions
     * @return
     */
    private boolean moveBox(Box box, Directions directions){
        Position nextBoxPosition = new Position(box.getPosition().getX(), box.getPosition().getY());
        if(directions.equals(Directions.N)){
            nextBoxPosition.setY(nextBoxPosition.getY() - 1);
        }
        if(directions.equals(Directions.S)){
            nextBoxPosition.setY(nextBoxPosition.getY() + 1);
        }
        if(directions.equals(Directions.E)){
            nextBoxPosition.setX(nextBoxPosition.getX() + 1);
        }
        if(directions.equals(Directions.W)){
            nextBoxPosition.setX(nextBoxPosition.getX() - 1);
        }
        if(getField(nextBoxPosition) instanceof Path) {
            setBoxNewPosition(box, nextBoxPosition);
            return true;
        }else {
            return false;
        }
    }

    /**
     *
     * @param box
     * @param position
     */
    private void setBoxNewPosition(Box box, Position position){
        getField(position).setPosition(box.getPosition());
        box.setPosition(position);
    }

    /**
     * usuwa pole w miejscu na ktore bedzie przestawiony gracz/box
     * @param position
     */
    private void removeField(Position position) {
        int index = -1;
        for (int i = 0; i < rows * columns; i++) {
            if(fields.get(i).getPosition().equals(position)){
                index = i;
                break;
            }
        }
        fields.remove(index);
    }

    /**
     *
     * @param newPosition
     */
    public void setPlayerNewPosition(Position newPosition) {
        Field temporaryField = getField(newPosition);
        removeField(newPosition);
        fieldUnderPlayer.setPosition(thePlayer.getPosition());
        thePlayer.setPosition(newPosition);
        fields.add(fieldUnderPlayer);
        fieldUnderPlayer = temporaryField;
    }

    /**
     * warunek sprawdzajacy pozytywne zakonczenie gry
     * @return
     */
    public boolean playerFoundHome(){
        return fieldUnderPlayer instanceof Home;
    }

    /**
     * zaladowanie nowej listy pol z pliku
     * @param level
     * @throws FileNotFoundException
     */
    public void loadNewMaze(int level) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("Mazes/maze_" + level + ".txt"));
        columns = scanner.nextInt();
        rows = scanner.nextInt();
        fields = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                fields.add(generateField(scanner.next().charAt(0), new Position(j, i)));
            }
        }
        fieldUnderPlayer = generateField('o',new Position(-1,-1));
        //fields.add(field_under_player);
    }

    /**
     * tworzenie nowego pola na podstawie zczytanego znaku
     * @param c
     * @param position
     * @return
     */
    private Field generateField(char c, Position position){
        switch (c){
            case 'p':
                thePlayer = new Player(position);
                return thePlayer;
            case 'h':
                return new Home(position);
            case 'o':
                return new Path(position);
            case 'w':
                return new Wall(position);
            case 'b':{
                return new Box(position);
            }
            default:
                //TU RZUCIC WYJATKIEM
                break;
        }
        return null;
    }

    /**
     *
     */
    public void drawMaze(){
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                getField(new Position(j, i)).draw();
            }
            System.out.println("");
        }

    }

}
