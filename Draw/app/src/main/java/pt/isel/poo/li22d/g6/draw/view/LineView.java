package pt.isel.poo.li22d.g6.draw.view;

import android.graphics.Canvas;

import pt.isel.poo.li22d.g6.draw.model.Figure;
import pt.isel.poo.li22d.g6.draw.model.Line;

public class LineView extends FigureView {

    protected LineView(Figure f)
    {
        super(f);
    }

    @Override
    void draw(Canvas c) {
        Line line = (Line) elem;

        c.drawLine(line.getStart().getX(),
                   line.getStart().getY(),
                   line.getEnd().getX(),
                   line.getEnd().getY(),
                   paint);
    }
}
