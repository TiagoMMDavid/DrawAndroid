package pt.isel.poo.li22d.g6.draw.model;

public class Pixel extends Figure {

    public static final char LETTER = PIXEL;

    public Pixel() {
        this(0,0);
    }

    public Pixel(int x, int y) {
        super(x,y);
    }

    @Override
    public void setEnd(int x, int y) {
        getStart().set(x,y);
    }

    @Override
    protected char getLetter() {
        return LETTER;
    }
}
