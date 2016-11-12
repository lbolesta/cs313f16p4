package com.example.boles.cs313f16p4;

/**
 * A source of onTick events for the stopwatch.
 * This interface is typically implemented by the model.
 *
 * @author laufer
 */
public interface OnTickSource {
    void setOnTickListener(OnTickListener listener);
}
