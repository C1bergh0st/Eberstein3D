package de.c1bergh0st.eberstein3d.overview;

import de.c1bergh0st.eberstein3d.math.Line;
import de.c1bergh0st.eberstein3d.math.Vector;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class LineObject implements Drawable{
    private List<Vector> vertecies;
    private Color color;

    public LineObject(Collection<Vector> vertecies, Color color){
        this.vertecies = new ArrayList<>();
        this.vertecies.addAll(vertecies);
        this.color = color;
    }

    public LineObject(){
        this(new LinkedList<>(), Color.MAGENTA);
    }

    public void add(Vector v){
        vertecies.add(v);
    }

    @Override
    public List<Line> getLines() {
        List<Line> lines = new ArrayList<>();
        if(vertecies.size() == 1){
            lines.add(new Line(vertecies.get(0), vertecies.get(0)));
            return lines;
        }
        for(int i = 1; i < vertecies.size(); i++){
            lines.add(new Line(vertecies.get(i - 1), vertecies.get(i)));
        }
        return lines;
    }

    @Override
    public Color getColor() {
        return color;
    }
}
