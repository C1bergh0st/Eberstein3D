package de.c1bergh0st.eberstein3d.overview;

import de.c1bergh0st.eberstein3d.math.Line;
import de.c1bergh0st.eberstein3d.math.Ray;
import de.c1bergh0st.eberstein3d.math.Vector;
import javafx.util.Pair;

import java.awt.*;
import java.awt.geom.Point2D;

public class Camera {
    public static final double BASE_SKALAR = 120;
    private Vector offset;
    private Vector screendim;
    private double skalar;
    private int pwidth;
    private int pheigth;
    private Graphics2D g;


    public Camera(Vector offset, Vector screendim, double skalar, Graphics2D g){
        this.offset = offset;
        this.skalar = skalar;
        this.screendim = screendim;
        pwidth = (int)(screendim.getX() * BASE_SKALAR * skalar);
        pheigth = (int)(screendim.getY() * BASE_SKALAR * skalar);
        this.g = g;
    }

    public void drawLine(Line l){
        drawLine(l, Color.MAGENTA);
    }

    public void drawVector(Vector v){
        drawVector(v, 4);
    }

    public void drawVector(Vector v, int size){
        g.setColor(Color.ORANGE);
        ScreenPos pos = getScreenPos(v);
        g.fillOval(pos.x - size/2, pos.y - size/2, size, size);
    }

    public void drawLine(Line l, Color c){
        g.setColor(c);
        ScreenPos start = getScreenPos(l.getStart());
        ScreenPos end = getScreenPos(l.getEnd());
        System.out.println(start.x + ", " + start.y);
        g.drawLine(start.x, start.y, end.x, end.y);
    }

    public void drawRay(Ray ray){
        g.setColor(Color.BLUE);
        ScreenPos start = getScreenPos(ray.getStart());
        ScreenPos end = getScreenPos(ray.getStart().add(ray.getDir().multiply(10)));
        g.drawLine(start.x, start.y, end.x, end.y);
    }

    private ScreenPos getScreenPos(Vector vector){
        Vector onScreen = vector.substract(offset);
        double x = onScreen.getX();
        double y = screendim.getY() - onScreen.getY();
        int px = (int)((x / screendim.getX()) * pwidth);
        int py = (int)((y / screendim.getY()) * pheigth);
        return new ScreenPos(px, py);
    }

    private class ScreenPos{
        public int x;
        public int y;

        public ScreenPos(int in_x, int in_y){
            x = in_x;
            y = in_y;
        }
    }

}
