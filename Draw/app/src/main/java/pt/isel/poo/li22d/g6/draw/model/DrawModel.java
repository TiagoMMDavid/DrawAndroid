package pt.isel.poo.li22d.g6.draw.model;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class DrawModel implements Iterable<Figure> {

    public LinkedList<Figure> figures = new LinkedList<>();

    /**
     * Saves the current drawing to an output stream,
     * by calling the "save" method present in each
     * of the Figures present in the drawing.
     *
     * @param out the PrintWriter to be used.
     */
    public void save(PrintWriter out) {
        out.print(figures.size());
        for(Figure figure : this) {
            out.println();
            out.print(figure.getLetter() + " ");
            figure.save(out);
        }
    }

    /**
     * Loads a drawing from an input stream.
     *
     * @param in the Scanner containing the data to be loaded
     */
    public void load(Scanner in) {
        for(int i = in.nextInt() ; i > 0 ; --i) {
            Figure curr = Figure.newInstance(in.next().charAt(0));
            curr.load(in);
            figures.add(curr);
        }
    }

    /**
     * Clears the current drawing.
     */
    public void clear() {
        figures.clear();
    }

    /**
     * Adds a figure to the drawing
     *
     * @param f the Figure object to be added
     */
    public void add(Figure f) {
        figures.add(f);
    }

    public Iterator<Figure> iterator() {
        return figures.iterator();
    }

    /**
     * Converts the current drawing to a String.
     * Can be used to get a text-based representation
     * of the drawing.
     *
     * @return String that represents the drawing
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(figures.size());
        for(Figure figure : this) {
            builder.append('\n')
                    .append(figure.getLetter())
                    .append(' ')
                    .append(figure.toString());
        }
        return builder.toString();
    }
}
