package com.company;

/**
 *
 */
public class Position {
    /**
     *
     * @param x
     * @param y
     */
    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * wartosc w poziomiomie
     */
    private int x;

    /**
     * wartosc w pionie
     */
    private int y;

    /**
     *
     * @return
     */
    public int getX() {
        return x;
    }

    /**
     *
     * @return
     */
    public int getY() {
        return y;
    }

    /**
     *
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     *
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    /**
     * funkcja do porownywania dwoch klas Position
     * @param obj
     * @return
     */
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
