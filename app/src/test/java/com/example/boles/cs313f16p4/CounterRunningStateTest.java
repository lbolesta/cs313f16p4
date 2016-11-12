package com.example.boles.cs313f16p4;

//package edu.luc.etl.cs313.android.simplecounter.model.state;

        import junit.framework.TestCase;

        import android.R;

/**
 * @author laufer
 */
public class CounterRunningStateTest extends TestCase {

    private CounterRunningState state;
    private boolean updateUIRuntimeCalled;
    private boolean actionInitCalled;
    private boolean actionUpdateViewCalled;
    private boolean actionDecrementCounterCalled;


    public void setUp() throws Exception {
        super.setUp();

        updateUIRuntimeCalled=false;
        actionInitCalled = false;
        actionUpdateViewCalled = false;
        actionDecrementCounterCalled = false;

        state = new CounterRunningState(new CounterSMStateView(){
            /**
             * Transition to WAITING
             */
            @Override
            public void Increment() {

            }

            /**
             * Transition to RUNNING
             */
            @Override
            public void t3SecInterval() {

            }

            /**
             * Transition to RUNNING
             */
            @Override
            public void t99Reached() {

            }

            /**
             * Transition to ALARM
             */
            @Override
            public void TimerAtZero() {

            }

            @Override
            public void toRunningState() {

            }

            @Override
            public void toStoppedState() {

            }

            @Override
            public void toWaitingState() {

            }

            @Override
            public void toAlarmState() {

            }

            @Override
            public void UpdateView() {

            }

            @Override
            public void Reset() {

            }

            @Override
            public void actionInit() {
                actionInitCalled=true;

            }

            @Override
            public void actionReset() {

            }

            @Override
            public void actionStop() {

            }

            /**
             * Counter actions
             */
            @Override
            public void actionIncrementCounter() {

            }

            @Override
            public void actionDecrementCounter() {
                actionDecrementCounterCalled=true;
            }

            @Override
            public void actionSetCounterTo(int counter) {

            }

            /**
             * Internal timer ations
             */
            @Override
            public void actionRunWaitingTimer() {

            }

            @Override
            public void actionStopWaitingTimer() {

            }

            /**
             * Alert actions
             */
            @Override
            public void actionRunAlert() {

            }

            @Override
            public void actionStopAlert() {

            }

            /**
             * Key pressed
             */
            @Override
            public void actionStartStopReset() {

            }

            @Override
            public void actionStartStopReset(int initialCounter) {

            }

            @Override
            public void actionUpdateView() {
                actionUpdateViewCalled=true;
            }

            @Override
            public void updateUIRuntime() {
                updateUIRuntimeCalled=true;

            }
        });

    }

    public void testUpdateView() throws Exception {
        state.updateView();
        assertTrue(updateUIRuntimeCalled);

    }

    public void testGetId() throws Exception {
        assertEquals(R.string.RUNNING, state.getId());

    }

    public void testOnStartStopReset() throws Exception {
        state.onStartStopReset();
        assertTrue(actionInitCalled);
        assertTrue(actionUpdateViewCalled);

    }

    public void testOnTick() throws Exception {
        state.onTick();
        assertTrue(actionDecrementCounterCalled);
        assertTrue(actionUpdateViewCalled);

    }

}