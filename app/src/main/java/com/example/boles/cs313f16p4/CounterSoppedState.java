package com.example.boles.cs313f16p4;

//package edu.luc.etl.cs313.android.simplecounter.model.state;


    import com.example.boles.cs313f16p4.R;

/**
 * @author laufer
 */

public class CounterSoppedState implements State {

    private final CounterSMStateView sv;

    public CounterSoppedState(CounterSMStateView sv) { this.sv = sv; }

    @Override
    public void updateView() {
        sv.updateUIRuntime();
    }

    @Override
    public int getId() {
        return R.string.STOPPED;
    }

    /**
     * User pressed the button
     */
    @Override
    public void onStartStopReset() {
        onStartStopReset(0);
    }

    @Override
    public void onStartStopReset(int initialValue) {
        if (initialValue>0) {
            sv.actionSetCounterTo(initialValue-1);
        }
        sv.Increment(); // transition to waiting state
        sv.actionRunWaitingTimer();
    }

    @Override
    public void onTick() {
// just stop the timer if any ... Or ignore it
        sv.actionStopWaitingTimer();
    }
}

