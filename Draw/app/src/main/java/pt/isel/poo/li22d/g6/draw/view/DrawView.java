package pt.isel.poo.li22d.g6.draw.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;

import java.util.LinkedList;

import pt.isel.poo.li22d.g6.draw.DrawController;
import pt.isel.poo.li22d.g6.draw.model.DrawModel;
import pt.isel.poo.li22d.g6.draw.model.Figure;

public class DrawView extends View {

    private LinkedList<FigureView> views = new LinkedList<>();
    private DrawController ctrl;
    private FigureView curr;
    private Figure currFigure;

    public DrawView(DrawController ctrl) {
        super(ctrl);
        this.ctrl = ctrl;
    }

    /**
     * Reloads the DrawModel associated with this DrawView
     * @param model the DrawModel to be reloaded
     */
    public void reloadModel(DrawModel model) {
        views.clear();
        for (Figure figure : model) {
            views.add(FigureView.newInstance(figure));
        }
        invalidate();
    }

    /**
     * Draws the background color of the canvas, while also drawing
     * each FigureView present in "views" on the canvas, based on
     * their own draw method.
     * Will also draw the figure that's being drawn, if it exists.
     *
     * @param canvas the Canvas in which the FigureViews will be drawn
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.rgb(200, 254, 200)); //Sets the background color

        //Draws every figure on the views List
        for (FigureView view : views) {
            view.draw(canvas);
        }

        //Draws the figure that's being drawn, if it exists
        if (curr != null)
            curr.draw(canvas);
    }

    /**
     * Creates a new figure when the user touches the canvas, and
     * changes the end point when the user moves the finger on the
     * canvas.
     * Will save the created figure when the user lifts his finger
     * from the canvas.
     *
     * @param event the MotionEvent associated with the user's touch input
     * @return true
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                currFigure = ctrl.createSelectedFigure((int)event.getX(), (int)event.getY());
                curr = FigureView.newInstance(currFigure);
                break;
            case MotionEvent.ACTION_MOVE:
                currFigure.setEnd((int)event.getX(), (int)event.getY());
                break;
            case MotionEvent.ACTION_UP:
                views.add(curr);
                curr = null;
        }
        invalidate();
        return true;
    }
}
