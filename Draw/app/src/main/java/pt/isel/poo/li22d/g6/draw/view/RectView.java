package pt.isel.poo.li22d.g6.draw.view;

import android.graphics.Canvas;

import pt.isel.poo.li22d.g6.draw.model.Figure;
import pt.isel.poo.li22d.g6.draw.model.Rect;

public class RectView extends FigureView {

    protected RectView(Figure f)
    {
        super(f);
    }

    @Override
    void draw(Canvas c) {
        Rect rect = (Rect) elem;
        c.drawRect(rect.getStart().getX(),
                   rect.getStart().getY(),
                   rect.getEnd().getX(),
                   rect.getEnd().getY(),
                   paint);
    }
}
