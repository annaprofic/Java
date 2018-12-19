package balls;

import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlendMode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Playground {

    public Canvas canvas;
    private GraphicsContext gc;
    public Rectangle box;
    private double height = 489.0;
    private double width = 755.0;

    public void initialize() {
        gc = canvas.getGraphicsContext2D();
        box = new Box();
        box.setStroke(Color.GRAY);
        box.setFill(Color.rgb(200,200,200, 0));
        box.setWidth(200);
        box.setHeight(200);
        box.setX(278);
        box.setY(200);
        clear(gc);
    }

    private void clear(GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.setGlobalBlendMode(BlendMode.SRC_OVER);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    public void addBall(ActionEvent actionEvent){
        Ball ball = new Ball(gc, (Box)box);
        Thread newBall = new Thread(ball);
        newBall.start();
    }

    public void awake(){
        synchronized (box) {
            ((Box)box).enter();
        }
    }
}




