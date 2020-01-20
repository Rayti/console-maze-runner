package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.DigestException;
import java.util.Scanner;

/**
 *
 */
public class Game {
    /**
     *
     * @param levelsAmount
     */
    public Game(int levelsAmount) {
        maze = new Maze();
        try{
            maze.loadNewMaze(1);
        }catch (FileNotFoundException e){
            System.out.println("Error with loading maze's!");
        }
        gui = new Gui();
        levelChosen = 1;
        isMenu = true;
        scanner = new Scanner(System.in);
        quit = false;
        levelHandler = new LevelHandler(levelsAmount);
        choosingLevels = false;
        mainLoop();
    }

    /**
     *
     */
    private Maze maze;

    /**
     *
     */
    private Gui gui;

    /**
     *
     */
    private int levelChosen;

    /**
     *
     */
    private boolean isMenu;

    /**
     *
     */
    private boolean choosingLevels;

    /**
     *
     */
    private boolean quit;

    /**
     *
     */
    private Scanner scanner;

    /**
     *
     */
    private LevelHandler levelHandler;

    /**
     *  glowna petla gry
     */
    private void mainLoop(){
        while (!quit) {
            if(isMenu && !choosingLevels){
                menuCase();
            }
            if(isMenu && choosingLevels){
                levelsCase();
                System.out.println("Press Enter");
                scanner.nextLine();
            }
            if(!isMenu){
                gameLoop();
            }

        }
    }

    /**
     *  przypadek kiedy w menu wejdziewy w wybor poziomow
     */
    private void levelsCase(){
        char c = scanner.nextLine().charAt(0);
        int lvl = (int)c - 48; //Zamiana z Ascii na inta
        if(lvl == 64){ //Gdy wcisniesz p
            choosingLevels = false;
            return;
        }
        if(lvl == 68){// Gdy wcisniesz t
            levelHandler.resetSave();
            choosingLevels = false;
            System.out.println("Reset successfully made!");
            return;
        }
        if(lvl > 0 && lvl <= levelHandler.getLevelsAmount()){ // gdy wcisniesz liczbe
            try{
                maze.loadNewMaze(lvl);
                System.out.println("Level " + lvl + " successfully loaded!");
                levelChosen = lvl;
                choosingLevels = false;
            }catch (FileNotFoundException e){
                System.out.println("This level does not exists!");
            }
            return;
        }
        choosingLevels = false;
        System.out.println("Wrong command!");
    }

    /**
     *  przypadek gdy jesesmy w glownym menu
     */
    private void menuCase(){
        gui.drawMenu();
        char c;
        c = scanner.nextLine().charAt(0);
        switch (c) {
            case 'a' :{
                gui.drawAuthors();
                scanner.nextLine();
                break;
            }
            case 's':{
                isMenu = false;
                break;
            }
            case 'h':{
                gui.drawHelp();
                System.out.println("Press Enter");
                scanner.nextLine();
                break;
            }
            case 'q':{
                quit = true;
                levelHandler.save();
                break;
            }
            case 'l':{
                levelHandler.drawLevels();
                choosingLevels = true;
                break;
            }
            default:{
                System.out.println("Wrong command!");
            }
        }
    }

    /**
     *  przypadek gdy klikniemy start
     */
    private void gameLoop(){
        System.out.println("*Maze number " + levelChosen + "*");
        maze.drawMaze();
        System.out.println("Moving: wsad\nBack to menu: p\nReset level: r");
        if(maze.playerFoundHome()){
            System.out.println("Congratulations! You've found home!");
            System.out.println("Press Enter");
            scanner.nextLine();
            levelHandler.levelFinished(levelChosen);
            isMenu = true;
            return;
        }
        char c = scanner.nextLine().charAt(0);
        switch (c) {
            case 'w' :{
                if(maze.movePlayer(Directions.N)){
                    System.out.println("Good!\n");
                }else{
                    System.out.println("Wrong\n");
                }
                break;
            }
            case 's' :{
                if(maze.movePlayer(Directions.S)){
                    System.out.println("Good!\n");
                }else{
                    System.out.println("Wrong\n");
                }
                break;
            }
            case 'a' :{
                if(maze.movePlayer(Directions.W)){
                    System.out.println("Good!\n");
                }else{
                    System.out.println("Wrong\n");
                }
                break;
            }
            case 'd' :{
                if(maze.movePlayer(Directions.E)){
                    System.out.println("Good!\n");
                }else{
                    System.out.println("Wrong\n");
                }
                break;
            }
            case 'p' :{
                isMenu = true;
                break;
            }
            case 'r' :{
                try{
                    maze.loadNewMaze(levelChosen);
                }catch (FileNotFoundException e){
                    System.out.println("Error in resetting level occured. Turn off game.");
                }
            }
            default:{
                System.out.println("Wrong command!");
            }
        }

    }
}


