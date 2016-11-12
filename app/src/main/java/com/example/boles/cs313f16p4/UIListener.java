package com.example.boles.cs313f16p4;

/**
 * A listener for stopwatch events coming from the UI.
 */
public interface UIListener {
    void onStartStopReset();
    void onStartStopReset(int initialValue);
}