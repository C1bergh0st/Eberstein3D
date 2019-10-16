package de.c1bergh0st.eberstein3d.overview;

import java.awt.*;

public class View {
    private Graphics2D g;

    public View(Graphics2D g){
        this.g = g;
        g.setColor(Color.WHITE);
        g.fillRect(1120, 0, 1920, 450);
    }

    public void drawColumn(int id, int heigth, Color c, int pixelsPerDegree){
        double scale = 450d / heigth;
        scale += 0.7;
        int width = 10 / pixelsPerDegree;
        if (heigth > 450) heigth = 450;
        int[] colors = new int[3];
        int rgb = (int) (c.getRGB() * scale);
        colors[0] = c.getRed();
        colors[1] = c.getGreen();
        colors[2] = c.getBlue();
        for(int i = 0; i < 3; i++){
            colors[i] = (int) (colors[i] / (scale /2));
            if(colors[i] > 255){
                colors[i] = 255;
            }
            if(colors[i] < 0){
                colors[i] = 0;
            }
        }
        g.setColor(new Color(colors[0], colors[1], colors[2]));
        //Wall
        g.fillRect(1120 + id * width, 225 - (heigth/2)  , width, heigth);

        //Ceiling
        g.setColor(Color.WHITE);
        g.fillRect(1120 + id * width, 0  , width, 225 - (heigth/2));
        //Ground
        g.setColor(Color.DARK_GRAY);
        g.fillRect(1120 + id * width, heigth + (225 - (heigth/2))  , width, 225 - (heigth/2));
    }


}
