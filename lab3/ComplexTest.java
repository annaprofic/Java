import org.junit.Assert;

/**
 * Klasa w ktorej wykonujemy testy metod potrzebnych dla dzialania na liczbach zespolonych opisanych w klasie Complex
 * @author Anna Sarnavska
 */

public class ComplexTest {

    void testAdd() {
        Complex a = new Complex("4-7i");
        Complex b = Complex.valueOf("-3+2i");
        a.add(b);
        String result = a.toString();

        assert result.equals("1.0-5.0i");
    }

    private static void testStaticAdd() {
        Complex a = new Complex("4-7i");
        Complex b = Complex.valueOf("-3+2i");
        a = Complex.add(a, b);
        String result = a.toString();

        assert result.equals("1.0-5.0i");
    }

    void testSub() {
        Complex a = new Complex(-1.2,3.4);
        Complex b = Complex.valueOf("5.6-7.8i");
        a.sub(b);
        String result = a.toString();

        assert result.equals("-6.8+11.2i");
    }

    private static void testStaticSub() {
        Complex a = new Complex("-1.2+3.4i");
        Complex b = Complex.valueOf("5.6-7.8i");
        a = Complex.sub(a, b);
        String result = a.toString();

        assert result.equals("-6.8+11.2i");
    }

    void testMul() {
        Complex a = new Complex("-1.2+3.4i");
        Complex b = Complex.valueOf("5.6-7.8i");
        a.mul(b);
        String result = a.toString();

        assert result.equals("19.8+28.4i");
    }

    private static void testStaticMul() {
        Complex a = new Complex(-1.2,3.4);
        Complex b = Complex.valueOf("5.6-7.8i");
        Complex c = Complex.mul(a, b);
        String result = c.toString();

        assert result.equals("19.8+28.4i");
    }

    void testDiv() throws DivideByZeroException {
        Complex a = new Complex("2+3i");
        Complex b = Complex.valueOf("3-1i");
        a.div(b);
        String result = a.toString();

        assert result.equals("0.3+1.1i");
    }

    private static void testStaticDiv() throws DivideByZeroException {
        Complex a = new Complex(2, 3);
        Complex b = Complex.valueOf("3-1i");
        a = Complex.div(a, b);
        String result = a.toString();
        assert result.equals("0.3+1.1i");
    }

    void testPhase(){
        Complex a = new Complex("1+1i");
        Complex b = new Complex();
        b.setVal(a);
        double result = b.phase(a);
        Assert.assertEquals(Math.PI / 4, result, 0);
    }

    private static void testStaticPhase() {
        Complex a = new Complex(1, 1);
        double phase = Complex.phaseStatic(a);
        Assert.assertEquals(Math.PI / 4, phase, 0);
    }

    void testAbs() {
        Complex a = new Complex("-2+2i");
        Complex b = new Complex();
        b.setVal(a);
        double abs = b.abs();
        Assert.assertEquals(2 * Math.sqrt(2), abs, 0);

    }

    private static void testStaticAbs() {
        Complex a = new Complex(-2, 2);
        double abs = Complex.abs(a);
        Assert.assertEquals(2 * Math.sqrt(2), abs, 0);
    }

    public static void main(String[] args) throws DivideByZeroException {

        ComplexTest comp = new ComplexTest();
        comp.testAdd();
        testStaticAdd();
        comp.testSub();
        testStaticSub();
        comp.testMul();
        testStaticMul();
        comp.testDiv();
        testStaticDiv();
        comp.testPhase();
        testStaticPhase();
        comp.testAbs();
        testStaticAbs();

    }

}

