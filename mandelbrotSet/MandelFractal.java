package mandelbrot;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelWriter;
import java.awt.Color;

public class MandelFractal implements ComplexDrawable {

    double r;

    public MandelFractal(double r){
        this.r = r * r;
    }

    @Override
    public void draw(PixelWriter pw, Complex a, Complex b, int w, int h) {
        int[] buffer = new int[w * h];
        Complex p = new Complex();

        double scaleR = (b.re() - a.re())/w;
        double scaleI = (b.im() - a.im())/h;

        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                p.setRe(a.re() + scaleR * x);
                p.setIm(a.im() + scaleI * y);
                buffer[w * y + x] = setColors(p);

            }

        }

        pw.setPixels(0, 0, w, h, PixelFormat.getIntArgbInstance(), buffer, 0,  w);
    }

    public int setColors(Complex p) {

        Complex z = new Complex();
        int i = 0;
        float iterations = 100;
        for (; i < iterations; i++) {
            z.mul(z);
            z.add(p);
            if (z.sqrAbs() >= this.r) break;
        }

        if (i == iterations) return 0xFF000000;
        else return Color.HSBtoRGB(i/iterations, 0.45f, 1); // number/256

    }

}
