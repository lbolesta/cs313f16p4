package com.example.boles.cs313f16p4;

//package edu.luc.etl.cs313.android.simplecounter.model.clock;

        import java.util.Timer;
        import java.util.TimerTask;

/**
 * An implementation of the internal clock.
 *
 * @author laufer
 */
public class DefaultClockModel implements ClockModel {

    // TODO make accurate by keeping track of partial seconds when canceled etc.
    // Done. Timer restarts every time the waiting period starts.

    private Timer timer;

    private OnTickListener listener;

    @Override
    public void setOnTickListener(final OnTickListener listener) {
        this.listener = listener;
    }

    @Override
    public void start() {
// Stop old timer if any...
        if (timer!= null) {
            stop();
        }
        timer = new Timer();

        // The clock model runs onTick every 1000 milliseconds
        timer.schedule(new TimerTask() {
            @Override public void run() {
                // fire event
                if (listener!= null){
                    listener.onTick();
                }
            }
        }, /*initial delay*/ 1000, /*periodic delay*/ 1000);
    }

    @Override
    public void stop() {
        if (timer!= null) {
            timer.cancel();
            timer= null;
        }
    }
}
