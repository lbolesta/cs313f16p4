package com.example.boles.cs313f16p4;

/**
 * Created by Boles on 11/11/2016.
 */

//public class CounterAlarmStatepackage edu.luc.etl.cs313.android.simplecounter.model.state;


import android.R;
/**
 * @author laufer
 */

public class CounterAlarmState implements State {

    private final CounterSMStateView sv;

    public CounterAlarmState(final CounterSMStateView sv){
        this.sv = sv;
    }
    @Override
    public void updateView() {
        sv.updateUIRuntime();
    }

    @Override
    public int getId() {
        return R.string.ALARM;
    }


    @Override
    public void onStartStopReset() {
        onStartStopReset(0);
    }

    @Override
    public void onStartStopReset(int initialValue) {
        sv.actionStopAlert();
        sv.actionInit();

    }

    @Override
    public void onTick() {
// just ignore it if any
    }
}


