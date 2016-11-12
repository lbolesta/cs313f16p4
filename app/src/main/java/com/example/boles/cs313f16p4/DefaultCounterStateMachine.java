package com.example.boles.cs313f16p4;

//package edu.luc.etl.cs313.android.simplecounter.model.state;

        import android.os.Bundle;

        import android.R;
        import com.example.boles.cs313f16p4.UIUpdateListener;
        import com.example.boles.cs313f16p4.ClockModel;
        import com.example.boles.cs313f16p4.CounterModel;

/**
 * An implementation of the state machine for the counter.
 *
 * @author laufer
 */
public class DefaultCounterStateMachine implements StateMachine, CounterSMStateView {
    /**
     * Max value for the counter
     */
    private /* static*/ final int MAXCOUNTER=99; // static is disabled in this task. oO
    /**
     * The counter. It keeps the number of secoconds
     */
    private final CounterModel counterModel;

    /**
     * The tick source. This clock is restarted every time the user increments the counter.
     */
    private final ClockModel clockModel;


    /**
     * The constructor
     *
     * @param counterModel The tick counter
     * @param clockModel The tick source
     */
    public DefaultCounterStateMachine(final CounterModel counterModel, final ClockModel clockModel) {
        this.counterModel = counterModel;
        this.clockModel = clockModel;
    }


    /**
     * The internal state of this adapter component. Required for the State pattern.
     */
    private State state;
    // Set state and update state name in GUI
    protected void setState(final State state) {
        this.state = state;
        uiUpdateListener.updateState(state.getId());
    }

    /**
     *  Start new waiting interval metter ...
     */
    private void restartWaitingClock(){
        actionStopWaitingTimer();
        actionRunWaitingTimer();
    }

    /**
     *  A listener for UI update notifications.
     */
    private UIUpdateListener uiUpdateListener;

    @Override
    public void setUIUpdateListener(final UIUpdateListener uiUpdateListener) {
        this.uiUpdateListener = uiUpdateListener;
    }

    // forward event uiUpdateListener methods to the current state
    // these must be synchronized because events can come from the
    // UI thread or the timer thread

    /**
     * User clicked the button
     */
    @Override
    public void onStartStopReset() {

//         state.onStartStopReset();
        onStartStopReset(0);
    }

    @Override
    public void onStartStopReset(int initialValue) {
        state.onStartStopReset(initialValue);
    }


    @Override public /* synchronized */ void onTick()      { state.onTick(); }

    // Update timer field in GUI
    @Override public void updateUIRuntime() {
        uiUpdateListener.updateTime(counterModel.getCounter());
    }

    // known states
    private final State STOPPED     = new CounterSoppedState(this);
    private final State RUNNING     = new CounterRunningState(this);
    private final State ALARM       = new CounterAlarmState(this);
    private final CounterWaitingState WAITING  = new CounterWaitingState(this);


    // transitions
    @Override public void toRunningState()    {setState(RUNNING);}
    @Override public void toStoppedState()    { setState(STOPPED); }
    @Override public void toWaitingState()    { setState(WAITING); }
    @Override public void toAlarmState()      { setState(ALARM); }


    /**
     * Transition to WAITING
     */
    @Override public void Increment() {
        toWaitingState();
        actionIncrementCounter();
        actionUpdateView();
    }

    // actions

    @Override
    public void t3SecInterval() {
        toRunningState();
        Beep();

    }

    /**
     * Requests the beep from the GUI
     */
    private void Beep(){
        uiUpdateListener.Beep();
    }

    /**
     * MACCOUNTERVALU is reached
     */
    @Override
    public void t99Reached() {
        toRunningState();
        Beep();
    }

    @Override
    public void UpdateView() {
        state.updateView();
    }

    /**
     * Go to ALARM state
     */
    @Override
    public void TimerAtZero() { toAlarmState(); }

    /**
     * Go to initial STOPPED state
     */

    @Override
    public void Reset() {
        actionStop(); toStoppedState(); actionReset();
    }

    @Override public void actionInit()       { toStoppedState(); actionReset(); }
    @Override public void actionReset()      { counterModel.resetCounter(); actionStopAlert(); WAITING.ResetWaitingCounter();  actionUpdateView(); }
    @Override public void actionStop()       { clockModel.stop(); } //-----------------

    /**
     * Counter actions.
     * This code may be moved to the corresponded state class
     */
    @Override
    public void actionIncrementCounter() {
        this.counterModel.incCounter();
        if (this.counterModel.getCounter()<MAXCOUNTER){
            restartWaitingClock();
            WAITING.ResetWaitingCounter();
            actionUpdateView();
        }else{
            t99Reached();
        }
    }

    @Override
    public void actionDecrementCounter() {
        this.counterModel.decrCounter();
        if (this.counterModel.getCounter()<=0){
            ZeroCounterReached();
        }
    }
    private void ZeroCounterReached(){
        toAlarmState();
        actionUpdateView();
        actionStopWaitingTimer();
        actionRunAlert();
    }
    @Override
    public void actionSetCounterTo(int counter) {
        this.counterModel.resetCounter(counter);
        if (this.counterModel.getCounter()<MAXCOUNTER){
            restartWaitingClock();
            WAITING.ResetWaitingCounter();
            actionUpdateView();
        }else{
            t99Reached();
        }

    }
    /**
     * Internal timer ations
     */
    @Override
    public void actionRunWaitingTimer() { clockModel.start(); }

    @Override
    public void actionStopWaitingTimer() { clockModel.stop(); }

    /**
     * Alert actions
     */
    @Override
    public void actionRunAlert() {
        uiUpdateListener.RunAlert();
    }

    @Override
    public void actionStopAlert() {
        uiUpdateListener.StopAlert();
    }

    @Override public void actionStartStopReset() {
        onStartStopReset();
    }

    @Override
    public void actionStartStopReset(int initialCounter) {
        if (state.getId()==STOPPED.getId()){
            counterModel.resetCounter(initialCounter-1);
        }
        actionStartStopReset();
    }


    @Override public void actionUpdateView() {
        state.updateView();
    }

    /**
     * Store in the res internal state
     * @param res
     */
    public void GetModelData(Bundle res){
        res.putInt("CounterCurrentStateId",state.getId());
        res.putInt("CounterCurrentValue",counterModel.getCounter());
        res.putInt("WAITINGCounterCurrentValue",WAITING.GetWaitingCounter());

    }

    /**
     * Restore internal state
     * @param res
     */
    public void RestoreModelData(Bundle res){
        counterModel.resetCounter(res.getInt("CounterCurrentValue",0));
        WAITING.SetWaitingCounter(res.getInt("WAITINGCounterCurrentValue",0));

        updateUIRuntime(); // update counter
        setStateById(res.getInt("CounterCurrentStateId",STOPPED.getId()));
    }
    private void setStateById(int stateId){
        switch(stateId){
            case R.string.RUNNING: restartWaitingClock(); toRunningState(); break;
            case R.string.WAITING: restartWaitingClock(); toWaitingState(); break;
            case R.string.ALARM: ZeroCounterReached(); break;
            default:  actionStopWaitingTimer(); toStoppedState();
        }

    }

}
