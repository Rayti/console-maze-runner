package com.company;

/**
 *
 */
public class Home implements Field{

    /**
     *
     * @param position
     */
    public Home(Position position){
        this.position = position;
    }

    /**
     *
     */
    @Override
    public void draw() {
        System.out.print("⌂");
    }

    /**
     *
     * @return
     */
    @Override
    public Position getPosition() {
        return position;
    }

    /**
     *
     * @param position
     */
    @Override
    public void setPosition(Position position){
        this.position.setX(position.getX());
        this.position.setY(position.getY());
    }

    private Position position;
}
