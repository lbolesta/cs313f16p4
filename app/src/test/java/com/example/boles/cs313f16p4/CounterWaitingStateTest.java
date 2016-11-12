package com.example.boles.cs313f16p4;


        import junit.framework.TestCase;

        import android.R;

/**
 * @author laufer
 */
public class CounterWaitingStateTest extends TestCase {

    private CounterWaitingState state;
    private boolean updateUIRuntimeCalled;
    private boolean actionRunWaitingTimerCalled;
    private boolean IncrementCalled;
    private boolean t3SecIntervalCalled;


    public void setUp() throws Exception {
        super.setUp();

        updateUIRuntimeCalled=false;
        actionRunWaitingTimerCalled=false;
        IncrementCalled=false;
        t3SecIntervalCalled=false;

        state = new CounterWaitingState(new CounterSMStateView(){
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
                t3SecIntervalCalled=true;
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
        assertEquals(R.string.WAITING, state.getId());

    }

    public void testOnStartStopReset() throws Exception {
        state.onStartStopReset();
        assertTrue(IncrementCalled);
        assertTrue(actionRunWaitingTimerCalled);

    }

    public void testOnTick() throws Exception {
        state.onTick();
        state.onTick();
        state.onTick();
        assertTrue(t3SecIntervalCalled);


    }

    public void testResetWaitingCounter() throws Exception {
        state.onTick();
        state.onTick();
        assertFalse(t3SecIntervalCalled);
        state.ResetWaitingCounter();
        state.onTick();
        state.onTick();
        assertFalse(t3SecIntervalCalled);
        state.onTick();
        assertTrue(t3SecIntervalCalled);



    }

}
