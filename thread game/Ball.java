package balls;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;

public class Ball extends Thread {
    private double x, y, xBox, yBox, x1Box, y1Box, clearX, clearY;
    public boolean go = false;
    private double height = 520.0;
    private double width = 755.0;
    private GraphicsContext gc;
    private Box box;
    private Color color;
    private double XV;
    private double YV;

    public Ball(GraphicsContext gc, Box box) {
        this.gc = gc;
        this.box = box;
        color = genColor();
        x = 150;
        y = 350;
        xBox = box.getX();
        yBox = box.getY();
        x1Box = xBox + box.getWidth();
        y1Box = yBox + box.getHeight();
        genVector();
    }

    private void genVector(){
        Random rand = new Random();
        XV = 1 + rand.nextInt(3);
        YV = 1 + rand.nextInt(3);
        if(rand.nextBoolean()) YV *= -1;
        if(rand.nextBoolean()) XV *= -1;
    }

    public void check() {
        go = true;
    }


    public boolean inside() {
        if (x + 10 >= xBox && x - 10 <= x1Box && y + 10 >= yBox  && y - 10 <= y1Box ) {
            return true;
        }
        return false;
    }

    public void move(){
        clearX = x;
        clearY = y;
        x += XV;
        y += YV;

        if (y <= 37 || y >= height - 10) YV *= -1;
        if (x <= 10 || x >= width - 10) XV *= -1;


        if (!inside()) go = false;

        if (x + 8 >= xBox && x - 8 <= x1Box && y + 8 >= yBox && y - 8 <= y1Box) {
            if(!go) {
                synchronized (box) {
                    try {
                        System.out.println("x = " + x + ", y = " + y);
                        box.wait();
                        go = true;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    public void draw() {
        synchronized (gc) {

            gc.setFill(Color.WHITE);
            gc.setStroke(Color.WHITE);
            gc.fillOval(clearX - 10.5, clearY - 10.5, 21, 21);
            gc.setFill(color);
            gc.setStroke(color);
            gc.fillOval(x - 10, y - 10, 20, 20);
        }
    }

    @Override
    public void run() {

        while(true) {
            try {
                draw();
                move();
                Thread.sleep(10);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private Color genColor(){
        return new Color(Math.random(), Math.random(), Math.random(), 0.8);
    }


    public int getX(){
        return (int)x;
    }

    public int getY(){
        return (int)y;
    }

}



