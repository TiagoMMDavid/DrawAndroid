package pt.isel.poo.li22d.g6.draw;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toolbar;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import pt.isel.poo.li22d.g6.draw.model.Circle;
import pt.isel.poo.li22d.g6.draw.model.DrawModel;
import pt.isel.poo.li22d.g6.draw.model.Figure;
import pt.isel.poo.li22d.g6.draw.model.Line;
import pt.isel.poo.li22d.g6.draw.model.Pixel;
import pt.isel.poo.li22d.g6.draw.model.Rect;
import pt.isel.poo.li22d.g6.draw.view.DrawView;

import static pt.isel.poo.li22d.g6.draw.model.Figure.*;

public class DrawController extends Activity {

    /**
     * TxtButton is a class that's meant to be used as
     * a Button, but accepts more parameters in its constructor, including a string,
     * which will be used as the Button's text.
     * It's used as a way to clean up clutter in the code, and to
     * simplify the declaration of Buttons.
     */
    @SuppressLint("AppCompatCustomView")
    private class TxtButton extends Button {
        TxtButton(String txt, ViewGroup parent, OnClickListener onClick)
        {
            super(getBaseContext());
            setText(txt);
            parent.addView(this);
            setOnClickListener(onClick);
        }
    }

    /**
     * TxtRadioButton is, similiarly to TxtButton, a class
     * that's meant to represent a RadioButton, but accepts
     * more parameters in its constructor.
     * It's used as a way to clean up clutter in the code, and to
     * simplify the declaration of Radio Buttons.
     */
    @SuppressLint("AppCompatCustomView")
    private class TxtRadioButton extends RadioButton {
        TxtRadioButton(String txt, ViewGroup parent, boolean startActivated, OnClickListener onClick)
        {
            super(getBaseContext());
            setText(txt);
            parent.addView(this);
            setOnClickListener(onClick);
            setChecked(startActivated);
        }
    }

    private static final String FILE = "draw.txt";
    private static final String KEY_MODEL_INSTANCE = "savedModel";
    private DrawModel model;
    private DrawView view;
    private char figureSelected = LINE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        model = new DrawModel();
        view = new DrawView(this);

        // Checks the bundle for a previously saved state, if it exists
        if (savedInstanceState != null) {
            Scanner in = new Scanner(savedInstanceState.getString(KEY_MODEL_INSTANCE));
            model.load(in);
            view.reloadModel(model);
        }

        //Main linear layout that contains all the buttons/other layouts
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);

        // Creates a toolbar (used only as a title bar in the app)
        Toolbar top = new Toolbar(this);
        top.setTitle("Draw");
        top.setBackgroundColor(Color.rgb(0, 121, 107));
        top.setTitleTextColor(Color.WHITE);
        root.addView(top);

        // Reset/Load/Save buttons layout
        LinearLayout optionsButtons = new LinearLayout(this);
        optionsButtons.setOrientation(LinearLayout.HORIZONTAL);
        root.addView(optionsButtons);

        // Reset, Load and Save buttons
        Button reset = new TxtButton("RESET", optionsButtons, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onReset();
            }
        });
        Button load = new TxtButton("LOAD", optionsButtons, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLoad();
            }
        });
        Button save = new TxtButton("SAVE", optionsButtons, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSave();
            }
        });

        // Radio buttons layout
        RadioGroup radioGroup = new RadioGroup(this);
        radioGroup.setOrientation(LinearLayout.HORIZONTAL);
        root.addView(radioGroup);

        // Radio Buttons to select the shape of the drawing
        RadioButton line = new TxtRadioButton("Line", radioGroup, true, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                figureSelected = LINE;
            }
        });
        RadioButton rect = new TxtRadioButton("Rect", radioGroup, false, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                figureSelected = RECT;
            }
        });
        RadioButton pixel = new TxtRadioButton("Pixel", radioGroup, false, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                figureSelected = PIXEL;
            }
        });
        RadioButton circle = new TxtRadioButton("Circle", radioGroup, false, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                figureSelected = CIRCLE;
            }
        });

        root.addView(view);
        setContentView(root);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_MODEL_INSTANCE, model.toString());
    }

    /**
     * Resets both the view and the model, by clearing
     * the model, and making the view reload it.
     */
    private void onReset() {
        model.clear();
        view.reloadModel(model);
    }

    /**
     * Loads from a file containing a saved drawing.
     * The file name should be defined in the variable FILE.
     */
    private void onLoad() {
        try(Scanner in = new Scanner(openFileInput(FILE))) {
            model.load(in);
            view.reloadModel(model);
            Log.v("Draw.DrawController", "Successfully loaded " + FILE);
        } catch(FileNotFoundException ie) {
            Log.e("Draw.DrawController", "Could not find " + FILE);
        }
    }


    /**
     * Saves the current drawing to a file.
     * The file name should be defined in the variable FILE.
     */
    private void onSave() {
        try(PrintWriter out = new PrintWriter((openFileOutput(FILE, MODE_PRIVATE)))) {
            model.save(out);
            Log.v("Draw.DrawController", "Successfully saved drawing to " + FILE);
        } catch(FileNotFoundException ie) {
            Log.e("Draw.DrawController", "Error opening file");
        }
    }

    /**
     * Creates a figure, based on the one that's currently
     * selected via the Radio Buttons, and on the passed coordinates.
     *
     * @param x X Coordinate of the figure
     * @param y Y Coordinate of the figure
     * @return the created figure
     */
    public Figure createSelectedFigure(int x, int y) {
        Figure f;

        switch (figureSelected) {
            case PIXEL:
                f = new Pixel(x,y);
                break;
            case LINE:
                f = new Line(x,y);
                break;
            case RECT:
                f = new Rect(x,y);
                break;
            case CIRCLE:
                f = new Circle(x,y);
                break;
            default:
                f = null;
        }

        model.add(f);
        return f;
    }
}
