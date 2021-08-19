package pt.isel.poo.li22d.g6.draw.model;

import java.io.PrintWriter;
import java.util.Scanner;

public abstract class Figure {

    public static final char LINE = 'L';
    public static final char CIRCLE = 'C';
    public static final char PIXEL = 'P';
    public static final char RECT = 'R';

    private Point start;

    protected Figure () {
        this(0,0);
    }

    /**
     * Creates a Figure on the specified coordinates. This method
     * should be called in the constructor of all the classes that
     * extend this class.
     *
     * @param x X coordinate
     * @param y Y coordinate
     */
    protected Figure(int x, int y) {
        start = new Point(x,y);
    }

    /**
     * Sets the end point of the Figure.
     *
     * @param x X coordinate
     * @param y Y coordinate
     */
    public abstract void setEnd(int x, int y);

    /**
     * Saves the current Figure to an output stream.
     * Should be called by all the save methods that
     * override this one
     *
     * @param out the PrintWriter to be used
     */
    public void save(PrintWriter out) {
        getStart().save(out);
    }

    /**
     * Loads the information of the Figure from an input-stream
     * Should be called by all the load methods that
     * override this one.
     *
     * @param in the Scanner containing the data to be loaded
     */
    public void load(Scanner in) {
        getStart().load(in);
    }

    public Point getStart() {
        return start;
    }

    protected abstract char getLetter();

    /**
     * Creates a new Figure based object, depending
     * on the char passed as parameter.
     * New figure types should have their
     * corresponding chars in this class's fields.
     *
     * @param letter the char representing the Figure
     * @return Figure created, based on the char
     */
    public static Figure newInstance(char letter) {
        switch(letter) {
            case LINE:
                return new Line();
            case RECT:
                return new Rect();
            case CIRCLE:
                return new Circle();
            case PIXEL:
                return new Pixel();
            default:
                return null;
        }
    }

    /**
     * Creates a text-based representation of the figure.
     * Should be called by all toString methods of all
     * classes that extend this one.
     *
     * @return String representing this Figure
     */
    @Override
    public String toString() {
        return getStart().toString();
    }
}
