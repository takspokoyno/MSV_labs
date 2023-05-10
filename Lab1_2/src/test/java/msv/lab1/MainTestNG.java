package msv.lab1;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.Assert;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class MainTestNG {
    public static double x;
    public static double y;
    public static double z;
    public static double h;
    public static double tan;
    public static double eps;
    public static double yBalfa;

    @BeforeMethod(groups={"groupA", "groupB"})
    public void init() {
        x = 2.0;
        y = 3.0;
        z = 4.0;
        h = 0.1;
        tan = 0.5;
        eps = 1e-4;
        yBalfa = 0.617191;
    }

    @Test(groups="groupA")
    public void testFunc() {
        double expected = 4.164;
        double result = Main.func(x, y, z, h);
        Assert.assertEquals(result, expected, eps);
    }

    @Test(groups="groupA")
    public void testFuncWithZeroStep() {
        double expected = z;
        double result = Main.func(x, y, z, 0);
        Assert.assertEquals(result, expected, eps);
    }

    @Test(groups="groupA")
    public void testYBAlfa() {
        double expected = 2.05796;
        double result = Main.yBAlfa(tan, h);

        Assert.assertEquals(result, expected, eps);
    }

    @Test(dataProvider = "comparisonData", groups="groupA")
    void testComparisonParameterized(double y1, int expected) {
        double result = Main.comparison(tan, yBalfa, 0.01, y1);
        Assert.assertEquals(result, expected);
    }

    @DataProvider
    public Object[][] comparisonData() {
        return new Object[][] {
                { 0.6, -1 },
                { 0.59, -1 },
                { 0.63, 1 },
                { 0.617191, 0 },
                { 0.5, -1 }
        };
    }

    @Test(dataProvider = "nextAlfaData", groups="groupB")
    void testNextAlfa(double value) {
        double comp1 = value;
        double comp2 = -1;
        tan = Math.tan(value/2);
        double tan2 = Math.tan(value/3);

        double unexpected = 1000;
        double actual = Main.nextAlfa(comp1, tan, comp2, tan2);

        Assert.assertNotEquals(actual, unexpected);
    }

    @DataProvider
    public Object[][] nextAlfaData() {
        return new Object[][] {
                { 1 },
                { -1 }
        };
    }

    @Test(groups="groupB")
    void testCalculationThrowsException() {
        eps = 0.1;
        Assert.assertThrows(IllegalArgumentException.class, () -> Main.calculation(tan, 0, eps, 2));
    }

    @Test(groups="groupB")
    void testConsoleOutput() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Main.main();

        Assert.assertTrue(outContent.toString().contains("Alfa next < this alfa"));
        Assert.assertTrue(outContent.toString().startsWith("y(1)="));
    }
}
