package com.example.boles.cs313f16p4;



import android.R;

/**

 */
public class CounterRunningState implements State {

    private final CounterSMStateView sv;

    public CounterRunningState(CounterSMStateView sv){
        this.sv = sv;
    }
    @Override
    public void updateView() {
        sv.updateUIRuntime();
    }

    @Override
    public int getId() {
        return R.string.RUNNING;
    }


    @Override
    public void onStartStopReset() {
        onStartStopReset(0);
    }

    @Override
    public void onStartStopReset(int initialValue) {
        sv.actionInit();
        sv.actionUpdateView();

    }

    @Override
    public void onTick() {
        sv.actionDecrementCounter();
        sv.actionUpdateView();
    }
}
