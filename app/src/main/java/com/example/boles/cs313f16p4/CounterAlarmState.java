package com.example.boles.cs313f16p4;


import android.R;


public class CounterAlarmState implements State {

    private final CounterSMStateView sv;

    /*this instance of counterSMStateView*/
    public CounterAlarmState(final CounterSMStateView sv){
        this.sv = sv;
    }

    /* update GUI view*/
    @Override
    public void updateView() {
        sv.updateUIRuntime();
    }

    /*for proper ring*/
    @Override
    public int getId() {
        return R.string.ALARM;
    }

    /*resets to 0*/
    @Override
    public void onStartStopReset() {
        onStartStopReset(0);
    }

    /*counter stops or is started at an initial val*/
    @Override
    public void onStartStopReset(int initialValue) {
        sv.actionStopAlert();
        sv.actionInit();

    }

    @Override
    public void onTick() {

    }
}


