package pt.isel.poo.li22d.g6.draw.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import pt.isel.poo.li22d.g6.draw.model.Circle;
import pt.isel.poo.li22d.g6.draw.model.Figure;
import pt.isel.poo.li22d.g6.draw.model.Pixel;
import pt.isel.poo.li22d.g6.draw.model.Rect;

public abstract class FigureView {

    Paint paint;
    Figure elem;

    /**
     * Constructor for FigureView.
     * Creates a FigureView, based on the Figure object
     * passed as parameter.
     * Used to set the "paint brush" color, size and paint style.
     * Should be called by every class that extends this one.
     *
     * @param f the Figure for which the view will be created
     */
    FigureView(Figure f) {
        elem = f;
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(6);
        paint.setStyle(Paint.Style.STROKE);
    }

    /**
     * Method that describes the drawing process
     * of the figure.
     *
     * @param c Canvas where the Figure will be drawn
     */
    abstract void draw(Canvas c);

    /**
     * Creates a specific FigureView type object
     * based on the Figure passed as parameter.
     *
     * @param f Figure for which the FigureView will be created
     * @return the specific FigureView for "f"
     */
    static FigureView newInstance(Figure f) {
        if (f instanceof Circle)
            return new CircleView(f);
        else if (f instanceof Rect)
            return new RectView(f);
        else if (f instanceof Pixel)
            return new PixelView(f);
        else return new LineView(f);
    }

}
