package de.c1bergh0st.eberstein3d.math;

public class Ray {
    private Vector start;
    private Vector dir;

    public Ray(Vector start, Vector dir){
        this.start = start;
        this.dir = dir.normalize();
    }

    public void setDir(Vector v){
        dir = v.normalize();
    }

    public Vector getStart() {
        return start;
    }

    public Vector getDir() {
        return dir;
    }

    public boolean intersects(Line line){
        return getIntersectionPoint(line) != null;
    }

    public Vector getIntersectionPoint(Line line){
        Vector linedir = line.getEnd().substract(line.getStart());
        double factor = dir.crossProduct(linedir);
        if(factor == 0){
            return null;
        }
        double upper = line.getStart().substract(start).crossProduct(linedir);
        double u = upper / factor;
        if (u < 0) return null;
        Vector intersectionPoint = start.add(dir.multiply(u));
        if(intersectionPoint.dist(line.getStart()) > linedir.abs() ||
                intersectionPoint.dist(line.getEnd()) > linedir.abs()){
            return null;
        }
        return intersectionPoint;
    }

}
