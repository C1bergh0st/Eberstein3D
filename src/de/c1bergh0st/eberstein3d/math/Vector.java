package de.c1bergh0st.eberstein3d.math;

public class Vector {
    private final double x;
    private final double y;

    public Vector(double x, double y){
        this.x = x;
        this.y = y;
    }

    public Vector(){
        this(0, 0);
    }

    public Vector add(Vector v){
        return new Vector(this.x+ v.x, this.y + v.y);
    }

    public Vector multiply(double skalar){
        return new Vector(this.x * skalar, this.y * skalar);
    }

    public Vector substract(Vector v){
        return this.add(v.multiply(-1d));
    }

    public double dotProduct(Vector v){
        return this.x * v.x + this.y * y;
    }

    public double crossProduct(Vector v){
        return this.x * v.y - this.y * v.x;
    }

    public Vector rotate(double angle){
        double cs = Math.cos(Math.toRadians(angle));
        double sn = Math.sin(Math.toRadians(angle));
        double px = x * cs - y * sn;
        double py = x * sn + y * cs;
        return new Vector(px, py);
    }

    public double dist(Vector v){
        return this.substract(v).abs();
    }

    public double abs(){
        return Math.sqrt(x * x + y * y);
    }

    public Vector normalize(){
        return new Vector(x / abs(), y / abs());
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    // This might be unsafe, but will suffice for my applications
    public boolean equals(Object o){
        if (!(o instanceof Vector)){
            return false;
        }
        Vector other = (Vector)o;
        long x = (long)(this.x * 100000);
        long y = (long)(this.y * 100000);
        long ox = (long)(other.x * 100000);
        long oy = (long)(other.y * 100000);
        return x == ox && y == oy;
    }

    public String toString(){
        return "(" + String.format("%.23f", x) + " , " + String.format("%.23f", y) + ")";
    }
}
