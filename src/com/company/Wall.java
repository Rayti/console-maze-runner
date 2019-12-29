package com.company;

public class Wall implements Field{

    public Wall(Position position){
        this.position = position;
    }
    @Override
    public void draw() {
        System.out.print("█");
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position position){
        this.position.setX(position.getX());
        this.position.setY(position.getY());
    }

    private Position position;
}
