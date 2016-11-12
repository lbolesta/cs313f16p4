package com.example.boles.cs313f16p4;

//package edu.luc.etl.cs313.android.simplecounter.model.state;

/**
 * The restricted view states have of their surrounding state machine.
 * This is a client-specific interface in Peter Coad's terminology.
 *
 * @author laufer
 */

public interface CounterSMStateView {
    // transitions

    /**
     * Transition to WAITING
     */
    void Increment();
    /**
     * Transition to RUNNING
     */
    void t3SecInterval();
    /**
     * Transition to RUNNING
     */
    void t99Reached();
    /**
     * Transition to ALARM
     */
    void TimerAtZero();

    // transitions
    void toRunningState();
    void toStoppedState();
    void toWaitingState();
    void toAlarmState();

    //
    void UpdateView();
    void Reset();




    // actions
    void actionInit(); //----------
    void actionReset(); //----------
    void actionStop(); //----------

    /**
     * Counter actions
     */
    void actionIncrementCounter();
    void actionDecrementCounter();
    void actionSetCounterTo(final int counter);


    /**
     * Internal timer ations
     */
    void actionRunWaitingTimer();
    void actionStopWaitingTimer();


    /**
     * Alert actions
     */
    void actionRunAlert();
    void actionStopAlert();


    /**
     * Key pressed
     */
    void actionStartStopReset();
    void actionStartStopReset(int initialCounter);


    void actionUpdateView();

    // state-dependent UI updates
    void updateUIRuntime();









}
