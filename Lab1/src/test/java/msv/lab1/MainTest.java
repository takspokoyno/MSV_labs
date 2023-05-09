package msv.lab1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    public static double x;
    public static double y;
    public static double z;
    public static double h;
    public static double tan;
    public static double eps;
    public static double yBalfa;
    @BeforeEach
    void setUp() {
        x = 2.0;
        y = 3.0;
        z = 4.0;
        h = 0.1;
        tan = 0.5;
        eps = 1e-4;
        yBalfa = 0.617191;
    }

    @Test
    public void testFunc() {
        double expected = 4.164;
        double result = Main.func(x, y, z, h);
        assertEquals(expected, result, eps);
    }

    @Test
    public void testFuncWithZeroStep() {
        h = 0.0;
        double expected = z;
        double result = Main.func(x, y, z, h);
        assertEquals(expected, result, eps);
    }

    @Test
    public void testYBAlfa() {
        double expected = 2.05796;
        double result = Main.yBAlfa(tan, h);

        assertEquals(expected, result, eps);
    }

    @ParameterizedTest
    @CsvSource({"0.6, -1", "0.59, -1", "0.63, 1", "0.617191, 0", "0.5, -1"})
    void testComparisonParameterized(double y1, int expected) {
        eps = 0.01;
        double result = Main.comparison(tan, yBalfa, eps, y1);
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @ValueSource(doubles = {1, -1})
    void testNextAlfa(double value) {
        double comp1 = value;
        double comp2 = -1;
        tan = Math.tan(value/2);
        double tan2 = Math.tan(value/3);

        double unexpected = 1000;
        double actual = Main.nextAlfa(comp1, tan, comp2, tan2);

        assertNotEquals(unexpected, actual);
    }

    @Test
    void testCalculationThrowsException() {
        h = 0;
        eps = 0.1;
        y = 2;

        assertThrows(IllegalArgumentException.class, () -> Main.calculation(tan, h, eps, y));
    }

    @Test
    void testConsoleOutput() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Main.main();

        assertThat(outContent.toString(), containsString("Alfa next < this alfa"));
        assertThat(outContent.toString(), startsWith("y(1)="));
    }
}