package de.c1bergh0st.eberstein3d.overview;

import de.c1bergh0st.eberstein3d.math.Line;
import de.c1bergh0st.eberstein3d.math.Ray;
import de.c1bergh0st.eberstein3d.math.Vector;

import java.awt.*;
import java.util.List;

public class Player {
    private Vector position;
    private Vector direction;
    private double radius;
    private int fov = 80;
    private int pixelsPerDegree = 2;


    public Player(Vector pos, Vector dir){
        position = pos;
        direction = dir;
    }


    public void draw(Camera c, List<LineObject> objects, View view){
        //2D Overview
        //Player Position
        c.drawVector(position,40);
        //Player angle
        c.drawRay(new Ray(position, direction));
        //Field of View
        c.drawRay(new Ray(position, direction.rotate(-fov/2d)));
        c.drawRay(new Ray(position, direction.rotate(fov/2d)));

        //View pane
        Vector viewstart = position.add(direction.rotate(40).multiply(1d / Math.cos(Math.toRadians(40))));
        Vector viewend = position.add(direction.rotate(-40).multiply(1d / Math.cos(Math.toRadians(40))));
        c.drawLine(new Line(viewstart, viewend), Color.orange);


        for(int i = -(fov/2) * pixelsPerDegree; i <= (fov/2) * pixelsPerDegree; i++){
            Ray r = new Ray(position, direction.rotate((double)(i) / pixelsPerDegree));
            Vector nearest = null;
            Color nearestC = null;
            for(LineObject lineObject: objects){
                for(Line line: lineObject.getLines()){
                    Vector point = r.getIntersectionPoint(line);
                    if(point != null){
                        c.drawVector(point, 8);
                        if(nearest == null || point.dist(position) < nearest.dist(position)){
                            nearest = point;
                            nearestC = lineObject.getColor();
                        }
                    }
                }
            }
            if(nearest != null){
                double angl = Math.abs(i);
                double distance = nearest.dist(position) - (0.3d / Math.cos(Math.toRadians(angl) / pixelsPerDegree));
                if(distance < 0.3){
                    distance = 0.3;
                }
                view.drawColumn((fov - (i + fov/2)),  (int)(350* (pixelsPerDegree / distance)), nearestC, pixelsPerDegree);
            } else {
                view.drawColumn((fov - (i + fov/2)),  0, Color.WHITE, pixelsPerDegree);
            }
        }
    }

    public void move(double distance){
        position = position.add(direction.multiply(distance));
    }

    public void setDir(Vector v){
        direction = v.normalize();
    }

    public Vector getDir(){
        return direction;
    }

}
