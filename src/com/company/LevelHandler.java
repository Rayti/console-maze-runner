package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

public class LevelHandler {
    public LevelHandler(int levelsAmount){
        this.levelsAmount = levelsAmount;
        hashMap = new HashMap<>();
        loadHashMap();
    }

    private HashMap<Integer, Integer> hashMap;
    private int levelsAmount;

    public int getLevelsAmount() {
        return levelsAmount;
    }

    public void loadHashMap(){
        int key, value;
        try{
            Scanner scanner = new Scanner(new File("Mazes/levelsFinished.txt"));
            hashMap.clear();
            for(int i = 0; i < levelsAmount; i++){
                value = scanner.nextInt();
                key = scanner.nextInt();
                hashMap.put(key, value);
            }
        }catch (FileNotFoundException e){
            System.out.println("File does not exists.");
        }
    }

    public void resetSave(){
        for (int i = 1; i <= levelsAmount; i++) {
            hashMap.remove(i);
            hashMap.put(i, 0);
        }
    }

    public void save() {
        try{
            PrintWriter printWriter = new PrintWriter(new File("Mazes/levelsFinished.txt"));
            for (int i = 1; i <= levelsAmount; i++) {
                printWriter.println(hashMap.get(i) + " " + i);
            }
            printWriter.close();
        }
        catch (FileNotFoundException e){
            System.out.println("File does not exists.");
        }
    }

    public void levelFinished(int levelNumber) {
        hashMap.remove(levelNumber);
        hashMap.put(levelNumber, 1);
    }

    public void drawLevels(){
        System.out.println("Levels: ");
        for (int i = 1; i <= levelsAmount; i++) {
            if(hashMap.get(i) == 1){
                System.out.println(" - " + i + " - " + "maze nr " + i + " || finished ||");
            }else {
                System.out.println(" - " + i + " - " + "maze nr " + i + " ||    x     ||");
            }
        }
        System.out.println("Reset save (all levels will be unfinished) : t\n" +
                "Back to menu : p");
    }

}

