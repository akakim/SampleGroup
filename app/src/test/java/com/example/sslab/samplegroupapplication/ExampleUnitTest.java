package com.example.sslab.samplegroupapplication;

import com.example.sslab.samplegroupapplication.testActivity.testCase.Adder;

import junit.framework.TestCase;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest extends TestCase {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void adderTest(){
        Adder adder = new Adder();
        assertEquals(5,adder.add(2,3));

        for(int k = 0;k<=6;k++){
            int x = 6-k;
            assertEquals(6,adder.add(k,x));
        }
    }

    @Test
    public void subTractTest(){
        Adder adder = new Adder();
        assertEquals(-1,adder.subtract(2,3));

        for(int k = 0;k<=6;k++){
            int x = 6-k;
            assertEquals(k-x,adder.subtract(k,x));
        }
    }
}