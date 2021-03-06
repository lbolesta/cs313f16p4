package com.example.boles.cs313f16p4;



        import junit.framework.TestCase;

        import android.R;

public class CounterSoppedStateTest extends TestCase {

    private CounterSoppedState state;
    private boolean updateUIRuntimeCalled;
    private boolean actionRunWaitingTimerCalled;
    private boolean IncrementCalled;


    public void setUp() throws Exception {
        super.setUp();


        updateUIRuntimeCalled=false;
        actionRunWaitingTimerCalled=false;
        IncrementCalled=false;


        state = new CounterSoppedState(new CounterSMStateView() {
            /**
             * Transition to WAITING
             */
            @Override
            public void Increment() {
                IncrementCalled=true;

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

            }

            @Override
            public void actionSetCounterTo(int counter) {

            }

            /**
             * Internal timer ations
             */
            @Override
            public void actionRunWaitingTimer() {
                actionRunWaitingTimerCalled=true;

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
        assertEquals(R.string.STOPPED, state.getId());

    }

    public void testOnStartStopReset() throws Exception {
        state.onStartStopReset();
        assertTrue(IncrementCalled);
        assertTrue(actionRunWaitingTimerCalled);
    }

    public void testOnTick() throws Exception {

    }

}
