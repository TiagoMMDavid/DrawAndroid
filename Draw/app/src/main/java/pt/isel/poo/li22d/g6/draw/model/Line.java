package pt.isel.poo.li22d.g6.draw.model;

import java.io.PrintWriter;
import java.util.Scanner;

public class Line extends Figure {

    public static final char LETTER = LINE;

    private Point end;

    public Line() {
        this(0,0);
    }

    public Line(int x, int y) {
        super(x,y);
        end = new Point(x,y);
    }

    @Override
    public void setEnd(int x, int y) {
        end.set(x,y);
    }

    @Override
    public void load(Scanner in) {
        super.load(in);
        end.load(in);
    }

    @Override
    public void save(PrintWriter out) {
        out.print(toString());
    }

    public Point getEnd() {
        return end;
    }

    @Override
    protected char getLetter() {
        return LETTER;
    }

    @Override
    public String toString() {
        return (super.toString() + ' ' + getEnd().toString());
    }
}
