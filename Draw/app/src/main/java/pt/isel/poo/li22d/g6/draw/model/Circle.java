package pt.isel.poo.li22d.g6.draw.model;

import java.io.PrintWriter;
import java.util.Scanner;

public class Circle extends Figure {

    private static final char LETTER = CIRCLE;
    private int radius;

    private static final char RADIUS_DELIMITER = '|';

    public Circle() {
        this(0,0);
    }

    public Circle(int x, int y) {
        super(x, y);
        radius = 0;
    }

    /**
     * Sets the end point of the circle. In other words,
     * calculates the radius of the circle, based on the
     * coordinate passed as parameters.
     *
     * @param x X coordinate
     * @param y Y coordinate
     */
    @Override
    public void setEnd(int x, int y) {
        int xLength = x - (getStart().getX());
        int yLength = y - (getStart().getY());
        radius = (int) Math.sqrt(xLength * xLength + yLength * yLength);
    }

    @Override
    public void save(PrintWriter out) {
        out.print(toString());
    }

    @Override
    public void load(Scanner in) {
        super.load(in);
        String radiusString = in.next();
        radius = parseRadius(radiusString);
    }

    /**
     * Parses the radius from a String. The delimiter
     * used to stop the scan must be specified in the
     * RADIUS_DELIMITER field.
     *
     * @param radiusString The string containing the radius.
     * @return the value of the radius.
     */
    private int parseRadius(String radiusString) {
        int value = 0;
        for (int i = 1; ; ++i) {
            char curr = radiusString.charAt(i);
            if (curr == RADIUS_DELIMITER)
                break;
            value *= 10;
            value += curr - '0';
        }
        return value;
    }

    public int getRadius() {
        return radius;
    }
    @Override
    protected char getLetter() {
        return LETTER;
    }

    @Override
    public String toString() {
        return (super.toString() + " " + RADIUS_DELIMITER + radius + RADIUS_DELIMITER);
    }

}
