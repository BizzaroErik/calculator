package com.example.android.calcarithmatic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    private Calculator calc;
    @Before
    public void createCalculator() {
        calc = new Calculator();
        assertNotNull(calc);
    }

    @Test
    public void checkAdd(){
        calc.setOperation("+");
        Double output;
        output = calc.putNumber("5");
        output = calc.putNumber("10");
        assertEquals(15.0, (double)output, 0);
    }

    @Test
    public void checkSubtract(){
        calc.setOperation("-");
        Double output;
        output = calc.putNumber("5");
        output = calc.putNumber("10");
        assertEquals(-5.0, (double)output, 0);

    }

    @Test
    public void checkMultiply(){
        calc.setOperation("*");
        Double output;
        output = calc.putNumber("5");
        output = calc.putNumber("10");
        assertEquals(50.0, (double)output, 0);
    }

    @Test
    public void checkDivide(){
        calc.setOperation("/");
        Double output;
        output = calc.putNumber("5");
        output = calc.putNumber("10");
        assertEquals(0.5, (double)output, 0);
    }

    @Test
    public void checkSin(){
        Double output = calc.calculateTrigFunction("Sin", "90");
        assertEquals(0.8939966636, (double)output, 0.00002);
    }

    @Test
    public void checkCos(){
        Double output = calc.calculateTrigFunction("Cos", "90");
        assertEquals(-0.44807361612, (double)output, 0.00002);
    }

    @Test
    public void checkTan(){
        Double output = calc.calculateTrigFunction("Tan", "45");
        assertEquals(1.61977519054, (double)output, 0.0000002);
    }

}