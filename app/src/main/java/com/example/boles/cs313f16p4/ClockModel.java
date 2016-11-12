package com.example.boles.cs313f16p4;

/**
 * The active model of the internal clock that periodically emits tick events.
 *
 * @author laufer
 */
public interface ClockModel extends OnTickSource {
    void start();
    void stop();
}
