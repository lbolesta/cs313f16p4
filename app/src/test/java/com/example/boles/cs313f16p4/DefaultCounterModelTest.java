package com.example.boles.cs313f16p4;

//package edu.luc.etl.cs313.android.simplecounter.model.counter;

        import junit.framework.TestCase;

/**
 * @author laufer
 */
public class DefaultCounterModelTest extends TestCase {
    private static int DEFAULTCAOUNTER=5;
    DefaultCounterModel counter;

    public void setUp() throws Exception {
        super.setUp();
        counter = new DefaultCounterModel(DEFAULTCAOUNTER);

    }

    public void tearDown() throws Exception {

    }

    public void testResetCounter() throws Exception {
        counter.resetCounter();
        assertEquals(0,counter.getCounter() );

    }

    public void testResetCounter1() throws Exception {
        counter.resetCounter(-10);
        assertEquals(0,counter.getCounter() );

        counter.resetCounter(99999);
        assertEquals(99,counter.getCounter() );

        counter.resetCounter(0);
        assertEquals(0,counter.getCounter() );


    }

    public void testIncCounter() throws Exception {
        int v_dExpected=counter.getCounter()+1;
        counter.incCounter();
        assertEquals(v_dExpected,counter.getCounter() );

    }

    public void testDecrCounter() throws Exception {
        int v_dExpected=counter.getCounter()-1;
        counter.decrCounter();
        assertEquals(v_dExpected,counter.getCounter() );


    }

    public void testGetCounter() throws Exception {
        assertEquals(DEFAULTCAOUNTER,counter.getCounter() );

    }

}