package com.example.boles.cs313f16p4;


/**
 * The passive data model of the counter.
 * It does not emit any events.
 *
 */

public interface CounterModel {
    /**
     * constructors
     */
    void resetCounter();
    void resetCounter(int counter);

    /**
     * Increment the counter
     */
    void incCounter();
    /**
     * Decrement the counter
     *
     */
    void decrCounter();

    /**
     * Getter for the counter
     * @return int Current value of the counter
     */
    int getCounter();

}

