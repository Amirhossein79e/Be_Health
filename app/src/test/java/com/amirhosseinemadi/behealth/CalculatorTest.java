package com.amirhosseinemadi.behealth;

import com.amirhosseinemadi.behealth.model.others.Calculator;
import org.junit.Assert;
import org.junit.Test;

public class CalculatorTest {

    Calculator calculator = new Calculator();

    @Test
    public void bmiTest()
    {
        Assert.assertEquals(Float.parseFloat(calculator.calculateBmi(185,71f)),20.7f,0f);
    }

    @Test
    public void strideTest()
    {
        Assert.assertEquals(calculator.calculateStride("male",185),76.775f,0f);
    }


    @Test
    public void distanceTest()
    {
        Assert.assertEquals(calculator.calculateDistance(7901f,76.775f),6065,1f);
    }
}
