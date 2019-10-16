package de.c1bergh0st.eberstein3d.math;

import java.util.List;

public class Line implements Cloneable{
    private Vector start;
    private Vector end;

    public Line(Vector start, Vector end){
        this.start = start;
        this.end = end;
    }

    public Line(Vector end){
        this(new Vector(), end);
    }

    public Line(){
        this(new Vector());
    }

    public Vector getStart() {
        return start;
    }

    public void setStart(Vector start) {
        this.start = start;
    }

    public Vector getEnd() {
        return end;
    }

    public void setEnd(Vector end) {
        this.end = end;
    }

    public Object clone(){
        return new Line(start, end);
    }
}
