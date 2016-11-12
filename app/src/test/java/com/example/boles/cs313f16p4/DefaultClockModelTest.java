package com.example.boles.cs313f16p4;

//package edu.luc.etl.cs313.android.simplecounter.model.clock;

        import junit.framework.TestCase;

        import static java.lang.Thread.sleep;

/**
 * @author laufer
 */
public class DefaultClockModelTest extends TestCase {

    private DefaultClockModel clock;
    private int counter;

    public void setUp() throws Exception {
        super.setUp();

        clock = new DefaultClockModel();
        counter=0;

        clock.setOnTickListener(new  OnTickListener(){
            @Override
            public void onTick() {
                counter++;
            }
        });
    }

    public void tearDown() throws Exception {
        clock.stop();
        clock=null;

    }

    public void testSetOnTickListener() throws Exception {

    }

    public void testStart() throws Exception {
        int currentValue=counter;
        clock.start();
        sleep(1500);
        clock.stop();

        assertTrue(counter > currentValue);

    }

    public void testStop() throws Exception {

        clock.start();
        sleep(1500);
        clock.stop();
        int currentValue=counter;
        sleep(1500);
        assertEquals (counter,currentValue);


    }

}
