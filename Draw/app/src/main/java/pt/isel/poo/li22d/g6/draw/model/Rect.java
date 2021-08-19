package pt.isel.poo.li22d.g6.draw.model;

public class Rect extends Line {

    public static final char LETTER = RECT;

    public Rect() {
        this(0,0);
    }

    public Rect(int x, int y) {
        super(x,y);
    }

    @Override
    protected char getLetter()
    {
        return LETTER;
    }
}
