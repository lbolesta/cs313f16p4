package com.example.boles.cs313f16p4;

//package edu.luc.etl.cs313.android.simplecounter.model.state;

        import com.example.boles.cs313f16p4.UIListener;
        import com.example.boles.cs313f16p4.OnTickListener;

/**
 * A state in a state machine. This interface is part of the State pattern.
 *
 * @author laufer
 */
interface State extends UIListener, OnTickListener {
    void updateView();
    int getId();
}
