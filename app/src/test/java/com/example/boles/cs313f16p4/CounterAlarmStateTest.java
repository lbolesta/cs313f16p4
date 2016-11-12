package com.example.boles.cs313f16p4;

        import junit.framework.TestCase;

        import android.R;

/**
 * @author laufer
 */
public class CounterAlarmStateTest extends TestCase {
    private CounterAlarmState alarmState;
    private boolean updateUIRuntimeCalled;
    private boolean actionStopAlertCalled;
    private boolean actionInitCalled;

    public void setUp() throws Exception {
        super.setUp();
        updateUIRuntimeCalled=false;
        actionStopAlertCalled=false;
        actionInitCalled=false;

        alarmState = new CounterAlarmState(new CounterSMStateView(){

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
                actionStopAlertCalled=true;
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

    public void tearDown() throws Exception {
        alarmState= null;

    }

    public void testUpdateView() throws Exception {

        alarmState.updateView();
        assertTrue(updateUIRuntimeCalled);
    }

    public void testGetId() throws Exception {

        assertEquals(R.string.ALARM,alarmState.getId());

    }

    public void testOnStartStopReset() throws Exception {

        alarmState.onStartStopReset();
        assertTrue(actionStopAlertCalled);
        assertTrue(actionInitCalled);
    }

}