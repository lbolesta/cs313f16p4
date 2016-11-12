package com.example.boles.cs313f16p4;


        import com.example.boles.cs313f16p4.UIListener;
        import com.example.boles.cs313f16p4.OnTickListener;

/**
 * A state in a state machine. This interface is part of the State pattern.
 *
 *
 */
interface State extends UIListener, OnTickListener {
    void updateView();
    int getId();
}
