package com.example.boles.cs313f16p4;

import android.os.Bundle;

import com.example.boles.cs313f16p4.UIUpdateListener;
import com.example.boles.cs313f16p4.ClockModel;
import com.example.boles.cs313f16p4.DefaultClockModel;
import com.example.boles.cs313f16p4.CounterModel;
import com.example.boles.cs313f16p4.DefaultCounterModel;
import com.example.boles.cs313f16p4.DefaultCounterStateMachine;
import com.example.boles.cs313f16p4.StateMachine;

/**
 * An implementation of the model facade.
 *
 * @author laufer
 */

public class CounterModelFasade implements ModelFacade {

    private CounterModel counterModel;
    private ClockModel clockModel;
    private StateMachine stateMachine;

    public CounterModelFasade() {

        this.counterModel = new DefaultCounterModel();
        this.clockModel = new DefaultClockModel();
        this.stateMachine = new DefaultCounterStateMachine(counterModel,clockModel);
        clockModel.setOnTickListener(stateMachine);
    }


    @Override
    public void onStart() {
        stateMachine.actionInit();
    }

    @Override
    public void RestoreModelData(Bundle res) {
        stateMachine.RestoreModelData(res);

    }

    @Override
    public void GetModelData(Bundle res) {
        stateMachine.GetModelData(res);

    }

    @Override
    public void onStartStopReset() {
        stateMachine.onStartStopReset();
    }

    @Override
    public void onStartStopReset(int initialValue) {
        stateMachine.onStartStopReset(initialValue);
    }

    @Override
    public void setUIUpdateListener(final UIUpdateListener listener) { stateMachine.setUIUpdateListener(listener); }
}
