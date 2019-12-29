package com.company;

public class Gui {

    public void drawAuthors(){
        System.out.println(
                "*****Milosz Karlowicz*****\n" +
                "************&&************\n" +
                "****Adam Wojciechowski****\n" +
                "\n" +
                "Press Enter");
    }
    public void drawMenu(){
        System.out.println("\n** MAZE RUNNER **\n" +
                "Task is simple - find your way to home!\n" +
                " - s - start\n" +
                " - l - choose level\n" +
                " - h - help\n" +
                " - a - authors\n" +
                " - q - quit\n");
    }

    public void drawHelp(){
        System.out.println(" - r - restart level\n" +
                " - p - back to menu\n" +
                " - w - move up\n" +
                " - s - move down\n" +
                " - a - move left\n" +
                " - d - move right");
    }


}
