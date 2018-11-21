package mandelbrot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Controller {

    public int w = 512;
    public int h = 512;
    public double r = 4d;
    public Canvas canvas;
    public Canvas canvas2;
    private GraphicsContext gc;
    private GraphicsContext gc2;
    private double x1, y1, x2, y2;
    private boolean mandelbrotOn = false;
    private boolean buttonMandel = false;
    public final Complex a = new Complex(-2, 2);
    public final Complex b = new Complex(2, -2);
    public double re1 = 0d;
    public double im1 = 0d;
    public double re2 = 0d;
    public double im2 = 0d;
    public MandelFractal mf;

    public Label label;
    @FXML
    private TextField width;
    @FXML
    private TextField height;
    @FXML
    private TextField px1;
    @FXML
    private TextField py1;
    @FXML
    private TextField px2;
    @FXML
    private TextField py2;
    @FXML
    private TextField parR;
    @FXML

    public void tab() {
        mandelbrotOn = !mandelbrotOn;
    }

    public void buttonMandelbrot(ActionEvent actionEvent) {
        buttonMandel =  true;
        drawMandelbrot();
    }

    public void initialize() {
        gc = canvas.getGraphicsContext2D();
        gc2 = canvas2.getGraphicsContext2D();
        clear(gc);
        clear(gc2);
    }

    public void setParameters(ActionEvent actionEvent) {

        if(!width.getText().equalsIgnoreCase("width")) {
            this.w = Integer.parseInt(this.width.getText());
        } else w =  512;

        if (!height.getText().equalsIgnoreCase("height")) {
            this.h = Integer.parseInt(this.height.getText());
        } else h = 512;

        canvas2.setWidth((double)w);
        canvas2.setHeight((double)h);

        if (!parR.getText().equalsIgnoreCase("R")) {
            r = Double.parseDouble(parR.getText());
        } else r = 4d;

        if(!px1.getText().equalsIgnoreCase("first real")) {
            this.re1 = Double.parseDouble(px1.getText());
            a.setRe(this.re1);
        } else a.setRe(-2);

        if (!py1.getText().equalsIgnoreCase("first im")) {
            this.im1 = Double.parseDouble(py1.getText());
            a.setIm(this.im1);
        } else a.setRe(-2);

        if(!px2.getText().equalsIgnoreCase("second real")) {
            this.re2 = Double.parseDouble(px2.getText());
            b.setRe(this.re2);
        } else b.setRe(2);

        if (!py2.getText().equalsIgnoreCase("second im")) {
            this.im2 = Double.parseDouble(py2.getText());
            b.setIm(this.im2);
        } else b.setIm(2);

    }


    private void clear(GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.setGlobalBlendMode(BlendMode.SRC_OVER);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    private void rect(GraphicsContext gc) {

        double x = x1;
        double y = y1;
        double w = x2 - x1;
        double h = y2 - y1;

        if (w < 0) {
            x = x2;
            w = -w;
        }

        if (h < 0) {
            y = y2;
            h = -h;
        }

        gc.strokeRect(x + 0.5, y + 0.5, w, h);
    }

    public void sayHello(ActionEvent actionEvent) {
        label.setText("Hello");
    }


    public void mouseMoves(MouseEvent mouseEvent) {
        double x = mouseEvent.getX();
        double y = mouseEvent.getY();
        if(!mandelbrotOn) {
            gc.setGlobalBlendMode(BlendMode.DIFFERENCE);
            gc.setStroke(Color.WHITE);
            rect(gc);
            x2 = x;
            y2 = y;
            rect(gc);
        } else {
            gc2.setGlobalBlendMode(BlendMode.DIFFERENCE);
            gc2.setStroke(Color.WHITE);
            rect(gc2);
            x2 = x;
            y2 = y;
            rect(gc2);
        }

    }

    public void drawRect(ActionEvent actionEvent) {
        gc.setStroke(Color.web("#FFF0F0"));
        gc.setGlobalBlendMode(BlendMode.MULTIPLY);
        gc.strokeRect(100.5, 100.5, 200, 200);

    }

    public void mousePressed(MouseEvent mouseEvent) {
        x1 = mouseEvent.getX();
        y1 = mouseEvent.getY();
        x2 = x1;
        y2 = y1;
    }

    public void mouseReleased(MouseEvent mouseEvent) {
        double tempX, tempY;
        if(mandelbrotOn) {
            rect(gc2);
        }
        else rect(gc);

        System.out.format("%f %f %f %f\n", x1, y1, x2, y2);

        if (x1 > x2) {
            tempX = x1;
            x1 = x2;
            x2 = tempX;
        }

        if (y1 > y2) {
            tempY = y1;
            y1 = y2;
            y2 = tempY;
        }

        if (mandelbrotOn && buttonMandel) {
            scaleMandelbrot();
            drawMandelbrot();
        }
    }

    public void clearCanvas(ActionEvent actionEvent) {
        clear(gc);
    }

    public void clearCanvas2(ActionEvent actionEvent) {
        buttonMandel = false;
        w = 512;
        h = 512;
        width.setText("Width");
        height.setText("Height");
        parR.setText("R");
        px1.setText("1st real nr");
        py1.setText("1st im nr");
        px2.setText("2st real nr");
        py2.setText("2st im nr");
        clear(gc2);

        a.setVal(-2, -2);
        b.setVal(2, 2);

//        upperLeft.setVal(a);
//        lowerRight.setVal(b);
    }


    public void draw(ActionEvent actionEvent) {

        final int size = 512;
        WritableImage wr = new WritableImage(size, size);
        PixelWriter pw = wr.getPixelWriter();

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                pw.setArgb(x, y, (x & y) == 0 ? 0xFFFF00FF : 0xFFFFFFFF);
            }
        }

        gc.setGlobalBlendMode(BlendMode.SRC_OVER);
        gc.drawImage(wr, 0, 0, 512, 512);

    }

    public void drawMandelbrot() {
        mf = new MandelFractal(this.r);
        WritableImage wr = new WritableImage(w, h);
        PixelWriter pw = wr.getPixelWriter();

        mf.draw(pw, a, b, w, h);

        gc2.setGlobalBlendMode(BlendMode.SRC_OVER);
        gc2.drawImage(wr, 0, 0, w, h);
    }

    public void scaleMandelbrot() {

        double scaleR = (b.re() - a.re())/w;
        double scaleI = (b.im() - a.im())/h;

        a.setRe(a.re() + scaleR * x1);
        a.setIm(a.im() + scaleI * y1);

        b.setRe(b.re() - scaleR * (w - x2));
        b.setIm(b.im() - scaleI * (h - y2));
    }


}