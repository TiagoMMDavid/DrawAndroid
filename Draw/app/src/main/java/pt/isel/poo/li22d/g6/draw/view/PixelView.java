package pt.isel.poo.li22d.g6.draw.view;

import android.graphics.Canvas;

import pt.isel.poo.li22d.g6.draw.model.Figure;

public class PixelView extends FigureView {

    protected PixelView(Figure f) {
        super(f);
    }

    @Override
    void draw(Canvas c) {
        c.drawPoint(elem.getStart().getX(), elem.getStart().getY(), paint);
    }
}
