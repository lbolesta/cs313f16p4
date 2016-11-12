package com.example.boles.cs313f16p4;

/**
 * A source of UI update events for the stopwatch.
 * This interface is typically implemented by the model..
 */
public interface UIUpdateSource {
    void setUIUpdateListener(UIUpdateListener listener);
}
