package pt.isel.poo.li22d.g6.draw.view;

import android.graphics.Canvas;

import pt.isel.poo.li22d.g6.draw.model.Circle;
import pt.isel.poo.li22d.g6.draw.model.Figure;

public class CircleView extends FigureView {

    protected CircleView(Figure f)
    {
        super(f);
    }

    @Override
    void draw(Canvas c) {
        Circle circle = (Circle) elem;
        c.drawCircle(circle.getStart().getX(),
                     circle.getStart().getY(),
                     circle.getRadius(),
                     paint);
    }
}
