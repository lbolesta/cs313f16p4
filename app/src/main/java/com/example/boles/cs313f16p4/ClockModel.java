package com.example.boles.cs313f16p4;

/**
 * The active model of the internal clock that does tick events.
 *
 *
 */
public interface ClockModel extends OnTickSource {
    void start();
    void stop();
}
