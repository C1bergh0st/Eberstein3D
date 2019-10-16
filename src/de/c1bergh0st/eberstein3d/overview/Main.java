package de.c1bergh0st.eberstein3d.overview;

import de.c1bergh0st.eberstein3d.input.InputHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Main implements Runnable{

    public static void main(String[] args){
        new Main().run();
    }

    @Override
    public void run() {
        JFrame frame = new JFrame("Eberstein3D");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        Canvas canvas = new Canvas();
        canvas.setSize(1920, 1080);
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);
        InputHandler handler = new InputHandler();
        handler.setSource((JPanel) frame.getContentPane());
        World w = new World(handler);
        canvas.createBufferStrategy(2);
        while (true){
            BufferStrategy bs = canvas.getBufferStrategy();
            Graphics2D g = (Graphics2D) bs.getDrawGraphics();
            g.setStroke(new BasicStroke(4));
            g.setColor(Color.GRAY);
            g.fillRect(0,0, 1920, 1080);
            w.tick();
            w.draw(g);
            g.dispose();
            bs.show();
        }
    }
}
