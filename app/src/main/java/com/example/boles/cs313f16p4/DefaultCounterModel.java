package com.example.boles.cs313f16p4;

//package edu.luc.etl.cs313.android.simplecounter.model.counter;

/**
 * An implementation of the counter data model.
 */

public class DefaultCounterModel implements CounterModel {

    private /* static */ int MAXCOUNTER=99; //  // it should be static, but statim members are disabled in this task.

    private int counter = 0;

    public DefaultCounterModel(){
        this.counter=0;
    }


    public DefaultCounterModel(int counter){resetCounter_(counter);}


    @Override
    public void resetCounter() {
        resetCounter_(0);

    }
    private void resetCounter_(int counter) {
        this.counter= (counter>=0)?counter:0;
        this.counter= (this.counter<=MAXCOUNTER)?this.counter:MAXCOUNTER;
    }

    @Override
    public void resetCounter(int counter) {
        resetCounter_(counter);
    }

    @Override
    public void incCounter() {resetCounter_(this.counter+1);}

    @Override
    public void decrCounter(){resetCounter_(this.counter-1);}

    @Override
    public int getCounter() {
        return this.counter;
    }
}
