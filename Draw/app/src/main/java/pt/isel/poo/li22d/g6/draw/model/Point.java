package pt.isel.poo.li22d.g6.draw.model;

import java.io.PrintWriter;
import java.util.Scanner;

public class Point {

    private int x, y;

    private static final char COORD_LEFT_LIMIT = '(';
    private static final char COORD_MID_SEPARATOR = ',';
    private static final char COORD_RIGHT_LIMIT = ')';

    Point() {
        new Point(0,0);
    }

    /**
     * Creates a Point on the specified coordinates.
     * @param x X coordinate
     * @param y Y coordinate
     */
    Point(int x, int y) {
       this.x = x;
       this.y = y;
    }

    /**
     * Sets the current point's coordinates
     * @param x X coordinate
     * @param y Y coordinate
     */
    void set(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Saves the current point to an output stream.
     *
     * @param out the PrintWriter to be used
     */
    public void save(PrintWriter out) {
        out.print(toString());
    }

    /**
     * Loads the point's info from an input-stream.
     * @param in the Scanner containing the input data
     */
    public void load(Scanner in) {
        String point = in.next();

        this.x = parseCoordinates(point, COORD_MID_SEPARATOR, 1);
        this.y = parseCoordinates(point, COORD_RIGHT_LIMIT, (int)Math.log10(x)+3); // Start value is equal to "x"'s digit count, +2 to skip the first parenthesis and the comma
    }

    /**
     * Parses a coordinate from a String.
     * It starts parsing from the index "startValue", until
     * it reaches the character "end"
     *
     * @param coordinates String containing the coordinates
     * @param end character that stops the parsing
     * @param startValue index at which the parsing begins
     * @return integer value of the coordinate
     */
    private int parseCoordinates(String coordinates, char end, int startValue) {
        int value = 0;
        for (; ; ++startValue) {
            char curr = coordinates.charAt(startValue);
            if (curr == end)
                break;
            value *= 10;
            value += curr - '0';
        }
        return value;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * Creates a text-based representation of the Point.
     *
     * @return String that represents the point
     */
    @Override
    public String toString() {
        return ("" + COORD_LEFT_LIMIT + x + COORD_MID_SEPARATOR + y + COORD_RIGHT_LIMIT);
    }
}
