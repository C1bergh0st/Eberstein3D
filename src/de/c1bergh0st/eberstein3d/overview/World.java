package de.c1bergh0st.eberstein3d.overview;

import de.c1bergh0st.eberstein3d.input.InputHandler;
import de.c1bergh0st.eberstein3d.math.Line;
import de.c1bergh0st.eberstein3d.math.Ray;
import de.c1bergh0st.eberstein3d.math.Vector;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class World {
    private List<LineObject> objects;
    private List<Ray> rays;
    public InputHandler handler;
    private Ray testRay;
    private Player player;

    public World(InputHandler handler){
        this.handler = handler;
        objects = new ArrayList<>();
        rays = new ArrayList<>();
        List<Vector> l = new LinkedList<>();
        l.add(new Vector(1,0));
        l.add(new Vector(1,2));
        l.add(new Vector(0,2));
        l.add(new Vector(1,0));
        objects.add(new LineObject(l, Color.BLUE));

        l.clear();
        l.add(new Vector(1, 0));
        l.add(new Vector(1, 1));
        l.add(new Vector(11, 1));
        l.add(new Vector(11, 0));
        l.add(new Vector(1, 0));
        objects.add(new LineObject(l, Color.LIGHT_GRAY));

        l.clear();
        l.add(new Vector(10, 0));
        l.add(new Vector(10, 5));
        l.add(new Vector(11, 0));
        l.add(new Vector(10, 0));
        objects.add(new LineObject(l, Color.MAGENTA.darker().darker()));


        l.clear();
        l.add(new Vector(11,5));
        l.add(new Vector(11,4));
        l.add(new Vector(8,4));
        l.add(new Vector(8,5));
        l.add(new Vector(11,5));
        objects.add(new LineObject(l, Color.YELLOW));


        l.clear();
        l.add(new Vector(7, 3));
        l.add(new Vector(8, 3));
        l.add(new Vector(8, 6));
        l.add(new Vector(7, 6));
        l.add(new Vector(7, 3));
        objects.add(new LineObject(l, Color.BLUE.brighter().brighter()));



        l.clear();
        l.add(new Vector(5, 5));
        l.add(new Vector(5, 7));
        l.add(new Vector(6, 7));
        l.add(new Vector(6, 5));
        l.add(new Vector(5, 5));
        objects.add(new LineObject(l, Color.GREEN.darker()));

        l.clear();
        l.add(new Vector(4, 4));
        l.add(new Vector(6, 6));
        l.add(new Vector(3, 6));
        l.add(new Vector(4, 4));
        objects.add(new LineObject(l, Color.RED.darker()));


        //rays.add(new Ray(new Vector(1,1), new Vector(1,1)));
        //rays.add(new Ray(new Vector(1,3), new Vector(1,1)));
        //testRay = new Ray(new Vector(5,5), new Vector(1,1));
        //rays.add(testRay);
        player = new Player(new Vector(5, 5), new Vector(0, 1));
    }

    public void tick(){
        if(handler.isDown("left")){
            player.setDir(player.getDir().rotate(0.5));
        }
        if(handler.isDown("right")){
            player.setDir(player.getDir().rotate(-0.5));
        }
        if(handler.isDown("up")){
            player.move(0.03);
        }
        if(handler.isDown("down")){
            player.move(-0.03);
        }
    }

    public void draw(Graphics2D g){
        Camera cam = new Camera(new Vector(), new Vector(16,9), 1, g);
        for(LineObject lineObject: objects){
            for(Line line: lineObject.getLines()){
                cam.drawLine(line, lineObject.getColor());
            }
        }

       /* for (Ray ray : rays){
            cam.drawRay(ray);
            for(LineObject lineObject: objects){
                for(Line line: lineObject.getLines()){
                    Vector point = ray.getIntersectionPoint(line);
                    if(point != null){
                        cam.drawVector(point);
                    }
                }
            }
        }*/

        View view = new View(g);
        player.draw(cam, objects, view);
    }
}
