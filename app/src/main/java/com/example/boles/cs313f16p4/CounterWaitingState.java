package com.example.boles.cs313f16p4;

//package edu.luc.etl.cs313.android.simplecounter.model.state;

import com.example.boles.cs313f16p4.R;

/**
 * @author laufer
 */

public class CounterWaitingState implements State  {
    private final int WAITIMGDELAY=3; // it should be static, but statim members are disabled in this task.
    private int waitingCounter;
    private final CounterSMStateView sv;

    public CounterWaitingState(CounterSMStateView sv) {
        this.sv = sv;
        waitingCounter=0;
    }

    @Override
    public void updateView() {
        sv.updateUIRuntime();
    }

    @Override
    public int getId() { return R.string.WAITING; }


    @Override
    public void onStartStopReset() {
        onStartStopReset(0);
    }

    @Override
    public void onStartStopReset(int initialValue) {
        sv.actionRunWaitingTimer();
        sv.Increment(); // transition to waiting state

    }

    @Override
    public void onTick() {
        waitingCounter++;
        if (waitingCounter>=3){
            sv.t3SecInterval(); // Transition to RUNNING
            sv.actionUpdateView();

        }

    }

    public void ResetWaitingCounter(){
        SetWaitingCounter(0);
    }

    /**
     * Save/Restore internal state
     *
     */
    public int GetWaitingCounter(){
        return waitingCounter;
    }
    public void SetWaitingCounter(int waitingCounter){
        this.waitingCounter=waitingCounter;
    }


}

