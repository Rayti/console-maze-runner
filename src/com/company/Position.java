package com.company;

public class Position {
    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }
    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null){
            return false;
        }
        if(obj instanceof Position){
            return x == ((Position) obj).getX() && y == ((Position) obj).getY();
        }else{
            return false;
        }
    }
}
